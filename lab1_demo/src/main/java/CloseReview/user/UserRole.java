package CloseReview.user;

import jakarta.persistence.*;

/**
 * UserRole
 *
 * @author star
 */
@Entity
@Table(name = "user_role")
public class UserRole {

//    private static final long serialVersionUID = 1997955934111931587L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", columnDefinition = "varchar(30)" , nullable = false)
    private String userName;

    @Column(name = "role",  columnDefinition = "varchar(20)" , length = 20, nullable = false)
    private String role;

    public UserRole(String userName, String role){
        super();
        this.role = role;
        this.userName = userName;
    }

    public UserRole() {

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

