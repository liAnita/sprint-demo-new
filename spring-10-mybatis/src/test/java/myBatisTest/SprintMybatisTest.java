package myBatisTest;

import com.mapper.UserMapper;
import com.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SprintMybatisTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userDao = context.getBean("userDaoImpl", UserMapper.class);
        List<User> users = userDao.selectUser();
        for (User user : users) {
            System.out.println(user);
        }

    }
}
