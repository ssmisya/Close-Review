package submit.service;

import io.grpc.StatusRuntimeException;
import jakarta.servlet.http.HttpServletResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import net.devh.boot.grpc.examples.lib.ConferenceRequest;
import net.devh.boot.grpc.examples.lib.ConferenceServiceGrpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import submit.entity.Paper;
import submit.entity.PaperDto;
import submit.exception.ConferenceNormalException;
import submit.exception.ConferenceNotFoundException;
import submit.repository.PaperRepository;

import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class SubmitService {
    @GrpcClient("cr-conference-service")
    private ConferenceServiceGrpc.ConferenceServiceBlockingStub conferenceStub;

    private static final Logger log = LoggerFactory.getLogger(SubmitService.class);

    final PaperRepository repository;

    public SubmitService(PaperRepository repository) {
        this.repository = repository;
    }

    public String submitPaper(PaperDto dto) {
        Paper paper = new Paper(dto);
        String conferenceName = dto.getConferenceName();
        String conferenceId = null;
        Long paperNum = 0L;
        try{
            // get id
            ConferenceRequest idRequest = ConferenceRequest.newBuilder()
                    .setQueryContent(conferenceName)
                    .build();
            conferenceId = conferenceStub.queryConferenceIdByConferenceName(idRequest).getValue();
            if(conferenceId.isBlank()) throw new ConferenceNotFoundException(conferenceName);

            // get status
            ConferenceRequest statusRequest = ConferenceRequest.newBuilder()
                    .setQueryContent(conferenceId)
                    .build();
            String status = conferenceStub.queryConferenceStatus(statusRequest).getValue();
            if (!status.equals("open_submit")) throw new ConferenceNormalException("Conference is not ready to receive Papers!");

            //get paper num
            ConferenceRequest pnRequest = ConferenceRequest.newBuilder()
                    .setQueryContent(conferenceId)
                    .build();
            paperNum = conferenceStub.queryPaperNumAndUpdateOne(pnRequest).getValue();
            // set paper
            String paperId = conferenceId +"_"+ new String(String.valueOf(paperNum));
            String pdfPath = "tmp/conference/"+conferenceId+"/"+paperId+".pdf";
            paper.setPaperId(paperId);
            paper.setPdfPath(pdfPath);
            paper.setConferenceId(conferenceId);
            return repository.save(paper).toString();
        }catch (final StatusRuntimeException e) {
            e.printStackTrace();
            return "GRPC_FAILED_with_" + e.getStatus().getCode().name();
        }catch (final Exception e){
            e.printStackTrace();
            return "FAILED_with_exception";
        }
    }

    public String uploadPaperByPaperId(MultipartFile file, String paper_id) throws IOException {
        Optional<Paper> paper = repository.findByPaperId(paper_id);
        if(paper.isPresent()){

            String filePath =  Paths.get(paper.get().getPdfPath()).toAbsolutePath().toString();
            log.info("saving into "+filePath);
            File dest = new File(filePath);
            createParentFile(dest);
            dest = new File(filePath);
            Files.copy(file.getInputStream(), dest.toPath());
            return "Upload file success : " + dest.getAbsolutePath();
        }
       return "No such paper";
    }

    public String downloadPaperByPaperId(String paper_id, HttpServletResponse response){
        Optional<Paper> paper = repository.findByPaperId(paper_id);
        if(paper.isPresent()){
            String filePath = paper.get().getPdfPath();
            try {
                // path是指想要下载的文件的路径
                File file = new File(filePath);
                // 获取文件名
                String filename = file.getName();
                // 获取文件后缀名
                String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
                // 将文件写入输入流
                FileInputStream fileInputStream = new FileInputStream(file);
                InputStream fis = new BufferedInputStream(fileInputStream);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                // 清空response
                response.reset();
                // 设置response的Header
                response.setCharacterEncoding("UTF-8");
                //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
                //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
                // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
                // 告知浏览器文件的大小
                response.addHeader("Content-Length", "" + file.length());
                OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                outputStream.write(buffer);
                outputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return "No such paper";
    }

    private void createParentFile(File file) {
        File parentFile = file.getParentFile();
        if (null != parentFile && !parentFile.exists()) {
            parentFile.mkdirs(); // 创建文件夹
            createParentFile(parentFile); // 递归创建父级目录
        }
    }
}
