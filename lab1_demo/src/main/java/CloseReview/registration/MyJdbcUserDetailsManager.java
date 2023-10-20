package CloseReview.registration;

import CloseReview.registration.Exception.NullUserNameException;
import CloseReview.registration.Exception.PasswordNotMatchException;
import CloseReview.registration.Exception.UserAlreadyExistException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import javax.validation.constraints.AssertFalse;

public class MyJdbcUserDetailsManager extends JdbcUserDetailsManager {

    private final String createUserSql = "insert into user_info (user_name,nick_name,password, email,organization,region, enabled) values (?,?,?,?,?,?,?)";

    private final String createAuthoritySql = "insert into user_role (user_name, role) values (?,?)";

    public MyJdbcUserDetailsManager(DataSource dataSource) {
        super(dataSource);
        super.setUsersByUsernameQuery("SELECT user_name AS username,password,enabled FROM user_info where user_name = ?");
        super.setAuthoritiesByUsernameQuery("SELECT user_name AS username,role AS authority FROM user_role where user_name=?");

    }

    public void createUser(final UserDto user) {
        validateUserDetails(user);
        user.encryptPassword(16);
        getJdbcTemplate().update(this.createUserSql, (ps) -> {
            ps.setString(1, user.getUserName());
            ps.setString(2,user.getNickName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getOrganization());
            ps.setString(6, user.getRegion());
            ps.setBoolean(7, user.isEnabled());

        });
        if (getEnableAuthorities()) {
            insertUserAuthorities(user);
        }
    }

    private void insertUserAuthorities(UserDto user) {
//        for (GrantedAuthority auth : user.getAuthorities()) {
//            getJdbcTemplate().update(this.createAuthoritySql, user.getUsername(), auth.getAuthority());
//        }
        getJdbcTemplate().update(this.createAuthoritySql, user.getUserName(), "ROLE_"+user.getRole());
    }


    //验证用户注册是否合法
    private void validateUserDetails(UserDto user){
        if (user.getUserName().isEmpty() || user.getUserName().isBlank()){
            throw new NullUserNameException();
        }
        super.setUserExistsSql("select user_name from user_info where user_name = ?");
        if(super.userExists(user.getUserName())){
            throw new UserAlreadyExistException(user.getUserName());
        }
        if(!user.getPassword().equals(user.getMatchingPassword())){
            throw new PasswordNotMatchException();
        }
    }
}
