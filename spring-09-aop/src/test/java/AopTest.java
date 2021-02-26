import com.dao.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
    @Test
    public void test1(){
       ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
       //代理（Proxy）：向目标对象应用通知之后创建的对象。
       UserDao userDao = context.getBean("userService", UserDao.class);
        userDao.add();
    }
}

