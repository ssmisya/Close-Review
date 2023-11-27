package conference.entity;

public class InvatationHandleDto {
    private String conferenceId;

    private String userName;

    private String advice;

    public InvatationHandleDto(String conferenceId, String userName, String advice) {
        this.conferenceId = conferenceId;
        this.userName = userName;
        this.advice = advice;
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

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}
