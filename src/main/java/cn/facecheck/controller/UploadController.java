package cn.facecheck.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/***
 * @ClassName: UploadController
 * @Description:
 * @Auther: 6yang
 * @Date: 2019/11/311:29
 * @version : V1.0
 */
@RestController
@RequestMapping("/file")
public class UploadController {

    @RequestMapping(value="/upload",method= RequestMethod.POST)
    public String upload(HttpServletRequest request,
                         @RequestParam("title") String description,
                         @RequestParam("imgFile") MultipartFile file) throws Exception {

        System.out.println(description);
        //如果文件不为空，写入上传路径
        if (!file.isEmpty()) {
            //上传文件路径
//            String path = request.getServletContext().getRealPath("/images/");
            String path = "F:\\androidapp\\face\\images";
            //上传文件名
            String filename = file.getOriginalFilename();

            File filepath = new File(path, filename);
            //判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            file.transferTo(new File(path + File.separator + filename));
            return "success";
        } else {
            return "error";
        }
    }

}
