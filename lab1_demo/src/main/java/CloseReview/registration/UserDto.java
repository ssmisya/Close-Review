package CloseReview.registration;

import com.unboundid.util.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class UserDto {
    @NotNull
    @NotEmpty
    private String userName;

    @NotNull
    @NotEmpty
    private String nickName;

    @NotNull
    @NotEmpty
    private String password;
    private String matchingPassword;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String organization;

    @NotNull
    @NotEmpty
    private String region;

    @NotNull
    @NotEmpty
    private String role;

    @NotNull
    @NotEmpty
    private Boolean enabled;

    public UserDto(String userName, String nickName, String password, String matchingPassword, String email, String organization, String region, String role, Boolean enabled) {
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.email = email;
        this.organization = organization;
        this.region = region;
        this.role = role;
        this.enabled = enabled;
    }

    public UserDto() {

    }

    public UserDto(String userName, String nickName, String password, String matchingPassword, String email, String organization, String region, String role) {
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.email = email;
        this.organization = organization;
        this.region = region;
        this.role = role;
        this.enabled = true;
    }


    // standard getters and setters

    public String getUserName() {
        return userName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPassword() {
        return password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getOrganization() {
        return organization;
    }

    public String getRegion() {
        return region;
    }

    public String getRole() {
        return role;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void encryptPassword(int strength){
        setPassword(new BCryptPasswordEncoder(strength).encode(getPassword()));
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", matchingPassword='" + matchingPassword + '\'' +
                ", email='" + email + '\'' +
                ", organization='" + organization + '\'' +
                ", region='" + region + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}