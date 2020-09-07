package com.example.wechaterweima.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @Author: meihao
 * @CreateDate: 2020/9/1 15:46
 */

@CrossOrigin(origins = "https://www.eecup.cn", maxAge = 3600)
@RestController
@Slf4j
public class PdfDownloadController {

    @Value("${file.location}")
    private String fileLocation;

    @Value("${check.code}")
    private String checkCode;

    @GetMapping("/download")
    public String  testDownload(HttpServletResponse response , Model model, String id) {
        log.info("文件下载，id： " + id);
        String fileName;
        if (StringUtils.isEmpty(id)){
            fileName = checkCode + "(0).pdf";
            response.setHeader("content-type", "application/pdf");
            response.setHeader("Content-disposition",checkCode + "(0)");
            response.setHeader("is-pdf","true");
        }else{
            fileName = checkCode + ".zip";
            response.setHeader("content-type", "application/zip");
            response.setHeader("Content-disposition",checkCode);
            response.setHeader("is-pdf","false");
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(fileLocation + fileName )));
            int read = bis.read(buff);
            while (read != -1) {
                outputStream.write(buff, 0, buff.length);
                outputStream.flush();
                read = bis.read(buff);
            }
        } catch ( IOException e ) {
            e.printStackTrace();
            model.addAttribute("result","下载失败");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        model.addAttribute("result","下载成功");
        return "/download";
    }
}
