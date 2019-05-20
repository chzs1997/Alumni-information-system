package com.winterchen.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: liuzipan
 * @Description 图片下载
 * @Date :11:48 2019/5/1
 * @Modefied By:
 */
public class PicutreLoad {
    public static String pictureStorage(int type, MultipartFile file, String img_name){
        String new_img_name = "";
        String artImage = "";
        if(type == 4){
            new_img_name =  "F:\\AlmuniSystem\\src\\main\\resources\\static\\static\\img\\service\\souvenir\\"+img_name;
            artImage = "static/static/img/service/souvenir/"+img_name;
        }
        else if(type == 5){
            new_img_name =  "F:\\AlmuniSystem\\src\\main\\resources\\static\\static\\img\\company\\"+img_name;
            artImage = "static/static/img/company/"+img_name;
        }
        else{
            new_img_name =  "F:\\AlmuniSystem\\src\\main\\resources\\static\\static\\img\\service\\entertainment\\"+img_name;
            artImage = "static/static/img/service/entertainment/"+img_name;
        }
        try{
            BufferedOutputStream out = new BufferedOutputStream(
                    new FileOutputStream(new_img_name));
            System.out.println(file.getName());
            out.write(file.getBytes());
            out.flush();
            out.close();
            return artImage;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        }
    }
}
