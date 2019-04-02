package com.winterchen.controller;

import com.winterchen.conf.MyWebAppConfigurer;
import com.winterchen.model.Manager;
import com.winterchen.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liuzipan
 * @Description
 * @Date :15:15 2019/3/31
 * @Modefied By:
 */
@Controller
@RequestMapping(value = "/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

    @Autowired
    private UserService userService;

    @Autowired
    private DonationService donationService;

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private ManagerLogService managerLogService;

    /*
    *
    * 用户登陆
    */
    @ResponseBody
    @RequestMapping(value = "/login")
    public Object login(
            @RequestParam(value = "managerName") String managerName,
            @RequestParam(value = "managerPassword") String managerPassword,
            HttpSession session
    ){
        Manager i = managerService.login(managerName, managerPassword);
        if (i == null) {
            //查询没有结果
            return 0;
        } else {
            //数据库中有该用户
            // 设置session
            managerLogService.save(i.getManagerId(),i.getManagerName(),i.getManagerIdentity(),i.getManagerGender());

            session.setAttribute(MyWebAppConfigurer.SESSION_KEY, i.getManagerId());
            session.setAttribute("managerName", i.getManagerName());
            session.setAttribute("managerPassword", i.getManagerPassword());
            session.setAttribute("managerGender", i.getManagerGender());
            session.setAttribute("managerIdentity", i.getManagerIdentity());
            HashMap<Object, Object> objectMap = new HashMap<>();
            objectMap.put("managerName", i.getManagerName());
            objectMap.put("managerPassword", i.getManagerPassword());
            objectMap.put("managerIdentity", i.getManagerIdentity());
            System.out.println(i.getManagerName());
            System.out.println(i.getManagerPassword());
            System.out.println(i.getManagerIdentity());
            return objectMap;
        }
    }

    /*
     * 状态检测
     * */
    @ResponseBody
    @PostMapping("/detectState")
    public Object detectState(
            @SessionAttribute(MyWebAppConfigurer.SESSION_KEY) String account,
            @SessionAttribute("managerName") String managerName,
            @SessionAttribute("managerPassword") String managerPassword,
            @SessionAttribute("managerIdentity") String managerIdentity,
            Model model) {
        model.addAttribute("name", account);
        System.out.println(managerName);
        System.out.println(managerPassword);
        System.out.println(managerIdentity);
        HashMap<Object, Object> objectMap = new HashMap<>();
        if (managerName == "尚未登陆") {
            objectMap.put("managerName", managerName);
            objectMap.put("managerPassword", "尚未登陆");
            objectMap.put("managerIdentity", "尚未登陆");
        } else {
            objectMap.put("managerName", managerName);
            objectMap.put("managerPassword", managerPassword);
            objectMap.put("managerIdentity", managerIdentity);
        }
        return objectMap;
    }


    /*
     * 退出登陆状态，注销
     *
     * */
    @ResponseBody
    @PostMapping("/logout")
    public Object logout(HttpSession session) {
        // 移除session
        session.removeAttribute(MyWebAppConfigurer.SESSION_KEY);
        return 1;
    }

    /*
    * 近一周用户注册量
    * */
    @ResponseBody
    @RequestMapping(value = "/findUserAmountLastWeek")
    public Object findUserAmountLastWeek(){
        Map<Integer,Integer> hashMap =  userService.findUserAmountLastWeek();
        return hashMap;
    }

    /*
    *
    * 近5个月实名捐赠金额
    * */
    @ResponseBody
    @RequestMapping(value = "/findAmountLast5MonthsReal")
    public Object findAmountLast5Months(){
        Map<Integer,Integer> hashMap =  donationService.findAmountLast5Months();
        return hashMap;
    }

    /*
     *
     * 近5个月匿名捐赠金额
     * */
    @ResponseBody
    @RequestMapping(value = "/findAmountLast5MonthsAnonymous")
    public Object findAmountLast5MonthsAnonymous(){
        Map<Integer,Integer> hashMap =  donationService.findAmountLast5MonthsAnonymous();
        return hashMap;
    }

    /*
    *
    * 查询最近用户登陆情况
    * */
    @ResponseBody
    @RequestMapping(value = "/findUserLoginLog")
    public Object findUserLoginLog(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "9")
                    int pageSize
    ){
        return loginLogService.selectLog(pageNum,pageSize);
    }


    /*
     *
     * 查询最近管理员登陆情况
     * */
    @ResponseBody
    @RequestMapping(value = "/findManagerLoginLog")
    public Object findManagerLoginLog(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "3")
                    int pageSize
    ){
        return managerLogService.selectLog(pageNum,pageSize);
    }
}
