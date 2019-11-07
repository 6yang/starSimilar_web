import cn.facecheck.entity.Msg;
import cn.facecheck.entity.StarMsg;
import cn.facecheck.utils.FaceCheckUtil;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/***
 * @ClassName: testHttpGet
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/619:41
 * @version : V1.0
 */

public class testHttpGet {


    String url = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php";
    String param  = "resource_id=28266&from_mid=1&format=json&ie=utf-8&oe=utf-8&query=%E6%BC%94%E5%91%98&sort_key=&sort_type=1&stat0=%E7%94%B7&stat1=%E5%86%85%E5%9C%B0&stat2=&stat3=&cb=jQuery1102020975468826603172_1573038305834&_=1573038305846";

    /*
    * 删除人脸库中所有的人脸
    * */
    @Test
    public void delete() {
        FaceCheckUtil.groupDelete("riben");
    }

    @Test
    public void testGet() throws Exception {

        for (int j = 0; j < 1; j++) {

            String para = "&pn=" + j * 50 + "&rn=10";

            String res = sendGet(url, param + para);
//            System.out.println(res);
            String json = res.split("\\(")[1].split("\\)")[0];
            StarMsg starMsg = new Gson().fromJson(json, StarMsg.class);
            System.out.println(json);
            for (int i = 0; i < starMsg.data.get(0).actor.size(); i++) {
                StarMsg.Data.Actor actor = starMsg.data.get(0).actor.get(i);
                System.out.println(actor);
                String imgUrl = actor.pic_4n_78;
                String name = actor.ename;
                String groupId = "test";
                String uuid = UUID.randomUUID().toString().replace("-", "_");
//                Thread.sleep(150);
                String result = FaceCheckUtil.faceAddTest(imgUrl, groupId, uuid, name);
                Msg msg = new Gson().fromJson(result, Msg.class);
                if("SUCCESS".equals(msg.error_msg)){
                    System.out.println(actor.ename + "上传成功" + msg.result.face_token);
                }else{
                    System.out.println(actor.ename ="上传失败");
                }


            }
        }




    }

    @Test
    public void getACC() {
        String accessToken = FaceCheckUtil.getAccessToken(FaceCheckUtil.APIKEY, FaceCheckUtil.SECRETKEY);
        System.out.println(accessToken);
    }

    public static String sendGet(String url, String param) throws Exception {

        String result = "";
        BufferedReader in = null;

        try{
            String request = url + "?" + param;
            //打开和URL之间的连接
            URLConnection connection = new URL(request).openConnection();
            /* begin获取响应码 */
            HttpURLConnection httpUrlConnection = (HttpURLConnection)connection;
            httpUrlConnection.setConnectTimeout(300000);
            httpUrlConnection.setReadTimeout(300000);
            httpUrlConnection.connect();

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                result +=  inputLine;

        }catch (Exception e){
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }
}
