package conference.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "conference")
public class Conference {

//    private static final long serialVersionUID = 3340373364530753417L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conference_id", columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String conferenceId;

    @Column(name = "conference_name", columnDefinition = "varchar(100)")
    private String conferenceName;

    @Column(name = "short_name", columnDefinition = "varchar(100)")
    private String shortName;

    @Column(name = "topic", columnDefinition = "varchar(100)")
    private String topic;

    @Column(name = "status", columnDefinition = "varchar(100)")
    private String status;

    @Column(name = "open_time", columnDefinition = "varchar(100)")
    private String openTime;

    @Column(name = "ddl_time", columnDefinition = "varchar(100)")
    private String ddlTime;

    @Column(name = "hold_place", columnDefinition = "varchar(100)")
    private String holdPlace;

    @Column(name = "paper_num")
    private Long paperNum;


    public Conference() {
    }

    public Conference(String conferenceId, String conferenceName, String shortName, String topic, String status, String openTime, String ddlTime, String holdPlace,Long paperNum) {
        this.conferenceId = conferenceId;
        this.conferenceName = conferenceName;
        this.shortName = shortName;
        this.topic = topic;
        this.status = status;
        this.openTime = openTime;
        this.ddlTime = ddlTime;
        this.holdPlace = holdPlace;
        this.paperNum = paperNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(String conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getDdlTime() {
        return ddlTime;
    }

    public void setDdlTime(String ddlTime) {
        this.ddlTime = ddlTime;
    }

    public String getHoldPlace() {
        return holdPlace;
    }

    public void setHoldPlace(String holdPlace) {
        this.holdPlace = holdPlace;
    }

    public Long getPaperNum() {
        return paperNum;
    }

    public void setPaperNum(Long paperNum) {
        this.paperNum = paperNum;
    }
}