package com.pojo;

import com.mapper.UserMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
