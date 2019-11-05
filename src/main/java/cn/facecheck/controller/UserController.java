package cn.facecheck.controller;

import cn.facecheck.entity.Msg;
import cn.facecheck.entity.SearchOut;
import cn.facecheck.entity.Star;
import cn.facecheck.entity.User;
import cn.facecheck.service.StarService;
import cn.facecheck.service.UserService;
import cn.facecheck.utils.FaceCheckUtil;
import cn.facecheck.utils.FileUtil;
import cn.facecheck.utils.GsonUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import java.io.File;
import java.util.UUID;

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

    @Autowired
    private UserService userService;

    @Autowired
    private StarService starService;

    /*
    * 用户注册
    * */
    @RequestMapping("/register")
    public String register(User user){

        userService.registerUser(user);

        return "注册成功";
    }

    /*
    * 用户登录
    * */
    @RequestMapping("/login")
    public String login(User user){
        User login = userService.findUser(user);
        if(login == null){
            return "fail";
        }else{
            return "success";
        }
    }

    @RequestMapping(value="/searchSimilar",method= RequestMethod.POST)
    public String searchSimilar(HttpServletRequest request,
                          @RequestParam("imgFile") MultipartFile file,
                          @RequestParam("username") String username,
                                @RequestParam("starGroup") String groupId) throws Exception {

        String uuid = UUID.randomUUID().toString().replace("-","_");
        String realPath = FileUtil.saveFile(file,uuid);
        String imgHttpUrl =request.getRequestURL().toString()
                .replace(request.getRequestURI().toString(),
                        "/face/images/" + uuid + ".jpg");

        System.out.println(realPath);
        SearchOut searchOut = FaceCheckUtil.faceSearch(realPath, groupId, 0);
        if("success".equals(searchOut.getSuccess_tag())){
            Star star =  starService.findStarImgUrl(searchOut.getUser_id());
            searchOut.setImg_url(star.getImg_url());
        }
        System.out.println(GsonUtils.toJson(searchOut));
        return GsonUtils.toJson(searchOut);
//        return  "上传成功";
    }

}
