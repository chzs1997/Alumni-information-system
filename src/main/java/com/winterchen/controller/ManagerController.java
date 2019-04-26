package com.winterchen.controller;

import com.winterchen.conf.MyWebAppConfigurer;
import com.winterchen.model.Manager;
import com.winterchen.model.SchoolUserIntegrity;
import com.winterchen.model.UserDomain;
import com.winterchen.service.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

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

    /**
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

    /**
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
    * 近一周用户注册量
    * */
    @ResponseBody
    @RequestMapping(value = "/findUserAmountLastWeek")
    public Object findUserAmountLastWeek(){
        Map<Integer,Integer> hashMap =  userService.findUserAmountLastWeek();
        return hashMap;
    }

    /**
    *
    * 近5个月实名捐赠金额
    * */
    @ResponseBody
    @RequestMapping(value = "/findAmountLast5MonthsReal")
    public Object findAmountLast5Months(){
        Map<Integer,Integer> hashMap =  donationService.findAmountLast5Months();
        return hashMap;
    }

    /**
     *
     * 近5个月匿名捐赠金额
     * */
    @ResponseBody
    @RequestMapping(value = "/findAmountLast5MonthsAnonymous")
    public Object findAmountLast5MonthsAnonymous(){
        Map<Integer,Integer> hashMap =  donationService.findAmountLast5MonthsAnonymous();
        return hashMap;
    }

    /**
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


    /**
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


    /**
     *
     *数据导出
     *
     * */
    @ResponseBody
    @RequestMapping(value = "export")
    public void export (HttpServletResponse response) throws IOException{
        List<UserDomain> users = userService.findAllUser();

        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFSheet sheet = wb.createSheet("获取excel测试表格");

        HSSFRow row = null;

        row = sheet.createRow(0); //创建第一个单元格

        row.setHeight((short)(26.25*20));

        row.createCell(0).setCellValue("用户信息列表"); //为第一行单元格设值

        /**
         * 为标题设置空间
         * firstRow 从第1行开始
         * lastRow 从第0行结束
         *
         * 从第一个单元格开始
         * 从第三个单元格结束
         * */

        CellRangeAddress rowRegion = new CellRangeAddress(0,0,0,2);
        sheet.addMergedRegion(rowRegion);


        /**
         * 动态获取数据库sql语句
         *
         *
         * */
        row = sheet.createRow(1);
        row.setHeight((short)(22.50*20)); //设置行高

         row.createCell(0).setCellValue("用户ID"); //为第一个单元格设值
         row.createCell(0).setCellValue("用户名"); //为第二个单元格设值
         row.createCell(0).setCellValue("用户密码"); //为第三个单元格设值

        for(int i = 0;i<users.size();i++){
            row = sheet.createRow(i+2);
            UserDomain user = users.get(i);
            row.createCell(0).setCellValue(user.getUserId());
            row.createCell(1).setCellValue(user.getUserName());
            row.createCell(2).setCellValue(user.getPassword());
        }

        sheet.setDefaultRowHeight((short)(16.5*20));

        //列宽自适应
        for(int i = 0;i<=13;i++){
            sheet.autoSizeColumn(i);
        }

        response.setContentType("application/vnd.ms-excel;charset=urf-8");
        OutputStream os = response.getOutputStream();
        response.setHeader("Content-disposition","attachment;filename=user.xls");

        wb.write(os);
        os.flush();
        os.close();

    }

    /**
     *
     * 用户信息完善度汇总
     * */
    @ResponseBody
    @RequestMapping(value = "/findIntegrityCount")
    public Object findIntegrityCount(){
        Map<String,Integer> hashMap =  userService.findIntegrityCount();
        return hashMap;
    }

    /**
     *
     * 校友信息完善度较好排名
     * */
    @ResponseBody
    @RequestMapping(value = "/findIntegrityBetter")
    public Object findIntegrityBetter(){
        Map<String,Double> hashMap =  userService.findIntegrityBetter();
        //1、按顺序保存map中的元素，使用LinkedList类型
        List<Map.Entry<String, Double>> keyList = new LinkedList<Map.Entry<String, Double>>(hashMap.entrySet());
        //2、按照自定义的规则排序
        Collections.sort(keyList, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2) {
                if(o2.getValue().compareTo(o1.getValue())>0){
                    return 1;
                }else if(o2.getValue().compareTo(o1.getValue())<0){
                    return -1;
                }  else {
                    return 0;
                }
            }

        });
        //3、将LinkedList按照排序好的结果，存入到HashMap中
        HashMap<String,Double> result=new LinkedHashMap<>();
        for(Map.Entry<String, Double> entry:keyList){
            result.put(entry.getKey(),entry.getValue());
        }
        return result;
    }

    /**
     *
     * 校友信息完善度较差排名
     * */
    @ResponseBody
    @RequestMapping(value = "/findIntegrityWorse")
    public Object findIntegrityWorse(){
        Map<String,Double> hashMap =  userService.findIntegrityWorse();
        //1、按顺序保存map中的元素，使用LinkedList类型
        List<Map.Entry<String, Double>> keyList = new LinkedList<Map.Entry<String, Double>>(hashMap.entrySet());
        //2、按照自定义的规则排序
        Collections.sort(keyList, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2) {
                if(o2.getValue().compareTo(o1.getValue())>0){
                    return -1;
                }else if(o2.getValue().compareTo(o1.getValue())<0){
                    return 1;
                }  else {
                    return 0;
                }
            }

        });
        //3、将LinkedList按照排序好的结果，存入到HashMap中
        HashMap<String,Double> result=new LinkedHashMap<>();
        for(Map.Entry<String, Double> entry:keyList){
            result.put(entry.getKey(),entry.getValue());
        }
        return result;
    }

    /**
     * 获取指定用户的完善度
     *
     * */
    @ResponseBody
    @RequestMapping(value = "/getUserIntegrity")
    public Object getUserIntegrity(@RequestParam("userId") Integer userId){
        SchoolUserIntegrity i =userService.getUserIntegrity(userId);
           int m = (int) (i.getUserInfoIntegrity()*100);
           return  m;
    }


}
