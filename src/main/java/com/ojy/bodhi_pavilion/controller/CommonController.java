package com.ojy.bodhi_pavilion.controller;

import com.ojy.bodhi_pavilion.util.GetId;
import com.ojy.bodhi_pavilion.util.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/common")
public class CommonController {

    /**
     * 下载图片
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response) {
        OutputStream out = null;
        InputStream is = null;
        if (name != null) {
            try {
                File file = new File("E:\\JavaProject\\copy-demo\\bodhi_pavilion\\src\\main\\resources\\images\\" + name);
                out = response.getOutputStream();
                is = new FileInputStream(file);

                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * 上传图片
     * @param multipartFile
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public Result upload(@RequestPart("file") MultipartFile multipartFile) {
        String id = GetId.getId();
        String filename = id.concat(multipartFile.getOriginalFilename());
        File file = new File("E:\\JavaProject\\copy-demo\\bodhi_pavilion\\src\\main\\resources\\images\\" + filename);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(filename);
    }

}
