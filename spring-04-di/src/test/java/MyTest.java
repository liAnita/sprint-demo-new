import com.study.pojo.Student;
import com.study.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {

    @Test
    public void testDi() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        Student student = (Student) context.getBean("student");
        System.out.println(student);
    }

    /*
    Student{name='易烊千玺',
            address=Address{address='上海'},
            hobbies=[读书, 看电影, 写代码],
            games=[LOL, 王者荣耀, 吃鸡, 魂斗罗],
            score={math=120, chinese=100, english=90, biology=80},
            subject=[biology, chemistry, physics], work='null',
            studentInfo={user=root, Driver=mysql.5.0, pwd=123456}
      }
     */

    @Test
    public void testCPNamespace(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        User user = context.getBean("user2", User.class);
        System.out.println(user);
    }
}


