package cn.facecheck.service.impl;

import cn.facecheck.dao.UserDao;
import cn.facecheck.entity.User;
import cn.facecheck.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 * @ClassName: UserServiceImpl
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/415:47
 * @version : V1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void registerUser(User user) {
        userDao.registerUser(user);
    }

    @Override
    public User findUser(User user) {
        return userDao.findUser(user);
    }

}
