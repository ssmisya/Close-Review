package conference.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "invatation")
public class Invatation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "conference_id", columnDefinition = "varchar(100)", nullable = false)
    private String conferenceId;

    @Column(name = "user_name", columnDefinition = "varchar(100)", nullable = false)
    private String userName;

    @Column(name = "status", columnDefinition = "varchar(100)", nullable = false)
    private String status;

    public Invatation(String conferenceId, String userName) {
        this.conferenceId = conferenceId;
        this.userName = userName;
        this.status = "invited";
    }

    public Invatation(InvatationDto dto) {
        this.conferenceId = dto.getConferenceId();
        this.userName = dto.getUserName();
        this.status = "invited";
    }

    public Invatation() {

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}