package com.winterchen.controller;

import com.winterchen.service.StrokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @Author: liuzipan
 * @Description 行程控制
 * @Date :17:46 2019/2/18
 * @Modefied By:
 */
@Controller
@RequestMapping(value = "stroke")
public class StrokeController {

    @Autowired
    private StrokeService strokeService;


    /*行程信息存储*/
    @PostMapping("strokeKeep")
    public Object strokeKeep(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "strokeTime") Date strokeTime,
            @RequestParam(value = "schPlace") String schPlace
    ){
        try{
            int i = strokeService.strokeKeep(userId, strokeTime, schPlace);
            if(i>0){
                return 1;
            }
            else{
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /*
    * 查询某一个校友的所有行程信息
    * */
//    @PostMapping("strokeSelectByuserId")
//    public Object strokeSelect(
//            @RequestParam(value = "userId") Integer userId
//    ){
//        HashMap<String, Object> hashMap = new HashMap<String, Object>();
//        Stroke stroke = strokeService.strokeSelectByuserId(userId);
//        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        hashMap.put("schTime", f.format(stroke.getSchTime()));
//        hashMap.put("schPlace", stroke.getSchPlace());
//        return hashMap;
//    }

    /*
    * 修改某一个校友的某项行程
    * */
    @PostMapping("strokeUpdateByuserId")
    public Object strokeUpdateByuserId(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "schTime") Date schTime,
            @RequestParam(value = "schPlace") String schPlace
    ){
        int a = strokeService.strokeUpdateByuserId(userId, schTime, schPlace);
        if(a>0){
            return 1;
        }
        else{
            return 0;
        }
    }



}
