import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.study.config.AppConfig;
import com.study.demo01.pojo.User;

public class MyTest {

    @Test
    public void test(){
        //如果完全使用配置类方式，我们只能通过AnnotationConfig上下文获取容器，通过配置类的class对象加载。
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        User user = context.getBean("getUser", User.class);
        System.out.println(user);

    }
}
