package conference.service;
import conference.entity.Conference;
import conference.entity.Invatation;
import conference.entity.InvatationDto;
import conference.repository.ConferenceRepository;
import conference.repository.InvatationRepository;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.examples.lib.HelloRequest;
import net.devh.boot.grpc.examples.lib.UserRequest;
import net.devh.boot.grpc.examples.lib.UserSearchServiceGrpc.UserSearchServiceBlockingStub;

import net.devh.boot.grpc.client.inject.GrpcClient;

import org.springframework.expression.spel.ast.OpNE;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConferenceService {
    @GrpcClient("cr-user-service")
    private UserSearchServiceBlockingStub userSearchServiceBlockingStub;

    final ConferenceRepository repository;
    final InvatationRepository invatationRepository;
    public ConferenceService(ConferenceRepository repository, InvatationRepository invatationRepository) {
        this.repository = repository;
        this.invatationRepository = invatationRepository;
    }

    public String  searchUser(String name) {
        try {
            UserRequest request = UserRequest.newBuilder()
                    .setQueryUsername(name)
                    .build();
            return userSearchServiceBlockingStub.searchUserByName(request).toString();
        }catch (final StatusRuntimeException e) {
            e.printStackTrace();
            return "FAILED with " + e.getStatus().getCode().name();
        }
    }
    public String receiveGreeting(String name) {
        try{
            HelloRequest request = HelloRequest.newBuilder()
                    .setName(name)
                    .build();
            return userSearchServiceBlockingStub.sayHello(request).getMessage();
        }catch (final StatusRuntimeException e) {
            e.printStackTrace();
            return "FAILED with " + e.getStatus().getCode().name();
        }

    }
    public String examineByConferenceId(String conferenceId,String advice){
        Optional<Conference> conferenceOptional= repository.findByConferenceId(conferenceId);
        if (conferenceOptional.isPresent()){
            Conference conference = conferenceOptional.get();
            if(!conference.getStatus().equals("register")) return "examine is not available";
            if(advice.equals("approve")){
                conference.setStatus("approved");
            }else{
                conference.setStatus("notApproved");
            }
            repository.save(conference);
            return "examine submit correctly";
        }
        return "no such conference";
    }
    public String openSubmitByConferenceId(String conferenceId,String advice){
        Optional<Conference> conferenceOptional= repository.findByConferenceId(conferenceId);
        if (conferenceOptional.isPresent()){
            Conference conference = conferenceOptional.get();
            if(!conference.getStatus().equals("approved")) return "open_submit is not available";
            if(advice.equals("open_submit")){
                conference.setStatus("open_submit");
                repository.save(conference);
                return "open_submit successful";
            }else{
                return "nothing to do";
            }
        }
        return "no such conference";
    }

    public String inviteByConferenceIdAndUserName(String conferenceId,String userName){
        try {
            UserRequest request = UserRequest.newBuilder()
                    .setQueryUsername(userName)
                    .build();
            if(userSearchServiceBlockingStub.searchUserByName(request).getStatus() == -1) return "no such user";
        }catch (final StatusRuntimeException e) {
            e.printStackTrace();
            return "FAILED with " + e.getStatus().getCode().name();
        }
        Optional<Conference> conferenceOptional = repository.findByConferenceId(conferenceId);
        if (conferenceOptional.isPresent()){
            Optional<Invatation> invatationOptional = invatationRepository.findByConferenceIdAndUserName(userName,conferenceId);
            if (invatationOptional.isPresent()) return "invatation already exists";
            Invatation invatation = new Invatation(conferenceId,userName);
            invatationRepository.save(invatation);
            return "invite successfully";
        }
        return "no such conference";
    }

    public String inviteByConferenceIdAndUserName(InvatationDto dto){
        return inviteByConferenceIdAndUserName(dto.getConferenceId(), dto.getUserName());
    }

    public String handleInvatationByConferenceIdAndUserName(String conferenceId,String userName,String advice){
        Optional<Invatation> invatationOptional = invatationRepository.findByConferenceIdAndUserName(userName,conferenceId);
        if (invatationOptional.isPresent()){
            Invatation invatation = invatationOptional.get();
            if (advice.equals("accept")){
                invatation.setStatus("accepted");
            }else{
                invatation.setStatus("declined");
            }
            invatationRepository.save(invatation);
            return "handle invatation successfully";
        }
        return "no such invatation";
    }

}
