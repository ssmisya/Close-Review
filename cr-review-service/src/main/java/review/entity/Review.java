package review.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paper_id", columnDefinition = "varchar(100)", nullable = false)
    private String paperId;

    @Column(name = "user_name", columnDefinition = "varchar(100)", nullable = false)
    private String userName;

    @Column(name = "conference_id", columnDefinition = "varchar(100)")
    private String conferenceId;

    @Column(name = "rebuttal", columnDefinition = "text")
    private String rebuttal;

    @Column(name = "advice", columnDefinition = "text")
    private String advice;

    @Column(name = "abstract_info", columnDefinition = "varchar(800)")
    private String abstractInfo;

    public Paper(String paperId, String conferenceName,String conferenceId, String author, String pdfPath, String email, String status, String rebuttal, String advice, String abstractInfo,String topic) {
        this.paperId = paperId;
        this.conferenceId = conferenceId;
        this.conferenceName = conferenceName;
        this.author = author;
        this.pdfPath = pdfPath;
        this.email = email;
        this.status = status;
        this.rebuttal = rebuttal;
        this.advice = advice;
        this.abstractInfo = abstractInfo;
        this.topic = topic;
    }

    public Paper(PaperDto dto){
        this.paperId = "tbd";
        this.conferenceName = dto.getConferenceName();
        this.conferenceId = "tbd";
        this.author = dto.getAuthor();
        this.pdfPath = "/tmp/save_pdf/1.pdf";
        this.email = dto.getEmail();
        this.status = "submit";
        this.rebuttal = "";
        this.advice = "";
        this.abstractInfo = dto.getAbstractInfo();
        this.topic = dto.getTopic();
    }

    public Paper() {
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(String conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRebuttal() {
        return rebuttal;
    }

    public void setRebuttal(String rebuttal) {
        this.rebuttal = rebuttal;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getAbstractInfo() {
        return abstractInfo;
    }

    public void setAbstractInfo(String abstractInfo) {
        this.abstractInfo = abstractInfo;
    }
}

