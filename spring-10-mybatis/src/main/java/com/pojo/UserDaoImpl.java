package com.pojo;

import com.mapper.UserMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserMapper {

    //sqlSession不用我们自己创建了，Spring来管理
    private SqlSessionTemplate sqlSession;


    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public UserMapper getMapper() {
        return this.sqlSession.getMapper(UserMapper.class);
    }

    @Override
    public List<User> selectUser() {
        UserMapper mapper = getMapper();
        List<User> users = mapper.selectUser();
        return users;
    }

}
