# IOC创建对象的方式
1. 使用无参构造函数，默认。
```xml
<!--默认无参-->
    <bean id="user" class="com.srz.pojo.User">
        <property name="name" value="超级大帅哥"></property>
    </bean>
```
2. 使用无参构造函数，默认。
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
总结：在配置文件加载的时候，容器中的对象就已经初始化了

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

