import cn.facecheck.entity.Msg;
import com.google.gson.Gson;
import org.junit.Test;

import javax.sound.midi.Soundbank;

/***
 * @ClassName: GsonTest
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/510:20
 * @version : V1.0
 */
public class GsonTest {

    @Test
    public void testGson() {
        String  json = "{\"error_code\":0,\"error_msg\":\"SUCCESS\",\"log_id\":7584897975796,\"timestamp\":1572919626,\"cached\":0,\"result\":{\"face_token\":\"883d4e8ab8e35fab9b229051fe7b1786\",\"user_list\":[{\"group_id\":\"star_woman_asia\",\"user_id\":\"12b27710_a050_4ca4_8292_cc60a57296c3\",\"user_info\":\"\\u5468\\u7b14\\u7545\",\"score\":55.346000671387}]}}";
        String json1 = "{\"error_code\":222202,\"error_msg\":\"pic not has face\",\"log_id\":8445554510175,\"timestamp\":1572915166,\"cached\":0,\"result\":null}";
        String json2 = "{\"error_code\":223114,\"error_msg\":\"face is fuzzy\",\"log_id\":6575797989201,\"timestamp\":1572917295,\"cached\":0,\"result\":null}";


        Msg msg = new Gson().fromJson(json2, Msg.class);

        System.out.println(msg.toString());
        System.out.println(msg.error_msg);
//        System.out.println(msg.result.face_token);
//        System.out.println(msg.result.user_list.get(0).group_id);
//        System.out.println(msg.result.user_list.get(0).user_id);
//        System.out.println(msg.result.user_list.get(0).user_info);
//        System.out.println(msg.result.user_list.get(0).score);


    }
}
