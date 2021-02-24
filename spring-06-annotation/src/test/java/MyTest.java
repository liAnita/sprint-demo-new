import com.study.pojo.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        Person person = context.getBean("person", Person.class);
        Person person1 = context.getBean("person", Person.class);
        System.out.println(person==person1);
        person.getDog().shout();
        person.getCat().shout();
        System.out.println(person.getName());
    }
}
