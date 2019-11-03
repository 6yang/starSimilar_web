package cn.facecheck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/find")
    public String find(){
        return "helloworld";
    }
}
