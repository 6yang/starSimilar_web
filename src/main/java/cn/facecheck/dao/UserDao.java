package cn.facecheck.dao;

import cn.facecheck.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    @Insert("insert into user(username,password,email) values(#{username},#{password},#{email})")
    void registerUser(User user);


    @Select("select * from user where username = #{username} and password = #{password}")
    User findUser(User user);
}
