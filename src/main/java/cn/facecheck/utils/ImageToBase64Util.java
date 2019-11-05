package cn.facecheck.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/***
 * @ClassName: ImageToBase64Util
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/316:40
 * @version : V1.0
 */
public class ImageToBase64Util {

    public static String imageToBase64(String filePath){
        FileInputStream fis = null;
        byte[] bytes = null;
        try {
            fis = new FileInputStream(filePath);
            bytes = new byte[fis.available()];
            fis.read(bytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String encode = Base64Util.encode(bytes);
        return encode;
    }

}
