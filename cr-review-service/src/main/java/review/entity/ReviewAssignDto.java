package review.entity;

import jakarta.persistence.Column;

public class ReviewAssignDto {

    private String paperId;

    private String userName;

    private String conferenceId;

    public ReviewAssignDto(String paperId, String userName, String conferenceId) {
        this.paperId = paperId;
        this.userName = userName;
        this.conferenceId = conferenceId;
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
}
