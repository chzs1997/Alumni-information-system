package com.winterchen.controller;

import com.winterchen.model.Donation;
import com.winterchen.service.DonationService;
import com.winterchen.service.NewsService;
import com.winterchen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * @Author: liuzipan
 * @Description
 * @Date :15:47 2019/2/20
 * @Modefied By:
 */
@RestController
@RequestMapping(value = "donation")
public class DonationController {

    @Autowired
    private DonationService donationService;

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;


    /**
     *
     * 捐赠信息存储*/
    @PostMapping("donationKeep")
    public Object donationKeep(
            @RequestParam(value = "userId") int userId,
            @RequestParam(value = "donProperty") int donProperty,
            @RequestParam(value = "donItem") String donItem,
            @RequestParam(value = "donAmount") int donAmount
    ){
        try{
            int i = donationService.donationKeep(userId, donProperty, donItem, donAmount);
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

    /**
     *
     * 查询某一个校友的所有捐赠信息
     * */
    @PostMapping("donationSelectByuserId")
    public Object strokeSelect(
            @RequestParam(value = "userId") Integer userId
    ){
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        Donation donation = donationService.donationSelectByuserId(userId);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        hashMap.put("donTime", f.format(donation.getDonTime()));
        hashMap.put("donProperty", donation.getDonProperty());
        hashMap.put("donItem", donation.getDonItem());
        hashMap.put("donAmount", donation.getDonAmount());
        return hashMap;
    }

    /**
     *
     * 修改某一个校友的捐赠信息
     * */
    @PostMapping("donationUpdateByuserId")
    public Object donationUpdateByuserId(
            @RequestParam(value = "userId") Integer userId,
            @RequestParam(value = "donProperty") Integer donProperty,
            @RequestParam(value = "donItem") String donItem,
            @RequestParam(value = "donAmount") Integer donAmount
    ){
        int a = donationService.donationUpdateByuserId(userId, donProperty, donItem, donAmount);
        if(a>0){
            return 1;
        }
        else{
            return 0;
        }
    }

    /**
    * 初始化捐赠信息
    *
    * */
    @ResponseBody
    @RequestMapping(value = "/detectState")
    public Object detectState(){
        HashMap<String,Object> hashMap = new HashMap<String, Object>();
        int donationAmountLastMonth = donationService.findAmountLastMonth();  //上个月捐赠金额
        int userAmountLastMonth = userService.findAmountLastMonth();   //上个月注册用户数
        int donationAmountTotal = donationService.findAmountTotal();   //累计捐赠总额
        int userAmountTotal = userService.findAmountTotal();           //累计用户总数
        hashMap.put("donationAmountLastMonth",donationAmountLastMonth);
        hashMap.put("userAmountLastMonth",userAmountLastMonth);
        hashMap.put("donationAmountTotal",donationAmountTotal);
        hashMap.put("userAmountTotal",userAmountTotal);
        return hashMap;
    }

    /**
     *
     * 捐赠故事初始化
     * */
    @ResponseBody
    @PostMapping("/donationSeralize")
    public Object donationSeralize(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "8")
                    int pageSize
    ){
        return newsService.findDonationNews(pageNum, pageSize);
    }

    /**
    * 捐赠金额公示
    * */
    @ResponseBody
    @PostMapping("donationShow")
    public Object donationShow(
            @RequestParam("num") Integer num
    ){
        return donationService.donationShow(num);
    }




}
