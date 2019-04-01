package com.winterchen.controller;



import com.winterchen.util.FileUploadUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: liuzipan
 * @Description
 * @Date :20:05 2019/2/21
 * @Modefied By:
 */
@RestController
@RequestMapping("/Advert")
public class AdvertController {
    @PostMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile file){
        String fileName = FileUploadUtil.writeUploadFile(file, "advert");
        System.out.println(fileName);
          return fileName;
    }
}
