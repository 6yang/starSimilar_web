package cn.facecheck.controller;

import cn.facecheck.entity.Star;
import cn.facecheck.service.StarService;
import cn.facecheck.utils.FaceCheckUtil;
import cn.facecheck.utils.FileUtil;
import cn.facecheck.utils.GsonUtils;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.UUID;


/***
 * @ClassName: StarController
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/49:19
 * @version : V1.0
 */
@RestController
@RequestMapping("/star")
public class StarController {

    @Autowired
    private StarService starServiceDao;

    @RequestMapping("findById")
    public String findById(@RequestParam(name = "id",required = true) String starId, HttpServletResponse response) throws IOException {
        Star star = starServiceDao.findById(Integer.parseInt(starId));
        return GsonUtils.toJson(star);
    }

    @RequestMapping(value="/addStar",method= RequestMethod.POST)
    public String addStar(HttpServletRequest request,
                         @RequestParam("starName") String starInfo,
                         @RequestParam("imgFile") MultipartFile file,
                         @RequestParam("starGroup") String groupId) throws Exception {
        /*
         * 组id
         * star_info
         * face_token
         * image_url
         * */

        String uuid = UUID.randomUUID().toString().replace("-","_");
        String realPath = FileUtil.saveFile(file,uuid);
        String face_token = FaceCheckUtil.faceAdd(realPath, groupId, uuid, starInfo);
        String imgHttpUrl =request.getRequestURL().toString()
                .replace(request.getRequestURI().toString(),
                        "/face/images/" + uuid + ".jpg");
        if(face_token ==null){
            File err_file = new File(realPath);
            err_file.delete();
            return "上传失败";
        }else{
            starServiceDao.insertStarFace(groupId,starInfo,face_token,imgHttpUrl,uuid);
            return "上传成功";
        }

    }

}
