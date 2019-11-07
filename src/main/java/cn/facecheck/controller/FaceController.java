package cn.facecheck.controller;

import cn.facecheck.entity.Msg;
import cn.facecheck.entity.Star;
import cn.facecheck.entity.StarMsg;
import cn.facecheck.service.FaceService;
import cn.facecheck.utils.FaceCheckUtil;
import cn.facecheck.utils.HttpUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/***
 * @ClassName: FaceController
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/79:23
 * @version : V1.0
 */

@RestController
@RequestMapping("/face")
public class FaceController {

    @Autowired
    private FaceService faceService;

    @RequestMapping("/add")
    public String faceAdd() throws Exception {

        String url = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php";
        String param  = "resource_id=28266&from_mid=1&format=json&ie=utf-8&oe=utf-8&query=%E6%BC%94%E5%91%98&sort_key=&sort_type=1&stat0=%E5%A5%B3&stat1=%E5%86%85%E5%9C%B0&stat2=&stat3=&cb=jQuery1102020975468826603172_1573038305834&_=1573038305841";

        for (int j = 0; j < 20; j++) {
            String para = "&pn=" + j * 50 + "&rn=50";
            String res = HttpUtil.sendGet(url, param + para);
            String json = res.split("\\(")[1].split("\\)")[0];
            StarMsg starMsg = new Gson().fromJson(json, StarMsg.class);
            String display_title = starMsg.data.get(0).display_title;
//            System.out.println(json);
            for (int i = 0; i < starMsg.data.get(0).actor.size(); i++) {
                StarMsg.Data.Actor actor = starMsg.data.get(0).actor.get(i);
//                System.out.println(actor);
                String imgUrl = actor.pic_4n_78;
                String name = actor.ename;
                String groupId = "star_female_neidi";
                String uuid = UUID.randomUUID().toString().replace("-", "_");
                Thread.sleep(150);
                String result = FaceCheckUtil.faceAddTest(imgUrl, groupId, uuid, name);
                Msg msg = new Gson().fromJson(result, Msg.class);
                if("SUCCESS".equals(msg.error_msg)){
                    Star star = new Star();
                    star.setGroupId(groupId);
                    star.setGroup_name(display_title);
                    star.setStarInfo(name);
                    star.setStarId(uuid);
                    star.setFace_taken(msg.result.face_token);
                    star.setImg_url(imgUrl);
                    faceService.addFace(star);

                }else{
                    System.out.println(actor.ename ="上传失败");
                }


            }
        }



        return "上传结束";

    }
}
