package com.winterchen.controller;


import com.winterchen.conf.MyWebAppConfigurer;
import com.winterchen.model.UserDomain;
import com.winterchen.service.LoginLogService;
import com.winterchen.service.MailService;
import com.winterchen.service.UserService;
import com.winterchen.util.SendMessageUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Random;

import static com.winterchen.util.SendMessageUtil.getRandomCode;


/**
 * @Author: liuzipan
 * @Description 登陆注册环节控制
 * @Date :17:46 2019/2/18
 * @Modefied By:
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends HttpServlet {


    @Autowired
    private UserService userService;

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private MailService mailService;

    /*保存手机验证码*/
    static String verifyCode;

    /*保存邮箱验证码*/
    static String mailCode;

    /*保存手机号码*/
    String phoneNum;

    /**
    查询所有用户
    */
    @ResponseBody
    @RequestMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize) {
        return userService.findAllUser(pageNum, pageSize);
    }

    /**
    查询所有用户
    */
    @ResponseBody
    @PostMapping("/selectByGrade")
    public Object selectByGrade(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "12")
                    int pageSize,
            @RequestParam(name = "grade" , required = false, defaultValue = "全体")
                    String userGrade,
            @RequestParam(name = "major", required = false, defaultValue = "全体")
                    String userMajor,
            @RequestParam(name = "gender", required = false, defaultValue = "全体")
                    String userGender){
        return userService.findByGrade(pageNum, pageSize, userGrade, userMajor, userGender);
    }

    /**
    用户名密码登录(测试通过)
    */
    @ResponseBody
    @PostMapping("check")
    public Object check(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password,
            HttpSession session
    ) {
        UserDomain i = userService.check(userName, password);
        if (i == null) {
            //查询没有结果
            return 0;
        } else {
            //数据库中有该用户
            // 设置session
            loginLogService.save(i.getUserId(),i.getUserName());  //在登陆日志中保存
            session.setAttribute(MyWebAppConfigurer.SESSION_KEY, i.getUserId());
            session.setAttribute("userId", i.getUserId());
            HashMap<Object, Object> objectMap = new HashMap<>();
            objectMap.put("userName", userName);
            objectMap.put("phone", i.getPhone());
            objectMap.put("gender", i.getUserGender());
            objectMap.put("userImage",i.getUserImage());
            return objectMap;
        }
    }

    /**
    注册阶段第一步(必要信息)
    */
    @ResponseBody
    @PostMapping("/login")
    public Object login(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "userMail") String userMail
    ) {
        HashMap<String,Object> result = new HashMap<>();
        try{
            int i = userService.login(userName, password, phone, userMail);
            userService.assignUserBkey(phone);
            if (i > 0) {
                //注册成功
                result.put("result",i);
                result.put("userMail",userMail);
            } else {
                //注册失败
                result.put("result",i);
            }
        }catch (DuplicateKeyException e){
            result.put("result","邮箱已被注册");
        }
        return result;

    }

    /**
    注册阶段第二步(额外信息)(检测无误)
    */
    @ResponseBody
    @PostMapping("/add_info")
    public Object login(
            @RequestParam(value = "userMail") String userMail,
            @RequestParam(value = "userGender", required = false) String userGender,  //1 表示男性 2表示女性
            @RequestParam(value = "userGrade", required = false) String userGrade,
            @RequestParam(value = "userMajor", required = false) String userMajor,
            @RequestParam(value = "userGraduateYear", required = false) String userGraduateYear,
            @RequestParam(value = "userHeadTeacher", required = false) String userHeadTeacher,
            @RequestParam(value = "userAddress", required = false) String userAddress,
            @RequestParam(value = "userCompany", required = false) String userCompany,
            @RequestParam(value = "userPosition", required = false) String userPosition,
            @RequestParam(value = "userEducation", required = false) String userEducation,
            @RequestParam(value = "userBirthPlace", required = false) String userBirthPlace
    ) {
        if (userGender == null) {
            userGender = "暂无";
        }

        if (userGrade == null) {
            userGrade = "暂无";
        }
        if (userMajor == null) {
            userMajor = "暂无";
        }
        if (userAddress == null) {
            userAddress = "暂无";
        }
        if (userCompany == null) {
            userCompany = "暂无";
        }
        if (userPosition == null) {
            userPosition = "暂无";
        }
        if (userGraduateYear == null) {
            userGraduateYear = "暂无";
        }
        if (userHeadTeacher == null) {
            userHeadTeacher = "暂无";
        }
        //性别解析
        if (userGender == "1") {
            userGender = "男";
        } else {
            userGender = "女";
        }


        int i = userService.add_info(userMail, userGender, userGrade, userMajor, userGraduateYear, userHeadTeacher, userAddress, userCompany, userPosition, userEducation, userBirthPlace);
        if (i > 0) {
            //信息完善成功
            return 1;
        } else {
            //信息完善失败
            return 0;
        }
    }

    /**
    找回密码第一步
     && 注册验证码第一步
    */
    @ResponseBody
    @PostMapping("/sendMessage")
    public void sendMessage(@RequestParam(value = "phone") String phone) {
        phoneNum = phone;
        verifyCode = getRandomCode(6);
        Integer resultCode = SendMessageUtil.send("chzs", "d41d8cd98f00b204e980", phone, "【湘潭大学公共管理学院校友系统】验证码:" + verifyCode);
        if (resultCode > 0) {
            System.out.println("发送成功");
        } else {
            System.out.println("发送失败");
        }
    }


    /**修改密码-1

    邮箱验证(测试通过)
    */
    @ResponseBody
    @PostMapping("/updatePassword_1")
    public Object updatePassword_1(@RequestParam(value = "mailCode") String mailCode,
                                   @RequestParam(value = "userMail") String userMail) {
        int result = (int) determine(mailCode);
        HashMap<Object, Object> objectMap = new HashMap<>();
        if(result == 1){
            UserDomain a = userService.findByuserMail(userMail);
            System.out.println(a);
//        若a不为空
            if (a != null) {
                objectMap.put("userId", a.getUserId());
                objectMap.put("userName", a.getUserName());
                return objectMap;
            }
//        否则返回0
            else {
                objectMap.put("userName", "查无此人");
                return objectMap;
            }
        }
        else{
            objectMap.put("userName","验证码错误");
        }
        return objectMap;

    }

    /**
     * 修改密码-2
      密码重置(测试通过)
      */
    @ResponseBody
    @PostMapping("/updatePassword_2")
    public int updatePassword(@RequestParam(value = "userId") Integer userId,
                              @RequestParam(value = "password") String password) {
        int a = userService.updatePassword(userId, password);
        //修改成功则返回修改1
        if (a > 0) {
            return 1;
        }
        //否则返回0
        else {
            return 0;
        }
    }


    /**
     *
     * 邮箱验证码
     * */
     @RequestMapping("getCheckCode")
     @ResponseBody
     public String getCheckCode(String userMail){
         String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
         mailCode = checkCode;
         String message = "您的注册验证码为" + checkCode;
         try{
             mailService.sendSimpleMail(userMail,"注册验证码",message);
         }catch (Exception e){
             e.printStackTrace();
         }
         return checkCode;
     }

    /*判断验证码(手机号码或者邮箱)*/
    @ResponseBody
    @PostMapping("/determine")
    public Object determine(@RequestParam(value = "Code") String Code) {
        //判断是否和保存的验证码一致
        if (Code.equals(mailCode)) {
            mailCode = "-1";
            return 1;
        } else {
            return 0;
        }
    }

    /*邮箱验证码登陆*/
    @ResponseBody
    @PostMapping("/loginByMail")
    public Object loginByMail(@RequestParam(value = "userMail") String userMail
                           ,@RequestParam(value = "Code") String Code
                           ,HttpSession session) {
        HashMap<Object, Object> objectMap = new HashMap<>();
        //判断是否和保存的验证码一致
        if (Code.equals(mailCode)) {
            mailCode = "-1";
            UserDomain i = userService.findByuserMail(userMail);
            if (i == null) {
                //查询没有结果
                objectMap.put("result","用户尚未注册，请先注册后登陆");
            } else {
                //数据库中有该用户
                // 设置session
                objectMap.put("result","1");
                loginLogService.save(i.getUserId(),i.getUserName());  //在登陆日志中保存
                session.setAttribute(MyWebAppConfigurer.SESSION_KEY, i.getUserId());
                session.setAttribute("userId", i.getUserId());
                objectMap.put("userName", i.getUserName());
                objectMap.put("phone", i.getPhone());
                objectMap.put("gender", i.getUserGender());
                objectMap.put("userImage",i.getUserImage());
                return objectMap;
            }
        } else {
            objectMap.put("result","邮箱验证码错误");
        }
        return objectMap;
    }

    /**
     * 状态检测
     * */
    @ResponseBody
    @PostMapping("/detectState")
    public Object detectState(
            @SessionAttribute(MyWebAppConfigurer.SESSION_KEY) String account,
            @SessionAttribute("userId") int userId,
            Model model) {
        model.addAttribute("name", account);
        UserDomain i = userService.findByUserId(userId);
        System.out.println(i.getUserName());
        System.out.println(i.getPassword());
        System.out.println(i.getUserGender());
        System.out.println(i.getPhone());
        return i;
    }


   /**
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

    /**
    *
    *个人资料信息填充
    * */
    @ResponseBody
    @PostMapping("/personalCheck")
    public Object personalCheck(
            @SessionAttribute(MyWebAppConfigurer.SESSION_KEY) String account,
            @SessionAttribute("userId") Integer userId,
            Model model
    ){
        model.addAttribute("name", account);
        HashMap<Object, Object> objectMap = new HashMap<>();
        UserDomain i = userService.findByUserId(userId);
        objectMap.put("userName", i.getUserName());
        objectMap.put("phone", i.getPhone());
        objectMap.put("gender", i.getUserGender());
        objectMap.put("birthPlace", i.getUserBirthPlace());

        objectMap.put("mail", i.getUserMail());
        objectMap.put("studentId", i.getUserStudentId());
        objectMap.put("education", i.getUserEducation());
        objectMap.put("grade", i.getUserGrade());
        objectMap.put("major", i.getUserMajor());
        objectMap.put("company", i.getUserCompany());
        objectMap.put("userAddress", i.getUserAddress());
        objectMap.put("position", i.getUserPosition());
        objectMap.put("userId",i.getUserId());
        objectMap.put("userImage",i.getUserImage());
        return objectMap;
    }

    /**
     *
     * 信息填充
     */
    @ResponseBody
    @RequestMapping(value = "/updateMessage")
    public int updateMessage(
            @RequestParam("userName") String userName,
            @RequestParam("userGender") String userGender,
            @RequestParam("userBirthPlace") String userBirthPlace,
            @RequestParam("phone") String phone,
            @RequestParam("userMail") String userMail,
            @RequestParam("userStudentId") String userStudentId,
            @RequestParam("userMajor") String userMajor,
            @RequestParam("userGrade") String userGrade,
            @RequestParam("userEducation") String userEducation,
            @RequestParam("userAddress") String userAddress,
            @RequestParam("userCompany") String userCompany,
            @RequestParam("userId") int userId,
            @RequestParam("userImage") String userImage,
            @RequestParam("userPosition") String userPosition

    ){
         int i = userService.updateMessage(userName
                                          ,userGender
                                          ,userBirthPlace
                                          ,phone
                                          ,userMail
                                          ,userStudentId
                                          ,userMajor
                                          ,userGrade
                                          ,userEducation
                                          ,userAddress
                                          ,userCompany
                                          ,userPosition
                                          ,userImage
                                          ,userId);
         return i;
    }


    /*
    *
    *安卓端应用*/
    @ResponseBody
    @PostMapping("/cc")
    public String check(@RequestBody JSONObject obj) {
        String username = obj.getString("username");
        String password = obj.getString("password");
        System.out.println("username: " + username);
        System.out.println("password: " + password);
        return "user";
    }


}