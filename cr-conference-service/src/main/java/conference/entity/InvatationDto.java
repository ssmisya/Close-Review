package conference.entity;

import jakarta.persistence.Column;

public class InvatationDto {

    private String conferenceId;

    private String userName;

    public InvatationDto(String conferenceId, String userName) {
        this.conferenceId = conferenceId;
        this.userName = userName;
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
}
