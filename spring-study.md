



[狂神说笔记][https://www.kuangstudy.com/bbs/1344884033053581313](https://www.kuangstudy.com/bbs/1344884033053581313)

https://mp.weixin.qq.com/s/kvp_3Uva1J2Q5ZVqCUzEsA

# IOC理论推导

在我们之前的业务中，用户的需求可能会影响我们原来的代码，我们需要根据用户的需求修改源代码
如果程序代码量十分大，需改一次的代价十分昂贵！
我们使用一个set接口实现，已经发生了革命性的变化

```java
    private UserDao userDao;
    //利用set进行动态实现值的注入
    public void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
```
- 之前，是程序主动创建对象！控制权在程序员手上！
- 使用了set注入后，程序员不再具有主动性，而是变成了被动的接受对象！
- 这种思想从本质上解决了问题，我们程序员不用再去管理对象的创建了。

# IOC本质

```text
控制反转loC(Inversion of Control)，是一种设计思想，DI(依赖注入)是实现loC的一种方法，也有人认为DI只是loC的另一种说法。没有loC的程序中，我们使用面向对象编程，对象的创建与对象间的依赖关系完全硬编码在程序中，对象的创建由程序自己控制，控制反转后将对象的创建转移给第三方，个人认为所谓控制反转就是:获得依赖对象的方式反转了。
采用XML方式配置Bean的时候，Bean的定义信息是和实现分离的，而采用注解的方式可以把两者合为一体，Bean的定义信息直接以注解的形式定义在实现类中，从而达到了零配置的目的。
控制反转是一种通过描述(XML或注解）并通过第三方去生产或获取特定对象的方式在Spring中实现控制反转
的是loC容器，其实现方法是依赖注入(Dependency Injection,Dl)。
```

# IOC创建对象的方式
1. **使用无参构造函数，默认。**
```xml
  <!--默认无参-->
      <bean id="user" class="com.srz.pojo.User">
          <property name="name" value="超级大帅哥"></property>
      </bean>
```
2. **有参构造创建对象**

   ```xml
   <!-- 第一种，下标赋值的操作 index指的是元素的个数-->
   <bean id="userT" class="com.srz.pojo.User">
     &lt;!&ndash; index指构造方法 , 下标从0开始 &ndash;&gt;   Exception
     <constructor-arg index="0" value="srzzz"/>
   </bean>
   ```
   ```xml
   <!-- 第二种 类型 如果两个就不行了 不建议使用   -->
   <bean id="userTT" class="com.srz.pojo.User">
                             <!-- 引用类型必须全类名 -->
       <constructor-arg type="java.lang.String" value="虽然在"></constructor-arg>
   </bean>
   ```
   ```xml
   <!--  第三种 ,参数名 掌握这一种就行 -->
   <bean id="user" class="com.srz.pojo.User">
        <constructor-arg name="name" value="srz"></constructor-arg>
   </bean>
   ```
总结：在配置文件加载的时候，容器中的对象就已经初始化了！

# Spring的配置

### 1. 别名

```xml
<!--  别名  -->
<alias name="user" alias="u"></alias>
```
### 2. Beam的配置

```xml
<!--
       id 是bean的标识符,要唯一,如果没有配置id,name就是默认标识符
       如果配置id,又配置了name,那么name是别名
       name可以设置多个别名,可以用逗号,分号,空格隔开
       如果不配置id和name,可以根据applicationContext.getBean(.class)获取对象;
    class是bean的全限定名=包名+类名
-->
<bean id="hello" name="hello2 h2,h3;h4" class="com.kuang.pojo.Hello">
    <property name="name" value="Spring"/>
</bean>
```
### 3. Import

```xml
<import resource="{path}/beans.xml"/>
```
>import,一般用于团队开发，可以将多个配置文件导入合并为一个。
application.xml中导入多个配置，使用总的配置即可！
```xml
<import resource="beans1.xml"/>
<import resource="beans2.xml"/>
<import resource="beans2.xml"/>
```



# 依赖注入（DI）

### 构造器注入

>前面已经说过(无参、有参),参考spring-03-ioc。
````xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--默认无参-->
<!--
    <bean id="user" class="com.srz.pojo.User">
        <property name="name" value="超级大帅哥"></property>
    </bean>
-->
<!--     第一种根据index参数下标设置-->
<!--   <bean id="user" class="com.srz.pojo.User">
&lt;!&ndash;        &lt;!&ndash; index指构造方法 , 下标从0开始 &ndash;&gt;&ndash;&gt;&ndash;&gt;&ndash;&gt;
        <constructor-arg index="0" value="srzzz"></constructor-arg>
   </bean>-->
    <!-- 第二种 如果两个就不行了 不建议使用   -->
<!--    <bean id="userTT" class="com.srz.pojo.User">
                                &lt;!&ndash; 引用类型必须全类名 &ndash;&gt;
        <constructor-arg type="java.lang.String" value="虽然在"></constructor-arg>
    </bean>
    -->
    <!--  第三种 ,参数名 -->
<!--    <bean id="user" class="com.srz.pojo.User">
        <constructor-arg name="name" value="srz"></constructor-arg>
    </bean>
    -->
    <bean id="user" class="com.srz.pojo.User">
        <constructor-arg index="0" value="srz"></constructor-arg>
        <!--   可与参数赋值共存，会覆盖前面的值     -->
        <property name="name" value="srz1"></property>
    </bean>
    <!--  别名  -->
    <alias name="user" alias="u"></alias>
    <!--  bean的配置 -->
<!--    <bean id="user" class="com.srz.pojo.User" name="usernew"></bean>-->
    <!--bean就是java对象,由Spring创建和管理-->
    <!--
       id 是bean的标识符,要唯一,如果没有配置id,name就是默认标识符
       如果配置id,又配置了name,那么name是别名
       name可以设置多个别名,可以用逗号,分号,空格隔开
       如果不配置id和name,可以根据applicationContext.getBean(.class)获取对象;
    class是bean的全限定名=包名+类名
    -->
<!--    <bean id="hello" name="hello2 h2,h3;h4" class="com.kuang.pojo.Hello">-->
<!--        <property name="name" value="Spring"/>-->
<!--    </bean>-->
</beans>
``````
### Set方式注入(重点)

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="student" class="com.srz.pojo.Student">
        <!--    第一种  普通值注入  value=...    -->
        <property name="name" value="宋"></property>
<!--
        <property name="name">
            <value>srz</value>
        </property>
-->
        <!--            第二种  Bean注入  value=...    -->
        <property name="address" ref="address"></property>
        <!--    第三种  数组注入  ref    -->
        <property name="books">
            <array>
                <value>三体</value>
                <value>球状闪电</value>
                <value>流浪地球</value>
                <value>乡村教师</value>
            </array>
        </property>
        <!--    第四种  List注入  ref=...    -->
        <property name="hobbys">
            <list>
                <value>听歌</value>
                <value>写代码</value>
                <value>看电影</value>
            </list>
        </property>
        <!--   第五种   Map注入     -->
        <property name="card">
            <map>
                <entry key="身份证" value="213124"></entry>
                <entry key="银行卡" value="342342"></entry>
            </map>
        </property>
        <!--   第六种   Set注入     -->
        <property name="games">
            <set>
                <value>1</value>
                <value>2</value>
                <value>3</value>
            </set>
        </property>
        <!--    null    -->
        <property name="wife">
            <null></null>
        </property>
        <!--    properties    -->
        <property name="info">
            <props>
                <prop key="学号">2019041204</prop>
                <prop key="姓名">srz</prop>
                <prop key="性别">男</prop>
            </props>
        </property>
    </bean>
    <bean id="address" class="com.srz.pojo.Address">
        <property name="address" value="济南"></property>
    </bean>
</beans>
```

### 扩展方式注入

```xml
 <!--使用p-namespace（properties）进行更简洁的 XML 配置 -->
    <bean id="user" class="com.study.demo01.pojo.User" p:name="易烊千玺" p:age="18"></bean>

    <!--使用c-namespace(construct-arg)进行更简洁的 XML 配置 -->
    <bean id="user2" class="com.study.demo01.pojo.User" c:name="四字弟弟" c:age="19"></bean>

```
>注意事项：
>1.需要在xml头部导入约束文件 2.c-namespace需要实体类包含有参构造
>```xml
>xmlns:p="http://www.springframework.org/schema/p"
>xmlns:c="http://www.springframework.org/schema/c"
>```

# bean的作用域

![image-20201203083618833](https://typora-blog.oss-cn-qingdao.aliyuncs.com/image-20201203083618833.png)

1. **Singleton 单例模式（在整个软件系统中，只会产生该类的一个对象）（Spring默认机制）**
![img](https://typora-blog.oss-cn-qingdao.aliyuncs.com/singleton.png?d=1614047731989)
   
   ```xml
    <bean id="accountService" class="com.something.DefaultAccountService"/>
    <!-- the following is equivalent, though redundant (singleton scope is the default) -->
    <bean id="accountService" class="com.something.DefaultAccountService" scope="singleton"/>
   ```
2. **Prototype 原型模式：每次从容器中get的时候，都会产生一个新的对象**![image-20201203084410401](https://typora-blog.oss-cn-qingdao.aliyuncs.com/image-20201203084410401.png)

   ```xml
   <bean id="accountService" class="com.something.DefaultAccountService" scope="prototype"/>
   ```

3. **其余的request、session、applicaiton 只能在web开发中使用到**



# Bean的自动装配

由于在手动配置xml过程中，常常发生字母缺漏和大小写等错误，而无法对其进行检查，使得开发效率降低。

采用自动装配将避免这些错误，并且使配置简单化。

- 自动装配是spring满足bean依赖的一种方式
- spring会在上下文中自动寻找，并自动给bean装配属性

在spring中装配有**三种**方式

1. 在xml中显示的配置

2. 在java 中显示配置
3. ==隐士的自动装配 【重要==】

这里我们主要讲第三种：自动化的装配bean。

Spring的自动装配需要从两个角度来实现，或者说是两个操作：

1. 组件扫描(component scanning)：spring会自动发现应用上下文中所创建的bean；
2. 自动装配(autowiring)：spring自动满足bean之间的依赖，也就是我们说的IoC/DI；

组件扫描和自动装配组合发挥巨大威力，使得显示的配置降低到最少。

### 1. **byName (按名称自动装配)**

```xml
 <!-- byName : 会在上下文中自动化查找，和自己对象set方法参数值相同的beanId-->
<bean id="user" class="com.kuang.pojo.User" autowire="byName">
   <property name="str" value="langli"/>
</bean>
```

**小结：**

当一个bean节点带有 autowire byName的属性时。

1. 将查找其类中所有的set方法名，例如setCat，获得将set去掉并且首字母小写的字符串，即cat。
2. 去spring容器中寻找是否有此字符串名称id的对象。
3. 如果有，就取出注入；如果没有，就报空指针异常。

### 2.byType (按类型自动装配)

==使用autowire byType首先需要保证：同一类型的对象，在spring容器中唯一。如果不唯一，会报不唯一的异常:NoUniqueBeanDefinitionException。==

```xml
 <!-- byType : 会在上下文中自动化查找，和自己对象属性相同的bean, bytype可以省略beanId-->
<bean id="user" class="com.kuang.pojo.User" autowire="byType">
   <property name="str" value="langli"/>
</bean>
```

**推荐不使用自动装配xml配置 , 而使用注解 .**



### 3.使用注解装配

> jdk1.5开始支持注解，spring2.5开始全面支持注解。

准备工作：利用注解的方式注入属性。

1. 在Spring配置文件中导入约束

```xml
xmlns:context="http://www.springframework.org/schema/context"

http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context.xsd
```

2. 开启属性注解支持

   ```xml
   <!-- 开启属性注解支持-->
   <context:annotation-config></context:annotation-config>
   ```

#### @Autowired

```java
@Resource(name = "cat...")
//其区别是Autowired可以动过ByName和ByType，而Resource只能通过ByName
//代表如果找不到装配的 不抛出异常 正常情况下找不到直接抛异常
required = false
@Autowired
@Qualifier(value = "dog111")   
/*
需要和Autowired配合使用    如果类型的值不唯一则将Autowired的required默认属性值设为false，再配合Qualifier进行使用
*/
@Nullable //段标记了这个注解，说明这个字段可以为null
```

- 直接在属性上使用即可！也可以在set上使用！
- 使用Autowired可以不用再写set方法了，前提是自动装配的属性在IOC容器（Spring容器）中存在且符合名字ByType！

**@Autowired与@Resource异同：**

1. @Autowired与@Resource都可以用来装配bean。都可以写在字段上，或写在setter方法上。

2. @Autowired默认按类型装配（属于spring规范），默认情况下必须要求依赖对象必须存在，如果要允许null 值，可以设置它的required属性为false，如：@Autowired(required=false) ，如果我们想使用名称装配可以结合@Qualifier注解进行使用

3. @Resource（属于J2EE复返），默认按照名称进行装配，名称可以通过name属性进行指定。如果没有指定name属性，当注解写在字段上时，默认取字段名进行按照名称查找，如果注解写在setter方法上默认取属性名进行装配。当找不到与名称匹配的bean时才按照类型进行装配。但是需要注意的是，如果name属性一旦指定，就只会按照名称进行装配。

4. 它们的作用相同都是用注解方式注入对象，但执行顺序不同。@Autowired先byType，@Resource先byName。



# 使用注解开发

### 1.注意

- 在spring4之后要保证spring-aop包导入

```java
<dependency>
  <groupId>org.springframework</groupId>
  <artifactId>spring-aop</artifactId>
  <version>5.3.1</version>
</dependency>
```

- 使用注解需要导入context约束，增加注解支持

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xmlns:context="http://www.springframework.org/schema/context"
      xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">
    <!--  开启注解支持  -->
    <context:annotation-config></context:annotation-config>
</beans>
```

### 2.bean用注解实现

我们之前都是使用 bean 的标签进行bean注入，但是实际开发中，我们一般都会使用注解！

```xml
1、配置扫描哪些包下的注解
<!--指定注解扫描包-->
<context:component-scan base-package="com.kuang.pojo"/>
```

```java
2、在指定包下编写类，增加注解
// 相当于配置文件中 <bean id="user" class="当前注解的类"/>
@Component("user") //可以指定beanId,
@Component //不指定beanId是，默认为类名称首字母小写
public class User {
   public String name = "秦疆";
}
```

### 3.属性如何注入

```java
//组件
//此注解等价于 bean
@Component
public class User {
    //Value注解相当于xml中bean的赋值操作
    @Value("srz")
    public String name;
}
```

### 4.衍生注解

[@Component](https://github.com/Component)有几个衍生注解，为了更好的进行分层，Spring可以使用其它三个注解，功能一样，目前使用哪一个功能都一样

- dao【[@Repository](https://github.com/Repository)】

  ```java
  @Repository
  public class UserDao {
      public UserDao() {
          System.out.println("这是UserDao的无参构造函数！");
      }
  }
  ```

- sevice【[@Service](https://github.com/Service)】

  ```java
  @Service
  public class UerService {
      public UerService() {
          System.out.println("这是UserService的无参构造函数！");
      }
  }
  
  ```

- controller【@Controller】

  ```java
  @Controller
  public class UserController {
      public UserController() {
          System.out.println("这是UserController的无参构造函数！");
      }
  }
  ```

这四个注解的功能是一样的，都是都是代表将某个类注册到Sprng中，装配Bean

### 5.作用域

- singleton：默认的，Spring会采用单例模式创建这个对象。关闭工厂 ，所有的对象都会销毁。
- prototype：多例模式。关闭工厂 ，所有的对象不会销毁。内部的垃圾回收机制会回收

```java
@Controller("user")
@Scope("prototype")
public class User {
   @Value("langli")
   public String name;
}
```

### 6.自动装配

- @autowire,前面已学习。

### 7.小结

xml与注解

- xml 更加万能，适用于任何场合！维护方便！但配置稍显麻烦
- 注解 不是自己类用不了，维护相对复杂！

xml 与 注解 的最佳实践

- xml用来管理bean
- 注解只负责完成属性的注入
- 在使用过程中，只需要注意一个问题：必须让注解生效，需要开启注解的支持

```xml
<!--  指定扫描包，这个包下的注解就会生效  -->
<context:component-scan base-package="com.srz.pojo"></context:component-scan>
<!--  开启注解支持  -->
<context:annotation-config/>
<!--作用：
1.进行注解驱动注册，从而使注解生效
2.用于激活那些已经在spring容器里注册过的bean上面的注解，也就是显示的向Spring注册
3.如果不扫描包，就需要手动配置bean
4.如果不加注解驱动，则注入的值为null！-->
```



# 使用javaConfig实现配置

JavaConfig 原来是 Spring 的一个子项目，它通过 Java 类的方式提供 Bean 的定义信息，在 Spring4 的版本， JavaConfig 已正式成为 Spring4 的核心功能 

- 实体类

  ```java
  //@Component表示这个类被spring接管，将User注册到容器中
  @Component
  public class User {
  
      @Value("小可爱")
      private String name;
  
      @Override
      public String toString() {
          return "User{" +
                  "name='" + name + '\'' +
                  '}';
      }
  }
  ```

- 配置类

  ```java
  /*
   这个类也会被Spring托管，注入到ioc容器中，因为它本身就是一个Component
  1.@Configuration 表示这是一个配置类，等价于之前学习的beans.xml
  2.@ComponentScan("com.study") ：配置扫描哪些包下的注解,等价于xml中的 <context:component-scan base-package="com.study"/>
  4.@Import：导入合并其他配置类，类似于配置文件中的import标签
  */
  @Configuration
  @ComponentScan("com.study") 
  @Import(AppConfig2.class)  
  public class AppConfig {
  
      /*
      1.注册一个Bean,相当于之前写的<bean>标签
      2.这个方法的名称相当于bean的id
      3.这个方法的属性相当于bean的class
       */
      @Bean
      public User getUser() {
          return new User(); //就是返回要注入到bean的对象
      }
  }
  ```

- 测试类

  ```java
  public class MyTest {
  
      @Test
      public void test(){
          //如果完全使用配置类方式，我们只能通过AnnotationConfig上下文获取容器，通过配置类的class对象加载。
          ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
          User user = context.getBean("getUser", User.class);
          System.out.println(user);
  
      }
  }
  ```




#  代理模式

为什么要学习代理模式，因为AOP的底层机制就是动态代理！

代理模式：

- 静态代理
- 动态代理

学习aop之前 , 我们要先了解一下代理模式！

![image-20210225104522098](/Users/langli/Library/Application Support/typora-user-images/image-20210225104522098.png)

### 1.静态代理

**静态代理角色分析**

- 抽象角色 : 一般使用接口或者抽象类来实现
- 真实角色 : 被代理的角色
- 代理角色 : 代理真实角色 ; 代理真实角色后 , 一般会做一些附属的操作 
- 客户  :  使用代理角色来进行一些操作 

**代码实现**

Rent.java 即抽象角色

```java
//抽象角色：租房
public interface Rent {
   public void rent();
}
```

Host.java 即真实角色

```java
//真实角色: 房东，房东要出租房子
public class Host implements Rent{
   public void rent() {
       System.out.println("房屋出租");
  }
}
```

Proxy.java 即代理角色

```java
//代理角色：中介
public class Proxy implements Rent {

   private Host host;
   public Proxy() { }
   public Proxy(Host host) {
       this.host = host;
  }

   //租房
   public void rent(){
       seeHouse();
       host.rent();
       fare();
  }
   //看房
   public void seeHouse(){
       System.out.println("带房客看房");
  }
   //收中介费
   public void fare(){
       System.out.println("收中介费");
  }
}
```

Client.java 即客户

```
//客户类，一般客户都会去找代理！
public class Client {
   public static void main(String[] args) {
       //房东要租房
       Host host = new Host();
       //中介帮助房东
       Proxy proxy = new Proxy(host);
       //你去找中介！
       proxy.rent();
  }
}
```

分析：在这个过程中，你直接接触的就是中介，就如同现实生活中的样子，你看不到房东，但是你依旧租到了房东的房子通过代理，这就是所谓的代理模式，程序源自于生活，所以学编程的人，一般能够更加抽象的看待生活中发生的事情。

**静态代理的好处:**

- 可以使得我们的真实角色更加纯粹 . 不再去关注一些公共的事情 .
- 公共的业务由代理来完成 . 实现了业务的分工 ,
- 公共业务发生扩展时变得更加集中和方便 .

**缺点 :**

- 类多了 , 多了代理类 , 工作量变大了 . 开发效率降低 .

我们想要静态代理的好处，又不想要静态代理的缺点，所以 , 就有了动态代理 !

==我们在不改变原来的代码的情况下，实现了对原有功能的增强，这是AOP中最核心的思想==

AOP：纵向开发，横向开发

![image-20210225105106854](/Users/langli/Library/Application Support/typora-user-images/image-20210225105106854.png)

### 2.动态代理

[其他参考文档](https://segmentfault.com/a/1190000011291179)

动态代理利用了[JDK API](http://tool.oschina.net/uploads/apidocs/jdk-zh/)，动态地在内存中构建代理对象，从而实现对目标对象的代理功能。动态代理又被称为JDK代理或接口代理。

静态代理与动态代理的区别主要在：

- 静态代理在编译时就已经实现，编译完成后代理类是一个实际的class文件
- 动态代理是在运行时动态生成的，即编译完成后没有实际的class文件，而是在运行时动态生成类字节码，并加载到JVM中

>- 动态代理的角色和静态代理的一样 .
>
>- 动态代理的代理类是动态生成的 . 静态代理的代理类是我们提前写好的
>- 动态代理分为两类 : 一类是基于接口动态代理 , 一类是基于类的动态代理
>  - 基于接口的动态代理----JDK动态代理
>  - 基于类的动态代理--cglib
>  - 现在用的比较多的是 javasist 来生成动态代理 .
>  - 我们这里使用JDK的原生代码来实现，其余的道理都是一样的！

**特点：**
动态代理对象不需要实现接口，但是要求目标对象必须实现接口，否则不能使用动态代理。

JDK中生成代理对象主要涉及的类有：

- [java.lang.reflect Proxy](http://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/reflect/Proxy.html)，主要方法为

  ```java
  static Object    newProxyInstance(ClassLoader loader,  //指定当前目标对象使用类加载器
  
   Class<?>[] interfaces,    //目标对象实现的接口的类型
   InvocationHandler h      //事件处理器
  ) 
  //返回一个指定接口的代理类实例，该接口可以将方法调用指派到指定的调用处理程序。
  ```

- [java.lang.reflect InvocationHandler](http://tool.oschina.net/uploads/apidocs/jdk-zh/java/lang/reflect/InvocationHandler.html)，主要方法为

  ```java
  // 在代理实例上处理方法调用并返回结果。 
  Object invoke(Object proxy, Method method, Object[] args) 
  /*参数
  proxy - 调用该方法的代理实例
  method -方法对应于调用代理实例上的接口方法的实例。方法对象的声明类将是该方法声明的接口，它可以是代理类继承该方法的代理接口的超级接口。
  args -包含的方法调用传递代理实例的参数值的对象的阵列，或null如果接口方法没有参数。原始类型的参数包含在适当的原始包装器类的实例中，例如java.lang.Integer或java.lang.Boolean 。*/
  ```

  **代码实现**：

  - 接口类：Rent

  ```java
  //抽象角色：租房
  public interface Rent {
     public void rent();
  }
  ```

  - 目标对象：Host

  ```java
  //真实角色: 房东，房东要出租房子
  public class Host implements Rent{
     public void rent() {
         System.out.println("房屋出租");
    }
  }
  ```

  - 动态代理对象：UserProxyFactory

  ```java
  package com.demo03.proxy;
  
  import java.lang.reflect.InvocationHandler;
  import java.lang.reflect.Method;
  import java.lang.reflect.Proxy;
  
  public class ProxyInvocationHandle implements InvocationHandler {
  
      private Object target;
  
      public void setTarget(Object target) {
          this.target = target;
      }
  
      //生成代理类，重点是第二个参数，获取要代理的抽象角色！之前都是一个角色，现在可以代理一类角色
      public Object getProxy() {
          return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
      }
  
  
      // proxy : 代理类 method : 代理类的调用处理程序的方法对象.
      // 处理代理实例上的方法调用并返回结果
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          //核心：本质利用反射实现！
          method.invoke(target, args);
          return null;
      }
  }
  ```

  	- 测试类 Client

  ```java
  public class Client {
      public static void main(String[] args) {
  //        Rent host = new Host();
  //        ProxyInvocationHandle proxyInvocationHandle = new ProxyInvocationHandle();
  //        proxyInvocationHandle.setTarget(host);
  //        Rent proxy = (Rent) proxyInvocationHandle.getProxy();
  //        proxy.rent();
          //真实角色
          UserImpl user = new User();
          //代理实例的调用处理程序
          ProxyInvocationHandle proxyInvocationHandle = new ProxyInvocationHandle();
          proxyInvocationHandle.setTarget(user);//将真实角色放置进去！
          UserImpl proxy = (UserImpl) proxyInvocationHandle.getProxy();//动态生成对应的代理类！
          proxy.delete();
      }
  }
  ```

  核心：**一个动态代理 , 一般代理某一类业务 , 一个动态代理可以代理多个类，代理的是接口！、**

  总结：

  - 可以使得我们的真实角色更加纯粹 . 不再去关注一些公共的事情 .
  - 公共的业务由代理来完成 . 实现了业务的分工 ,
  - 公共业务发生扩展时变得更加集中和方便 .
  - 一个动态代理 , 一般代理某一类业务
  - 一个动态代理可以代理多个类，代理的是接口！

# AOP

### 1.介绍

> 什么是AOP
>
> AOP（Aspect Oriented Programming）意为：面向切面编程，通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术。AOP是OOP的延续，是软件开发中的一个热点，也是Spring框架中的一个重要内容，是函数式编程的一种衍生范型。利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。

![image-20210226114341577](/Users/langli/Library/Application Support/typora-user-images/image-20210226114341577.png)

>Aop在Spring中的作用
>
>==**提供声明式事务；允许用户自定义切面**==
>
>以下名词需要了解下
>
>- 横切关注点：跨越应用程序多个模块的方法或功能。即是，与我们业务逻辑无关的，但是我们需要关注的部分，就是横切关注点。如日志 , 安全 , 缓存 , 事务等等 ....
>- 切面（ASPECT）：横切关注点 被模块化 的特殊对象。即，它是一个类。
>- 通知（Advice）：切面必须要完成的工作。即，它是类中的一个方法。
>- 目标（Target）：被通知对象。
>- 代理（Proxy）：向目标对象应用通知之后创建的对象。
>- 切入点（PointCut）：切面通知 执行的 “地点”的定义。
>- 连接点（JointPoint）：与切入点匹配的执行点。

![image-20210226114748799](/Users/langli/Library/Application Support/typora-user-images/image-20210226114748799.png)

SpringAOP中，通过Advice定义横切逻辑，Spring中支持5种类型的Advice:

![image-20210226115005961](/Users/langli/Library/Application Support/typora-user-images/image-20210226115005961.png)

即 Aop 在 不改变原有代码的情况下 , 去增加新的功能 .

### 2.使用Spring实现Aop

==使用AOP织入，需要导入一个依赖包==

```xml
<!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
<dependency>
   <groupId>org.aspectj</groupId>
   <artifactId>aspectjweaver</artifactId>
   <version>1.9.4</version>
</dependency>
```

##### 方式一：Spring API实现

接口

```java
public interface UserDao {
    public void add();
    public void update();
    public void select();
    public void delete();
}
```

实现类

```java
@Component
public class UserService implements UserDao {

    @Override
    public void add() {
        System.out.println("新增用户");
    }

    @Override
    public void update() {
        System.out.println("更新用户");
    }

    @Override
    public void select() {
        System.out.println("查询用户");
    }

    @Override
    public void delete() {
        System.out.println("删除用户");
    }
}
```

增强类 ：一个前置增强 一个后置增强

```java
@Component
public class LogAfter implements AfterReturningAdvice {
    //returnValue 返回值
    //method被调用的方法
    //args 被调用的方法的对象的参数
    //target 被调用的目标对象
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        System.out.println(target.getClass().getName() + "的" + method.getName() + "执行完毕！");
    }
}
```

```java
@Component
public class LogBefore implements MethodBeforeAdvice {

    //method : 要执行的目标对象的方法
    //objects : 被调用的方法的参数
    //Object : 目标对象
    @Override
    public void before(Method method, Object[] arg, Object target) throws Throwable {
        System.out.println(target.getClass().getName() + "的" + method.getName() + "准备开始执行了！");
    }
}
```

最后去spring的文件中注册 , 并实现aop切入实现 , 注意导入aop约束 .

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">
    <!--使用注解注册bean-->
    <context:annotation-config></context:annotation-config>
    <!--扫描指定包下的注解-->
    <context:component-scan base-package="com"/>

    <!--方式一：通过Spring API实现-->
    <!--aop配置-->
    <aop:config>
        <!--切入点 expression:表达式匹配要执行的方法-->
        <aop:pointcut id="pointcut" expression="execution(* com.service.UserService.*(..))"/>
        <!--执行环绕; advice-ref执行方法 . pointcut-ref切入点-->
        <aop:advisor advice-ref="logBefore" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="logAfter" pointcut-ref="pointcut"/>
    </aop:config>
</beans>
```

测试类

```java
public class AopTest {
    @Test
    public void test1(){
       ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
       //代理（Proxy）：向目标对象应用通知之后创建的对象。
       UserDao userDao = context.getBean("userService", UserDao.class);
        userDao.add();
    }
}
```

##### 方式二：自定义类实现AOP

1.自定义类

```java
@Component
public class DiyLog {
    public void methodBefore() {
        System.out.println("方式执行前");
    }

    public void methodAfter() {
        System.out.println("方法执行后");
    }
}
```

2.Spring配置文件中增加aop配置

```xml
<!--第二种方式：使用AOP的标签实现-->
<aop:config>
  <!--切面-->
  <aop:aspect ref="diyLog">
    <aop:pointcut id="pointcut" expression="execution(* com.service.UserService.*(..))"/>
    <aop:before method="methodBefore" pointcut-ref="pointcut"/>
    <aop:after-returning method="methodAfter" pointcut-ref="pointcut"/>
  </aop:aspect>
</aop:config>
```

##### 方式三：使用注解实现

1.编写注解实现的增强类，使用@Component将类注册到bean

```java
@Component
@Aspect //定义切面就是annotationPointcut
//切面（ASPECT）：横切关注点 被模块化 的特殊对象。即，它是一个类。
public class AnnotationPointcut {

    //目标（Target）：UserService，被通知对象。
    //切入点（PointCut）：切面通知 执行的 “地点”的定义。
    @Before("execution(* com.service.UserService.*(..))")
    //通知（Advice）：切面必须要完成的工作。即，它是类中的一个方法。
    public void methodBefore() {
        System.out.println("方法执行前");
    }

    @AfterReturning("execution(* com.service.UserService.*(..))")
    public void methodAfter() {
        System.out.println("方法执行后");
    }
}
```

2.在Spring配置文件中，增加支持注解的配置

```xml
<!--第三种方式：注解实现-->
<aop:aspectj-autoproxy proxy-target-class="true"></aop:aspectj-autoproxy>
<!-- proxy-target-class属性：默认为false，表示使用jdk动态代理织入增强，true：表示使用CGLib动态代理技术织入增强-->
```

>aop:aspectj-autoproxy：说明
>
>```
>通过aop命名空间的<aop:aspectj-autoproxy />声明自动为spring容器中那些配置@aspectJ切面的bean创建代理，织入切面。当然，spring 在内部依旧采用AnnotationAwareAspectJAutoProxyCreator进行自动代理的创建工作，但具体实现的细节已经被<aop:aspectj-autoproxy />隐藏起来了
>
><aop:aspectj-autoproxy />有一个proxy-target-class属性，默认为false，表示使用jdk动态代理织入增强，当配为<aop:aspectj-autoproxy  poxy-target-class="true"/>时，表示使用CGLib动态代理技术织入增强。不过即使proxy-target-class设置为false，如果目标类没有声明接口，则spring将自动使用CGLib动态代理。
>```



# Mybatis-spring

### 1.mybatis回顾

- 工具类

  ```java
  public class MybatisUtil {
  
      private static InputStream inputStream;
      private static SqlSessionFactory sqlSessionFactory;
  
      static {
          try {
              String resource = "mybatis-config.xml";
              inputStream = Resources.getResourceAsStream(resource);
              sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
  
      public static SqlSession getSqlSession() {
          return sqlSessionFactory.openSession();
      }
  }
  ```

- 实体类 

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String pwd;
}
```

- 编写mybatis核心配置文件

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <!--configuration核心配置文件-->
  <configuration>
      <properties resource="db.properties"></properties>
      <typeAliases>
          <typeAlias type="com.pojo.User" alias="user"/>
      </typeAliases>
      <environments default="development">
          <environment id="development">
              <transactionManager type="JDBC"/>
              <dataSource type="POOLED">
                  <property name="driver" value="${driver}"/>
                  <property name="url" value="${url}"/>
                  <property name="username" value="${username}"/>
                  <property name="password" value="${password}"/>
              </dataSource>
          </environment>
      </environments>
      <mappers>
          <mapper class="com.mapper.UserMapper"/>
      </mappers>
  </configuration>
  ```

- UserMapper接口&userMapper.xml

  ```java
  public interface UserMapper {
      List<User> selectUser();
  }
  ```

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <!--命名空间（Namespaces）绑定一个Dao/Mapper接口-->
  <mapper namespace="com.mapper.UserMapper">
      <select id="selectUser" resultType="user">
          select * from user
      </select>
  </mapper>
  ```

- 测试

  ```java
  public class ReviewMybatis {
      @Test
      public void test() {
          SqlSession sqlSession = MybatisUtil.getSqlSession();
          UserMapper mapper = sqlSession.getMapper(UserMapper.class);
          List<User> users = mapper.selectUser();
          for (User user : users) {
              System.out.println(user);
          }
      }
  }
  ```

### 2.mybaits-spring

[mybaits-spring官网](https://mybatis.org/spring/zh/)

**入门**

1. 导包

   ```xml
   <dependency>
     <groupId>org.mybatis</groupId>
     <artifactId>mybatis-spring</artifactId>
     <version>2.0.6</version>
   </dependency>
   ```

2. 参照mybatis工具类，spring使用mybaits需要以下三步

   - 加载resource

     ```java
     //mybatis代码实现
     String resource = "mybatis-config.xml";
     inputStream = Resources.getResourceAsStream(resource);
     ```

     ```xml
     <!--使用spring配置mybatis的数据源，原来这部分配置是写在mybatis-config.xml中-->
     <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
       <!-- <property name="driverClassName" value="com.mysql.jdbc.Driver"/>-->
       <property name="username" value="${username}"/>
       <property name="password" value="${password}"/>
       <property name="url" value="${url}"/>
     </bean>
     ```

     ```xml
      <!-- spring中使用properties配置-->
      <context:property-placeholder ignore-unresolvable="true" location="classpath:db.properties"/>
     ```

   - 用SqlSessionFactoryBuilder创建sqlSessionFactory

     ```java
     //mybatis代码实现
     sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
     ```

     ```xml
     <!--配置sqlSessionFactory-->
     <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
         <property name="dataSource" ref="dataSource"/>
         <!--关联mybatis配置-->
         <property name="configLocation" value="classpath:mybatis-config.xml"/>
         <!--导入mapper.xml, 等价于mybatis配置中：<mappers>-->
         <property name="mapperLocations" value="classpath:com/mapper/*.xml"/>
     </bean>
     ```

   - 获取sqlSession

     - **方式一 ：SqlSessionTemplate**
     
     ```java
     //mybatis代码实现
     public static SqlSession getSqlSession() {
       return sqlSessionFactory.openSession();
   }
     ```
     
     ```xml
     <!--注册sqlSessionTemplate , 关联sqlSessionFactory-->
     <bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
         <!--利用构造器注入 因为该类没有set方法-->
         <constructor-arg index="0" ref="sqlSessionFactory"/>
     </bean>
     ```

   配置完成后，需要增加一个接口实现类并注册到bean

   ```java
   @Component
   public class UserDaoImpl implements UserMapper {
   
     //sqlSession不用我们自己创建了，Spring来管理  
      private SqlSession sqlSession;
   
       @Autowired
       public void setSqlSessionTemplate(SqlSession sqlSession) {
           this.sqlSession = sqlSession;
       }
   
       @Override
       public List<User> selectUser() {
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
           List<User> users = mapper.selectUser();
           return users;
       }
   }
   ```

   - **方式二 ：SqlSessionDaoSupport**

      ```java
//继承SqlSessionDaoSupport实现，底层其实也是使用SqlSessionTemplete
//SqlSessionDaoSupport 需要两个参数：setSqlSessionFactory or SqlSessionTemplete
@Component
public class UserDaoImpl2 extends SqlSessionDaoSupport implements UserMapper {

    @Override
    @Autowired //自动装配setSqlSessionFactory
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @Override
    public List<User> selectUser() {
        return super.getSqlSession().getMapper(UserMapper.class).selectUser();
    }
}
      ```

​    测试类

```java
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
```



# Spring事务

**事务：**

- 要么都成功，要么都失败
- 十分重要，涉及到数据一致性
- 确保完整性和一致性

**事务的ACID原则:**

- 原子性
- 一致性
- 隔离性
  - 多个业务可能操作一个资源，防止数据损坏
- 持久性
  - 事务一旦提交，无论系统发生什么问题，结果都不会被影响。



Spring中的事务管理

- 声明式事务
- 编程式事务

### 声明式事务

1. mapper

   ```java
   
   public interface UserMapper {
       List<User> selectUser();
   
       int insert(User user);
   
       int delete(@Param("id") int id);
   }
   ```

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE mapper
           PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   <!--命名空间（Namespaces）绑定一个Dao/Mapper接口-->
   <mapper namespace="com.mapper.UserMapper">
       <select id="selectUser" resultType="user">
           select * from user
       </select>
       <insert id="insert" parameterType="user">
           insert into mybatis.user (name ,pwd) values (#{name},#{pwd})
       </insert>
       <delete id="delete" parameterType="int">
           delete from user where id = #{id}
       </delete>
   </mapper>
   ```

2. 接口实现类

   ```java
   @Component
   public class UserDaoImpl2 extends SqlSessionDaoSupport implements UserMapper {
   
       @Override
       @Autowired
       public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
           super.setSqlSessionFactory(sqlSessionFactory);
       }
   
       @Override
       public List<User> selectUser() {
           User user = new User("黄黄", "ererer");
           insert(user);
           delete(9);
           return super.getSqlSession().getMapper(UserMapper.class).selectUser();
       }
   
       @Override
       public int insert(User user) {
           return super.getSqlSession().getMapper(UserMapper.class).insert(user);
       }
   
       @Override
       public int delete(int id) {
           return super.getSqlSession().getMapper(UserMapper.class).delete(id);
       }
   }
   ```

   

3. Spring xml配置

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xmlns:context="http://www.springframework.org/schema/context"
          xmlns:tx="http://www.springframework.org/schema/tx" xmlns:aop="http://www.springframework.org/schema/aop"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/context
           https://www.springframework.org/schema/context/spring-context.xsd
           http://www.springframework.org/schema/tx
           http://www.springframework.org/schema/tx/spring-tx.xsd
           http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd">
       <context:annotation-config></context:annotation-config>
   
       <context:component-scan base-package="com"></context:component-scan>
   
       <!-- spring中使用properties配置-->
       <context:property-placeholder ignore-unresolvable="true" location="classpath:db.properties"/>
   
       <!--配置sqlSessionFactory-->
       <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
           <property name="dataSource" ref="dataSource"/>
           <!--关联mybatis配置-->
           <property name="configLocation" value="classpath:mybatis-config.xml"/>
           <!--导入mapper.xml, 等价于mybatis配置中：<mappers>-->
           <property name="mapperLocations" value="classpath:com/mapper/*.xml"/>
       </bean>
   
       <!--注册sqlSessionTemplate , 关联sqlSessionFactory-->
       <bean id="sqlSessionTemplate" class="org.mybatis.spring.SqlSessionTemplate">
           <!--利用构造器注入 因为该类没有set方法-->
           <constructor-arg index="0" ref="sqlSessionFactory"/>
       </bean>
   
       <!--dataSource:使用spring的数据源替代mybatis的配置，c3p0 dbcp druid
           这里使用spring提供的jdbc:org.springframework.jdbc
       -->
       <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
           <!-- <property name="driverClassName" value="com.mysql.jdbc.Driver"/>-->
           <property name="username" value="${username}"/>
           <property name="password" value="${password}"/>
           <property name="url" value="${url}"/>
       </bean>
   
       <!-- 声明式事务-->
       <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
           <constructor-arg ref="dataSource"/>
       </bean>
   
       <!-- 结合aop实现事务织入-->
       <tx:advice id="tx1" transaction-manager="transactionManager">
           <!--给哪些方法配置事务-->
           <!--配置事务的传播特性-->
           <tx:attributes>
               <tx:method name="add" propagation="REQUIRED"/>
               <tx:method name="delete" propagation="REQUIRED"/>
               <!--给所有方法配置事务-->
               <tx:method name="*" propagation="REQUIRED"/>
           </tx:attributes>
       </tx:advice>
   
       <!--配置事务切入-->
       <aop:config>
           <aop:pointcut id="txPointcut" expression="execution(* com.mapper.*.*(..))"/>
           <aop:advisor advice-ref="tx1" pointcut-ref="txPointcut"></aop:advisor>
       </aop:config>
   </beans>
   ```

测试总结：

- 未配置事务时，当有sql报错，执行selectUser()方法，有新数据插入
- 配置事务后，当有sql报错，执行selectUser()方法，没有新数据插入



