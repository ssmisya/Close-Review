package CloseReview;

//import jakarta.activation.DataSource;
import javax.sql.DataSource;

import CloseReview.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JdbcCustomizeDatasourceApplicationTests {

    @Autowired
    @Qualifier("H2Datasource")
    private DataSource dataSource;

    @Test
    public void springJdbcTemplateTest(){
        try{
            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
            String queryStr = "select * from user_info";
            List<User> resultList = new ArrayList<>();
            jdbcTemplate.query(queryStr, (ResultSet resultSet)->{
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setNickName(resultSet.getString("nick_name"));
                user.setPassword(resultSet.getString("password"));
                user.setEmail(resultSet.getString("email"));
                user.setRegion(resultSet.getString("region"));
                user.setOrganization(resultSet.getString("organization"));
                resultList.add(user);
            });
            resultList.forEach((User user) -> System.out.println(user.toString()));
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}