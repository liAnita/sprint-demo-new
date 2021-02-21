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