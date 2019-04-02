package com.winterchen.controller;


import com.winterchen.conf.MyWebAppConfigurer;
import com.winterchen.model.UserDomain;
import com.winterchen.service.LoginLogService;
import com.winterchen.service.UserService;
import com.winterchen.util.SendMessageUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

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

    /*保存手机验证码*/
    static String verifyCode;

    /*保存手机号码*/
    String phoneNum;

    /*
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

    /*
    查询所有用户
    */
    @ResponseBody
    @PostMapping("/selectByGrade")
    public Object selectByGrade(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize,
            @RequestParam(name = "grade" , required = false, defaultValue = "全体")
                    String userGrade,
            @RequestParam(name = "major", required = false, defaultValue = "全体")
                    String userMajor) {
        return userService.findByGrade(pageNum, pageSize, userGrade, userMajor);
    }

    /*
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
            session.setAttribute("username", i.getUserName());
            session.setAttribute("password", i.getPassword());
            session.setAttribute("phone", i.getPhone());
            session.setAttribute("gender", i.getUserGender());
            session.setAttribute("userId", i.getUserId());
            HashMap<Object, Object> objectMap = new HashMap<>();
            objectMap.put("userName", userName);
            objectMap.put("phone", i.getPhone());
            objectMap.put("gender", i.getUserGender());
            return objectMap;
        }
    }

    /*
    注册阶段第一步(必要信息)(检测无误)
    */
    @ResponseBody
    @PostMapping("/login")
    public Object login(
            @RequestParam(value = "userName") String userName,
            @RequestParam(value = "password") String password,
            @RequestParam(value = "phone") String phone,
            @RequestParam(value = "userIdNumber") String userIdNumber
    ) {
        int i = userService.login(userName, password, phone, userIdNumber);
        if (i > 0) {
            //注册成功
            return 1;
        } else {
            //注册失败
            return 0;
        }
    }

    /*
    注册阶段第二步(额外信息)(检测无误)
    */
    @ResponseBody
    @PostMapping("/add_info")
    public Object login(
            @RequestParam(value = "userIdNumber") String userIdNumber,
            @RequestParam(value = "userGender", required = false) String userGender,  //1 表示男性 2表示女性
            @RequestParam(value = "userGrade", required = false) String userGrade,
            @RequestParam(value = "userMajor", required = false) String userMajor,
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
        //性别解析
        if (userGender == "1") {
            userGender = "男";
        } else {
            userGender = "女";
        }


        int i = userService.add_info(userIdNumber, userGender, userGrade, userMajor, userAddress, userCompany, userPosition, userEducation, userBirthPlace);
        if (i > 0) {
            //信息完善成功
            return 1;
        } else {
            //信息完善失败
            return 0;
        }
    }

    /*
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


    /*判断验证码*/
    @ResponseBody
    @PostMapping("/determine")
    public Object determine(@RequestParam(value = "Code") String Code) {
        //判断是否和保存的验证码一致
        if (Code.equals(verifyCode)) {
            verifyCode = "-1";
            return 1;
        } else {
            return 0;
        }
    }

    /*修改密码-1

    身份证验证(测试通过)
    */
    @ResponseBody
    @PostMapping("/updatePassword_1")
    public Object updatePassword_1(@RequestParam(value = "userIdNumber") String userIdNumber) {
        UserDomain a = userService.findByuserIdNumber(userIdNumber);
        System.out.println(a);
        HashMap<Object, Object> objectMap = new HashMap<>();
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

    /*修改密码-2
      密码重置(测试通过)
      */
    @ResponseBody
    @PostMapping("/updatePassword_2")
    public int updatePassword(@RequestParam(value = "userIdNumber") String userIdNumber,
                              @RequestParam(value = "password") String password) {
        int a = userService.updatePassword(userIdNumber, password);
        //修改成功则返回修改1
        if (a > 0) {
            return 1;
        }
        //否则返回0
        else {
            return 0;
        }
    }


    /*
     *
     * 验证码测试
     * */


    /*
     * 状态检测
     * */
    @ResponseBody
    @PostMapping("/detectState")
    public Object detectState(
            @SessionAttribute(MyWebAppConfigurer.SESSION_KEY) String account,
            @SessionAttribute("username") String username,
            @SessionAttribute("password") String password,
            @SessionAttribute("gender") String gender,
            @SessionAttribute("phone") String phone,
            Model model) {
        model.addAttribute("name", account);
        System.out.println(username);
        System.out.println(password);
        System.out.println(gender);
        System.out.println(phone);
        HashMap<Object, Object> objectMap = new HashMap<>();
        if (username == "尚未登陆") {
            objectMap.put("username", username);
            objectMap.put("password", "尚未登陆");
            objectMap.put("phone", "尚未登陆");
            objectMap.put("gender", "尚未登陆");
        } else {
            objectMap.put("username", username);
            objectMap.put("password", password);
            objectMap.put("phone", phone);
            objectMap.put("gender", gender);
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
        return objectMap;
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