package cn.facecheck.controller;

import cn.facecheck.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/***
 * @ClassName: UserController
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/213:52
 * @version : V1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @RequestMapping(value="/register",method= RequestMethod.POST)
    public String register(User user){
        return user.toString();
    }
}
