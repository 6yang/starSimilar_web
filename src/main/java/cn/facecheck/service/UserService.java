package cn.facecheck.service;

import cn.facecheck.entity.User;

public interface UserService {

    void registerUser(User user);

    User findUser(User user);
}
