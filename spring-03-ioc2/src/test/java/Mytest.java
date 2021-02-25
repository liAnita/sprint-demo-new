import com.study.demo01.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mytest {
    @Test
    public void test1() {
        //spring容器会一开始就创建好所有对象，需要的时候直接获取，
        // 在容器中注入了UserTwo，但没有获取它，仍然会执行userTwo的无参构建函数。
        // 获取两次相同的user对象，输出true.

        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        User user = (User) context.getBean("user");
        User user2 = (User) context.getBean("user");
        System.out.println(user==user2); //输出是true
//        运行结果：
//        这是无参构建函数～
//        true

    }
}
