[狂神说笔记]

[狂神说笔记]: https://www.kuangstudy.com/bbs/1344884033053581313
#IOC理论推导
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

###IOC本质
```text
控制反转loC(Inversion of Control)，是一种设计思想，DI(依赖注入)是实现loC的一种方法，也有人认为DI只是loC的另一种说法。没有loC的程序中，我们使用面向对象编程，对象的创建与对象间的依赖关系完全硬编码在程序中，对象的创建由程序自己控制，控制反转后将对象的创建转移给第三方，个人认为所谓控制反转就是:获得依赖对象的方式反转了。
采用XML方式配置Bean的时候，Bean的定义信息是和实现分离的，而采用注解的方式可以把两者合为一体，Bean的定义信息直接以注解的形式定义在实现类中，从而达到了零配置的目的。
控制反转是一种通过描述(XML或注解）并通过第三方去生产或获取特定对象的方式在Spring中实现控制反转
的是loC容器，其实现方法是依赖注入(Dependency Injection,Dl)。
```

# IOC创建对象的方式
1. 使用无参构造函数，默认。
```xml
<!--默认无参-->
    <bean id="user" class="com.srz.pojo.User">
        <property name="name" value="超级大帅哥"></property>
    </bean>
```
2. 有参构造创建对象

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

#Spring的配置
###别名
```xml
<!--  别名  -->
<alias name="user" alias="u"></alias>
```
###Beam的配置
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
###Import
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

#依赖注入（DI）
###构造器注入
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
###Set方式注入(重点)
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

###扩展方式注入
```xml
 <!--使用p-namespace（properties）进行更简洁的 XML 配置 -->
    <bean id="user" class="com.study.pojo.User" p:name="易烊千玺" p:age="18"></bean>

    <!--使用c-namespace(construct-arg)进行更简洁的 XML 配置 -->
    <bean id="user2" class="com.study.pojo.User" c:name="四字弟弟" c:age="19"></bean>

```
>注意事项：
>1.需要在xml头部导入约束文件 2.c-namespace需要实体类包含有参构造
>```xml
>xmlns:p="http://www.springframework.org/schema/p"
>xmlns:c="http://www.springframework.org/schema/c"
>```

