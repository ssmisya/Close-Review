package CloseReview.registration;

import CloseReview.user.User;
import CloseReview.user.UserRole;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface UserMapper {
    User loadUserByUsername(String user_name);
    List<UserRole> getUserRolesByUserName(String user_name);
    int addUserByUsername(UserDto userRegister);
    List<UserRole> getAllRole();
    int addRole(Integer uid,Integer rid);
}
