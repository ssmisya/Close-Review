package CloseReview.user;

import CloseReview.repository.UserRepository;
import jakarta.persistence.*;


/**
 * User
 *
 * @author star
 */
@Entity
@Table(name = "user_info")//使用user作为名字将导致报错，user是H2 SQL的保留字
public class User {

//    private static final long serialVersionUID = 3340373364530753417L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", columnDefinition = "varchar(100)", nullable = false, unique = true)
    private String userName;

    @Column(name = "nick_name", columnDefinition = "varchar(100)")
    private String nickName;

    @Column(name = "password", columnDefinition = "varchar(100)", nullable = false)
    private String password;

    @Column(name = "email", columnDefinition = "varchar(100)", nullable = false)
    private String email;

    @Column(name = "organization", columnDefinition = "varchar(100)", nullable = false)
    private String organization;

    @Column(name = "region", columnDefinition = "varchar(100)", nullable = false)
    private String region;

    @Column(name = "enabled", columnDefinition = "BOOL",nullable = false)
    private boolean enabled;

    public User(String userName, String nickName, String password, String email, String organization, String region){
        super();
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.organization = organization;
        this.region = region;
        this.enabled = true;
    }

    public User(String userName, String nickName, String password, String email, String organization, String region,boolean enabled){
        super();
        this.userName = userName;
        this.nickName = nickName;
        this.password = password;
        this.email = email;
        this.organization = organization;
        this.region = region;
        this.enabled = enabled;
    }

    public User() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganization(){
        return organization;
    }
    public void setOrganization(String organization){
        this.organization = organization;
    }

    public String getRegion(){
        return region;
    }

    public void setRegion(String region){
        this.region = region;
    }

    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString(){
        return "id="+this.getId()+" userName="+this.getUserName()+" nickName="+this.getNickName()+" password="+this.getPassword()
                +" Email="+this.getEmail()+" Organization="+this.getOrganization()+" Region="+this.getRegion();
    }
}