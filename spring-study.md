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

#别名
一、
