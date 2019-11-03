import cn.facecheck.utils.Base64Util;
import cn.facecheck.utils.FaceCheckUtil;
import cn.facecheck.utils.GsonUtils;
import cn.facecheck.utils.HttpUtil;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/***
 * @ClassName: FacesearchTest
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/314:50
 * @version : V1.0
 */
public class FacesearchTest {

    @Test
    public void faceSearch() throws IOException {
        File file = new File("F:\\androidapp\\face\\images\\tongliya.jpg");
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        String encode = Base64Util.encode(bytes);

        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", encode);
            map.put("liveness_control", "NORMAL");
            map.put("group_id_list", "star_woman_asia");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");
            String param = GsonUtils.toJson(map);
//            System.out.println(param);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.955d1bc29598b059e13c5f25419082dc.2592000.1575357016.282335-17676403";

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testGetToken() {
        String s = FaceCheckUtil.getAccessToken("8QDvA6eIqUzKfP51bHuq9KiG", "dso2mPmTGLFLb4E0pw7pYkhpX58xITeL");
        System.out.println(s);
    }


    @Test
    public void testaddFace(){
        String filePath = "F:\\androidapp\\face\\images\\tongliya.jpg";
        String s = FaceCheckUtil.faceAdd(filePath, "star_woman_asia", "tongliya", "佟丽娅");
        System.out.println(s);
    }
}
