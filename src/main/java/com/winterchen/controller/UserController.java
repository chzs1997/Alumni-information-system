package com.winterchen.controller;


import com.winterchen.conf.MyWebAppConfigurer;
import com.winterchen.model.Stroke;
import com.winterchen.model.UserDomain;
import com.winterchen.modelVO.UserDonationVO;
import com.winterchen.modelVO.UserRecommendVO;
import com.winterchen.modelVO.UserStrokeVO;
import com.winterchen.modelVO.UserVO;
import com.winterchen.service.LoginLogService;
import com.winterchen.service.MailService;
import com.winterchen.service.StrokeService;
import com.winterchen.service.UserService;
import com.winterchen.util.SendMessageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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
@Api(value = "类描述", tags = {"显示的标签"})
public class UserController extends HttpServlet {


    @Autowired
    private UserService userService;

    @Autowired
    private LoginLogService loginLogService;

    @Autowired
    private MailService mailService;

    @Autowired
    private StrokeService strokeService;

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
    @ApiOperation(value = "方法描述", produces = "application/json")
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
            @RequestParam(name = "grade" , required = false, defaultValue = "全体")
                    String userGrade,
            @RequestParam(name = "major", required = false, defaultValue = "全体")
                    String userMajor,
            @RequestParam(name = "gender", required = false, defaultValue = "全体")
                    String userGender){
        List<UserVO> user = userService.findByGrade(userGrade, userMajor, userGender);
        return user;
    }

    /**
     *
     *管理端查询所有用户*/
    @ResponseBody
    @PostMapping("/selectAllUser")
    public Object selectAllUser() {
        List<UserVO> user = userService.findAllUser();
        return user;
    }


    /**
     *
     *管理端查询所有用户行程*/
    @ResponseBody
    @PostMapping("/selectAllUserStroke")
    public Object selectAllUserStroke() {
        List<UserStrokeVO> user = strokeService.selectAllUserStroke();
        return user;
    }


    /**
     *
     *管理端添加一条用户行程*/
    @ResponseBody
    @PostMapping("/addUserStrokeByManager")
    public Object addUserStrokeByManager(
            @RequestParam("userMail") String userMail,
            @RequestParam("schTime") String schTime,
            @RequestParam("schPlace") String schPlace,
            @RequestParam("schState") String schState

    ) {
        int i = strokeService.addUserStrokeByManager(userMail, schTime, schPlace, schState);
        List<UserStrokeVO> user = strokeService.selectUserStrokeByUserMail(userMail);
        return user;
    }

    /**
     *
     *管理端添加一条用户行程*/
    @ResponseBody
    @PostMapping("/editUserStrokeByschId")
    public Object editUserStrokeByschId(
            @RequestParam("schTime") String schTime,
            @RequestParam("schPlace") String schPlace,
            @RequestParam("schState") String schState,
            @RequestParam("schId") Integer schId

    ) {
        int i = strokeService.editUserStrokeByschId(schTime, schPlace, schState, schId);
        return i;
    }

    /**
     *
     *管理端删除一条用户行程*/
    @ResponseBody
    @PostMapping("/deleteUserStrokeBySchId")
    public Object deleteUserStrokeBySchId(
            @RequestParam("schId") Integer schId

    ) {
        int i = strokeService.deleteUserStrokeBySchId(schId);
        return i;
    }



    /**
    用户名密码登录
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
            objectMap.put("result", 1);
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
            @RequestParam(value = "userMail") String userMail,
            @RequestParam(value = "userWX") String userWX
    ) {
        HashMap<String,Object> result = new HashMap<>();
        try{
            int i = userService.login(userName, password, phone, userMail, userWX);
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
    注册阶段第二步(额外信息)
    */
    @ResponseBody
    @PostMapping("/add_info")
    public Object login(
            @RequestParam(value = "userMail") String userMail,
            @RequestParam(value = "userGender", required = false, defaultValue = "暂无") String userGender,  //1 表示男性 2表示女性
            @RequestParam(value = "userGrade", required = false, defaultValue = "暂无") String userGrade,
            @RequestParam(value = "userMajor", required = false, defaultValue = "暂无") String userMajor,
            @RequestParam(value = "userGraduateYear", required = false, defaultValue = "暂无") String userGraduateYear,
            @RequestParam(value = "userHeadTeacher", required = false, defaultValue = "暂无") String userHeadTeacher,
            @RequestParam(value = "userAddress", required = false, defaultValue = "暂无") String userAddress,
            @RequestParam(value = "userCompany", required = false, defaultValue = "暂无") String userCompany,
            @RequestParam(value = "userPosition", required = false, defaultValue = "暂无") String userPosition,
            @RequestParam(value = "userEducation", required = false, defaultValue = "暂无") String userEducation,
            @RequestParam(value = "userBirthPlace", required = false, defaultValue = "暂无") String userBirthPlace
    ) {
        int i = userService.add_info(userMail
                                     , userGender
                                     , userGrade
                                     , userMajor
                                     , userGraduateYear
                                     , userHeadTeacher
                                     , userAddress
                                     , userCompany
                                     , userPosition
                                     , userEducation
                                     , userBirthPlace);
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
        Integer resultCode = SendMessageUtil.send("chzs", "d41d8cd98f00b204e980", phone, "【湘潭大学公共管理学院校友信息管理系统】验证码:" + verifyCode);
        if (resultCode > 0) {
            System.out.println("发送成功");
        } else {
            System.out.println("发送失败");
        }
    }


    /**修改密码-步骤1
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
         //若a不为空
            if (a != null) {
                objectMap.put("userId", a.getUserId());
                objectMap.put("userName", a.getUserName());
                return objectMap;
            }
         //否则返回0
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
     * 修改密码-步骤2
      密码重置
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
     * 邮箱验证码获取（登陆使用）
     * */
     @RequestMapping("getCheckCode")
     @ResponseBody
     public String getCheckCode(String userMail){
         String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
         mailCode = checkCode;
         String message = "[Alumni Information System of The Faulty of Public Administration]: Your registration verification code is【" + checkCode +"】";
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


    /*邮箱验证码登陆-验证阶段*/
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

                //存储SESSION_ID
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
     * 捐赠立项信息查询
     * */
    @ResponseBody
    @PostMapping("/getInfo")
    public Object getInfo(
            @SessionAttribute(MyWebAppConfigurer.SESSION_KEY) String account,
            @SessionAttribute("userId") int userId,
            Model model) {
        model.addAttribute("name", account);
        UserDomain i = userService.findByUserId(userId);
        return i;
    }


    /**
     * 个人立项信息查询
     * */
    @ResponseBody
    @PostMapping("/findDonationByUserId")
    public Object findDonationByUserId(
            @SessionAttribute(MyWebAppConfigurer.SESSION_KEY) String account,
            @SessionAttribute("userId") int userId,
            Model model) {
        model.addAttribute("name", account);
        List<UserDonationVO> result = userService.findDonationByUserId(userId);
        for(int i = 0;i<result.size();i++){
            result.get(i).setUserId(i+1);
        }
        return result;
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
     * 查询行程
     * */
    @ResponseBody
    @PostMapping("/findStrokeByUserId")
    public Object findStrokeByUserId(
            @SessionAttribute(MyWebAppConfigurer.SESSION_KEY) String account,
            @SessionAttribute("userId") Integer userId,
            Model model
    ){
        System.out.println(userId);
        model.addAttribute("name", account);
        List<Stroke> result = strokeService.findStrokeByUserId(userId);
        System.out.println(result);
        return  result;
    }

    /**
     *
     * 查询行程
     * */
    @ResponseBody
    @PostMapping("/findRecommendUser")
    public Object findRecommendUser(
            @SessionAttribute(MyWebAppConfigurer.SESSION_KEY) String account,
            @SessionAttribute("userId") Integer userId,
            Model model
    ){
        model.addAttribute("name", account);
        List<UserRecommendVO> user = userService.findRecommendUser(userId);
        return user;
    }

    /**
     *
     * 捐赠立项
     * */
    @ResponseBody
    @PostMapping("/projectApplicant")
    public Object projectApplicant(
             @RequestParam("applicantName") String applicantName,
             @RequestParam("donationAmount") Integer donationAmount,
             @RequestParam("donationMajor") String donationMajor,
             @RequestParam("applicantContact") String applicantContact,
             @RequestParam("applicantMail") String applicantMail,
             @RequestParam("applicantPurpose") String applicantPurpose
    ){
        int i = userService.projectApplicant(applicantName, donationAmount, donationMajor, applicantContact, applicantMail, applicantPurpose);
        return  i;
    }

    /**
     *
     * 查询行程
     * */
    @ResponseBody
    @PostMapping("/findStrokeByUserIdAndState")
    public Object findStrokeByUserIdAndState(
            @SessionAttribute(MyWebAppConfigurer.SESSION_KEY) String account,
            @SessionAttribute("userId") Integer userId,
            @RequestParam("strokeVal") String strokeVal,
            Model model
    ){
        model.addAttribute("name", account);
        List<Stroke> result = null;
        int strokeState;
        switch (strokeVal){
            case "all":
                result= strokeService.findStrokeByUserId(userId);
                break;
            case "S1":    //已处理行程查询
                strokeState = 0;
                result = strokeService.findStrokeByUserIdAndState(userId,strokeState);
                break;
            case "S2":    //未处理行程查询
                strokeState = 1;
                result = strokeService.findStrokeByUserIdAndState(userId,strokeState);
                break;
        }
        return  result;
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
        objectMap.put("WX",i.getUserWX());

        objectMap.put("mail", i.getUserMail());
        objectMap.put("education", i.getUserEducation());
        objectMap.put("graduateYear", i.getUserGraduateYear());
        objectMap.put("headTeacher", i.getUserHeadTeacher());
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
            @RequestParam("userWX") String userWX,
            @RequestParam("userMail") String userMail,
            @RequestParam("userMajor") String userMajor,
            @RequestParam("userGrade") String userGrade,
            @RequestParam("userEducation") String userEducation,
            @RequestParam("userGraduateYear") String userGraduateYear,
            @RequestParam("userHeadTeacher") String userHeadTeacher,
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
                                          ,userWX
                                          ,userMail
                                          ,userMajor
                                          ,userGrade
                                          ,userEducation
                                          ,userGraduateYear
                                          ,userHeadTeacher
                                          ,userAddress
                                          ,userCompany
                                          ,userPosition
                                          ,userImage
                                          ,userId);

         return i;
    }



    /**
    *
    *管理员端校友信息删除*/
    @ResponseBody
    @PostMapping("/deleteUserByUserId")
    public Integer check(@RequestParam("userId") Integer userId) {
        int i = userService.deleteUserByUserId(userId);
        return i;
    }

    /**
     *
     *管理员端校友信息编辑*/
    @ResponseBody
    @PostMapping("/editUserByUserId")
    public Integer editUserByUserId(@RequestParam("userId") Integer userId,
                                    @RequestParam("userName") String userName,
                                    @RequestParam("userGender") String userGender,
                                    @RequestParam("userEducation") String userEducation,
                                    @RequestParam("userBirthPlace") String userBirthPlace,
                                    @RequestParam("userGrade") String userGrade,
                                    @RequestParam("userMajor") String userMajor,
                                    @RequestParam("userGraduateYear") String userGraduateYear,
                                    @RequestParam("userHeadTeacher") String userHeadTeacher,
                                    @RequestParam("userMail") String userMail,
                                    @RequestParam("userCompany") String userCompany,
                                    @RequestParam("userPosition") String userPosition,
                                    @RequestParam("userAddress") String userAddress,
                                    @RequestParam("phone") String phone
) {
        int i = userService.editUserByUserId(userId
                                            ,userName
                                            ,userGender
                                            ,userEducation
                                            ,userBirthPlace
                                            ,userGrade
                                            ,userMajor
                                            ,userGraduateYear
                                            ,userHeadTeacher
                                            ,userMail
                                            ,userCompany
                                            ,userPosition
                                            ,userAddress
                                            ,phone
                                           );
        return i;
    }

    /**
     *
     *管理员端校友信息编辑*/
    @ResponseBody
    @PostMapping("/addUserByManager")
    public Integer addUserByManager(
                                    @RequestParam("userName") String userName,
                                    @RequestParam("userGender") String userGender,
                                    @RequestParam("userEducation") String userEducation,
                                    @RequestParam("userBirthPlace") String userBirthPlace,
                                    @RequestParam("userGrade") String userGrade,
                                    @RequestParam("userMajor") String userMajor,
                                    @RequestParam("userGraduateYear") String userGraduateYear,
                                    @RequestParam("userHeadTeacher") String userHeadTeacher,
                                    @RequestParam("userMail") String userMail,
                                    @RequestParam("userCompany") String userCompany,
                                    @RequestParam("userPosition") String userPosition,
                                    @RequestParam("userAddress") String userAddress,
                                    @RequestParam("phone") String phone
    ) {
        int i = userService.addUserByManager(
                 userName
                ,userGender
                ,userEducation
                ,userBirthPlace
                ,userGrade
                ,userMajor
                ,userGraduateYear
                ,userHeadTeacher
                ,userMail
                ,userCompany
                ,userPosition
                ,userAddress
                ,phone
        );
        return i;
    }

}