import com.study.dao.UserDaoMysqlImpl;
import com.study.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test1() {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDao(new UserDaoMysqlImpl());
        userService.show();
    }

    @Test
    public void test2() {
        // 加载spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserServiceImpl userService = (UserServiceImpl) context.getBean("userService");
        userService.show();
    }

}
