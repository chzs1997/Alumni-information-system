package com.winterchen.util;

import org.apache.commons.io.FilenameUtils;


import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;


/**
 * @Author: liuzipan
 * @Description
 * @Date :19:46 2019/2/21
 * @Modefied By:
 */
public class FileUploadUtil {
    /*
    * 文件上传处理
    */

    public static String writeUploadFile(MultipartFile file, String module){
        String fileName = file.getOriginalFilename();
        String realPath = "D:/software/"+module+"/";
        File fileDir = new File(realPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }

        String extName = FilenameUtils.getExtension(fileName);
        String allowImgFormat = "gif,jpg,jpeg,png";
        if(!allowImgFormat.contains(extName.toLowerCase())){
            return "NOT_IMAGE";
        }

        fileName = Math.abs(file.getOriginalFilename().hashCode()) + RandomUtils.createRandomString(4)+"."+extName;
        InputStream input = null;
        FileOutputStream fos = null;
        try{
            input = file.getInputStream();
            fos = new FileOutputStream(realPath+"/"+fileName);
            IOUtils.copy(input,fos);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            IOUtils.closeQuietly(input);
            IOUtils.closeQuietly(fos);
        }
        return "/"+fileName;
    }
}
