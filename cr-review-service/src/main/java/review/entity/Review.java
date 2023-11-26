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

    @Column(name = "rank", columnDefinition = "int")
    private int rank;

    @Column(name = "confidence", columnDefinition = "text")
    private String confidence;

    @Column(name = "comment", columnDefinition = "text")
    private String comment;

    @Column(name = "rebuttal_sign", columnDefinition = "int")
    private int rebuttalSign;


    public Review(String paperId, String userName, String conferenceId, String rebuttal, int rank, String confidence, String comment) {
        this.paperId = paperId;
        this.userName = userName;
        this.conferenceId = conferenceId;
        this.rebuttal = rebuttal;
        this.rank = rank;
        this.confidence = confidence;
        this.comment = comment;
        this.rebuttalSign = 0;
    }

    public Review(ReviewAssignDto dto){
        this.paperId = dto.getPaperId();
        this.userName = dto.getUserName();
        this.conferenceId = dto.getConferenceId();
        this.rebuttalSign = 0;
        this.rebuttal = "";
        this.rank = 0;
        this.confidence = "";
        this.comment = "";
    }

    public Review(String paperId, String userName, String conferenceId) {
        this.paperId = paperId;
        this.userName = userName;
        this.conferenceId = conferenceId;
    }

    public Review(){}

    public int getRebuttalSign() {
        return rebuttalSign;
    }

    public void setRebuttalSign(int rebuttalSign) {
        this.rebuttalSign = rebuttalSign;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(String conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getRebuttal() {
        return rebuttal;
    }

    public void setRebuttal(String rebuttal) {
        this.rebuttal = rebuttal;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getConfidence() {
        return confidence;
    }

    public void setConfidence(String confidence) {
        this.confidence = confidence;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

