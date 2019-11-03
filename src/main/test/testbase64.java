import org.junit.Test;

import java.io.*;
import java.util.Base64;

/***
 * @ClassName: testbase64
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/312:19
 * @version : V1.0
 */
public class testbase64 {

    @Test
    public void name() throws IOException {
        InputStream inputStream=new FileInputStream("F:\\androidapp\\face\\images\\1572754683842.jpg");

        //文件读入缓存并编码
        byte[] buf=new byte[inputStream.available()];
        inputStream.read(buf);
        //编码
        String s=new String(Base64.getEncoder().encode(buf));
        System.out.println(s);
    }
}
