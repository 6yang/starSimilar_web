package cn.facecheck.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/***
 * @ClassName: FaceCheckUtil
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/315:56
 * @version : V1.0
 */
public class FaceCheckUtil {

    private final static String APIKEY = "8QDvA6eIqUzKfP51bHuq9KiG";

    private final static String SECRETKEY ="dso2mPmTGLFLb4E0pw7pYkhpX58xITeL";



    public static String faceSearch(String filePath,String faceGroup){

        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";

        String base64Code = ImageToBase64Util.imageToBase64(filePath);
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", base64Code);
            map.put("liveness_control", "NORMAL");
            map.put("group_id_list", faceGroup);
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");

            String param = GsonUtils.toJson(map);


            String accessToken = getAccessToken(APIKEY,SECRETKEY);

            String result = HttpUtil.post(url, accessToken, "application/json", param);

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取API访问token
     * 该token有一定的有效期，需要自行管理，当失效时需重新获取.
     * @param ak - 百度云官网获取的 API Key
     * @param sk - 百度云官网获取的 Securet Key
     * @return assess_token 示例：
     * "24.460da4889caad24cccdb1fea17221975.2592000.1491995545.282335-1234567"
     */
    public static String getAccessToken(String ak, String sk) {
        // 获取token地址
        String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl = authHost
                // 1. grant_type为固定参数
                + "grant_type=client_credentials"
                // 2. 官网获取的 API Key
                + "&client_id=" + ak
                // 3. 官网获取的 Secret Key
                + "&client_secret=" + sk;
        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.err.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            /**
             * 返回结果示例
             */
            JSONObject jsonObject = new JSONObject(result);
            String access_token = jsonObject.getString("access_token");
            return access_token;
        } catch (Exception e) {
            System.err.printf("获取token失败！");
            e.printStackTrace(System.err);
        }
        return null;
    }

    /*
    * 向人脸库中添加人脸
    * */
    public static String faceAdd(String filePath,String groupId,String userId,String userInfo) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";

        String base64Code = ImageToBase64Util.imageToBase64(filePath);

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", base64Code);
            map.put("group_id", groupId);
            map.put("user_id", userId);
            map.put("user_info", userInfo);
            map.put("liveness_control", "NORMAL");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");
            String param = GsonUtils.toJson(map);
            String accessToken = getAccessToken(APIKEY,SECRETKEY);

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
