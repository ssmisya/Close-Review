package conference.controller;

import conference.entity.*;
import conference.exception.ConferenceNotFoundException;
import conference.repository.ConferenceRepository;
import conference.repository.InvatationRepository;
import conference.repository.PcMemberRepository;
import conference.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;
import java.util.Optional;

@RestController
public class ConferenceController {

    private final ConferenceRepository repository;
    private final PcMemberRepository pcMemberRepository;

    private final InvatationRepository invatationRepository;

    ConferenceController(ConferenceRepository repository, PcMemberRepository pcMemberRepository, InvatationRepository invatationRepository) {
        this.repository = repository;
        this.pcMemberRepository = pcMemberRepository;
        this.invatationRepository = invatationRepository;
    }

    @Autowired
    ConferenceService conferenceService;

    //hello rpc
    @GetMapping("/conference/hello/{text}")
    String receiveGreeting (@PathVariable String text) {
        return conferenceService.receiveGreeting(text);
    }

    // 查找全部会议
    @GetMapping("/conference/all")
    List<Conference> all() {
        return repository.findAll();
    }

    // 新建会议
    @PostMapping("/conference/new")
    Conference newConference(@RequestBody Conference newConference) {
        return repository.save(newConference);
    }


    //通过会议ID查找会议
    @GetMapping("/conference/searchbyid/{conference_id}")
    Optional<Conference> searchByConferenceId(@PathVariable String conference_id) {
        Optional<Conference> res =  repository.findByConferenceId(conference_id);
        if (res.isEmpty()) {
            throw new ConferenceNotFoundException(conference_id);
        }
        return res;
    }

    //通过会议名称查找会议
    @GetMapping("/conference/searchbyname/{conference_name}")
    List<Conference> searchByConferenceName(@PathVariable String conference_name) {
        List<Conference> res =  repository.findByConferenceName(conference_name);
        if (res.isEmpty()) {
            throw new ConferenceNotFoundException(conference_name);
        }
        return res;
    }

    //会议审核
    @PostMapping("/conference/examine/{conference_id}")
    String examineByConferenceId(@PathVariable String conference_id,@RequestBody String advice) {
        return conferenceService.examineByConferenceId(conference_id,advice);
    }

    //开启投稿
    @PostMapping("/conference/open_submit/{conference_id}")
    String openByConferenceId(@PathVariable String conference_id,@RequestBody String advice) {
        return conferenceService.openSubmitByConferenceId(conference_id,advice);
    }

    //修改会议信息
    @PutMapping("/conference/update/{conference_id}")
    Conference updateConference(@RequestBody Conference newConference, @PathVariable String conference_id) {
        return repository.findByConferenceId(conference_id)
                .map(conference -> {
                    conference.setConferenceId(newConference.getConferenceId());
                    conference.setConferenceName(newConference.getConferenceName());
                    conference.setShortName(newConference.getShortName());
                    conference.setTopic(newConference.getTopic());
                    conference.setStatus(newConference.getStatus());
                    conference.setOpenTime(newConference.getOpenTime());
                    conference.setDdlTime(newConference.getDdlTime());
                    conference.setHoldPlace(newConference.getHoldPlace());
                    conference.setPaperNum(newConference.getPaperNum());
                    return repository.save(conference);
                })
                .orElseGet(() -> {
                    newConference.setConferenceId(conference_id);
                    return repository.save(newConference);
                });
    }

    //删除会议信息
    @DeleteMapping("/conference/update/{conference_id}")
    void deleteConference(@PathVariable String conference_id) {
        repository.deleteByConferenceId(conference_id);
    }

    //通过用户名搜索用户
    @GetMapping("/conference/pcmember/search/{user_name}")
    String searchUserByUserName(@PathVariable String user_name) {
        return conferenceService.searchUser(user_name);
    }

    //查找某一会议的PC  member
    @GetMapping("/conference/pcmember/{conference_id}")
    Optional<Conference> searchPcmemberByConferenceId(@PathVariable String conference_id) {
        Optional<Conference> res =  repository.findByConferenceId(conference_id);
        if (res.isEmpty()) {
            throw new ConferenceNotFoundException(conference_id);
        }
        return res;
    }

    // 查找某一topic的Pc member
    @GetMapping("/conference/pcmember/findbytopic/{topic}")
    List<PcMember> findPcmemberByTopic(@PathVariable String  topic) {
        return pcMemberRepository.findByTopic(topic);
    }


    //查找自己是否是某个会议的Pcmember
    @GetMapping("/conference/pcmember/findbyusername/{user_name}")
    Optional<PcMember> findPcmemberByUserName(@PathVariable String user_name){
        return pcMemberRepository.findByUserName(user_name);
    }


    // 新建Pc member
    @PostMapping("/conference/pcmember/new")
    PcMember newPcmember(@RequestBody PcMember newPcmember) {
        return pcMemberRepository.save(newPcmember);
    }

    // 邀请Pc member
    @PostMapping("/conference/pcmember/invite/doinvite")
    String invitePcmemberByConferenceIdAndUserName(@RequestBody InvatationDto dto) {
        return conferenceService.inviteByConferenceIdAndUserName(dto);
    }

    // Pc member 接受或拒绝邀请
    @PostMapping("/conference/pcmember/invite/handle")
    String handleInvatationByPcmemberByConferenceIdAndUserName(@RequestBody InvatationHandleDto dto) {
        return conferenceService.handleInvatationByConferenceIdAndUserName(dto.getConferenceId(), dto.getUserName(), dto.getAdvice());
    }

    //查找全部邀请
    @GetMapping("/conference/pcmember/invite/all")
    List<Invatation> findAllInvatation() {
        return invatationRepository.findAll();
    }
    //查找某一会议的邀请
    @GetMapping("/conference/pcmember/invite/search/conferenceid/{conference_id}")
    List<Invatation> findInvatationByConferenceId(@PathVariable String conference_id) {
        return invatationRepository.findByConferenceId(conference_id);
    }

    //查找某个用户的邀请
    @GetMapping("/conference/pcmember/invite/search/username/{user_name}")
    List<Invatation> findInvatationByUserName(@PathVariable String user_name) {
        return invatationRepository.findByUserName(user_name);
    }

    //删除某个邀请
    @DeleteMapping("/conference/pcmember/invite/delete/")
    void deleteInvatationByUserNameAndConferenceId(@RequestParam String user_name,@RequestParam String conference_id){
        invatationRepository.deleteByConferenceIdAndUserName(user_name,conference_id);
    }
}
