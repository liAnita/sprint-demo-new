package com.mapper;

import com.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserMapper {
    List<User> selectUser();

//    int insert(HashMap map);
    int insert(User user);

    int delete(@Param("id") int id);
}
