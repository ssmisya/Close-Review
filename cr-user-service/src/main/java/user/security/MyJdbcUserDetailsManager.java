package user.security;

import user.Exception.*;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import user.dto.UserDto;
import user.entity.User;

import javax.sql.DataSource;

public class MyJdbcUserDetailsManager extends JdbcUserDetailsManager {

    private final String createUserSql = "insert into user_info (user_name,nick_name,password, email,organization,region, enabled) values (?,?,?,?,?,?,?)";

    private final String createAuthoritySql = "insert into user_role (user_name, role) values (?,?)";

    private final String getUserSql = "SELECT * FROM (USER_INFO AS A INNER JOIN USER_ROLE AS  B ON A.USER_NAME=B.USER_NAME)";

    public MyJdbcUserDetailsManager(DataSource dataSource) {
        super(dataSource);
        super.setUsersByUsernameQuery("SELECT user_name AS username,password,enabled FROM user_info where user_name = ?");
        super.setAuthoritiesByUsernameQuery("SELECT user_name AS username,role AS authority FROM user_role where user_name=?");

    }
    public User getUser(String UserId){
        return null;
    }

    public void createUser(final UserDto user) {
        validateUserDetails(user);
        user.encryptPassword(6);
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
