package cn.facecheck.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.facecheck.entity.Msg;
import cn.facecheck.entity.SearchOut;
import com.google.gson.Gson;
import org.apache.ibatis.annotations.Insert;
import org.apache.taglibs.standard.tag.common.fmt.RequestEncodingSupport;
import org.json.JSONObject;

/***
 * @ClassName: FaceCheckUtil
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/315:56
 * @version : V1.0
 */
public class FaceCheckUtil {

    public final static String APIKEY = "8QDvA6eIqUzKfP51bHuq9KiG";

    public final static String SECRETKEY ="dso2mPmTGLFLb4E0pw7pYkhpX58xITeL";

    public final static String AccessToken ="24.286950606e08dfefc610091f41507cc5.2592000.1575636077.282335-17676403";


    /*
    * 从人脸库中搜集人脸
    * */
    public static SearchOut faceSearch(String filePath,String faceGroup,Integer max_user_num){

        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";



        String base64Code = ImageToBase64Util.imageToBase64(filePath);

        String male = "star_male_neidi,star_male_taiwan,star_male_hongkong,star_male_riben,star_male_hanguo,star_male_oumei";
        String female = "star_female_neidi,star_female_taiwan,star_female_hongkong,star_female_riben,star_female_hanguo,star_female_oumei";

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", base64Code);
            map.put("liveness_control", "NONE");
            if("male".equals(faceGroup)){
                map.put("group_id_list", male);
            }else if("female".equals(faceGroup)){
                map.put("group_id_list", female);
            }
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");
            if(max_user_num !=0){
                map.put("max_user_num",max_user_num);
            }

            String param = GsonUtils.toJson(map);

            String accessToken = getAccessToken(APIKEY,SECRETKEY);

            String result = HttpUtil.post(url, accessToken, "application/json", param);

            System.out.println("请求结果:"+result);
            Msg msg = new Gson().fromJson(result, Msg.class);

            SearchOut searchOut = new SearchOut();
            if(!"SUCCESS".equals(msg.error_msg)){
                //不成功就把文件删除掉
                searchOut.setSuccess_tag("fail");
                File tmpFile = new File(filePath);
                tmpFile.delete();
            }else {
                searchOut.setFace_token(msg.result.face_token);
                searchOut.setGroup_id(msg.result.user_list.get(0).group_id);
                searchOut.setUser_id(msg.result.user_list.get(0).user_id);
                searchOut.setUser_info(msg.result.user_list.get(0).user_info);
                searchOut.setScore(msg.result.user_list.get(0).score);
                searchOut.setSuccess_tag("success");
            }
            return searchOut;

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
            map.put("liveness_control", "NONE");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");
            String param = GsonUtils.toJson(map);
            String accessToken = getAccessToken(APIKEY,SECRETKEY);
            String result = HttpUtil.post(url, accessToken, "application/json", param);
            JSONObject j1 = new JSONObject(result);
            JSONObject j2 = j1.getJSONObject("result");
            if(j2 != null){
                String face_token = j2.getString("face_token");
                return face_token;
            }else{
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /*
    *
    * 人脸添加测试
    * */
    public static String faceAddTest(String imgUrl,String groupId,String userId,String userInfo) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";


        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", imgUrl);
            map.put("group_id", groupId);
            map.put("user_id", userId);
            map.put("user_info", userInfo);
            map.put("liveness_control", "NONE");
            map.put("image_type", "URL");
            map.put("quality_control", "LOW");
            String param = GsonUtils.toJson(map);
            String accessToken = AccessToken;
            String result = HttpUtil.post(url, accessToken, "application/json", param);
//            JSONObject j1 = new JSONObject(result);
//            JSONObject j2 = j1.getJSONObject("result");
            if(result != null){
//                String face_token = j2.getString("face_token");
                return result;
            }else{
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    * 人脸删除
    * */
    public static String groupDelete(String groupId) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/group/delete";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("group_id", groupId);

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AccessToken;
            String result = HttpUtil.post(url, accessToken, "application/json", param);
//            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
