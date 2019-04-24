package com.winterchen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.dao.MapSessionMapper;
import com.winterchen.dao.UserDao;
import com.winterchen.dao.UserIntegrityDao;
import com.winterchen.model.SchoolUserIntegrity;
import com.winterchen.model.UserDomain;
import com.winterchen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/16.
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;//这里会报错，但是并不会影响

    @Autowired
    private UserIntegrityDao userIntegrityDao;

    @Autowired
    MapSessionMapper mapSessionMapper;

    /**
     *
     * 初步注册*/
    @Override
    public int login(String userName, String password, String phone, String userMail) {
        int i = userDao.login(userName, password, phone, userMail);
        userIntegrityDao.addUser(userName, password, phone, userMail);
        return i;
    }

    /**
     * 详细信息注册
     * */
    @Override
    public int add_info(String userMail, String userGender, String userGrade, String userMajor,  String userGraduateYear
            ,String userHeadTeacher,String userAddress, String userCompany, String userPosition, String userEducation, String userBirthPlace) {
        int i = userDao.add_info(userMail, userGender, userGrade, userMajor, userGraduateYear, userHeadTeacher, userAddress, userCompany, userPosition, userEducation, userBirthPlace);
        userIntegrityDao.renewInfo();
        userIntegrityDao.countIntegrity();
        return i;
    }

    /*登录*/
    @Override
    public UserDomain check(String userName, String password){
        return userDao.check(userName, password);
    }

    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    /*查询所有用户*/
    @Override
    public PageInfo<UserDomain> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<UserDomain> userDomains = userDao.selectUsers();
        PageInfo result = new PageInfo(userDomains);
        return result;
    }


    /*根据年级和专业查询*/
    @Override
    public PageInfo<UserDomain> findByGrade(int pageNum, int pageSize, String userGrade, String userMajor, String userGender) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserDomain> userDomains = filterByConditions(userGrade, userMajor, userGender);
        PageInfo result = new PageInfo(userDomains);
        return result;
    }

    /*根据客户id查询用户*/
    @Override
    public UserDomain findByUserId(Integer userId) {
        return userDao.findByUserId(userId);
    }

    /*根据电话号码查询用户*/
    @Override
    public UserDomain findByPhoneNum(String phoneNum) {
        return userDao.findByPhoneNum(phoneNum);
    }

    //根据用户id修改密码
    @Override
    public int updatePassword(Integer userId, String password) {
        return userDao.updatePassword(userId,password);
    }

    //设定用户编号
    @Override
    public int assignUserBkey(String phone) {
        return userDao.assignUserBkey(phone);
    }

    @Override
    public UserDomain findByuserIdNumber(String userIdNumber) {
        return userDao.findByuserIdNumber(userIdNumber);
    }

    @Override
    public UserDomain findByuserMail(String userMail) {
        return userDao.findByuserMail(userMail);
    }



    @Override
    public int updateMessage(String userName, String userGender, String userBirthPlace, String phone, String userMail, String userStudentId, String userMajor, String userGrade, String userEducation, String userGraduateYear, String userHeadTeacher,String userAddress, String userCompany, String userPosition, String userImage, int userId) {
        int i =userDao.updateMessage(userName
                ,userGender
                ,userBirthPlace
                ,phone
                ,userMail
                ,userStudentId
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
        userIntegrityDao.renewInfo();
        userIntegrityDao.countIntegrity();
        return i;
    }

    @Override
    public UserDomain findUserByUserId(Integer userId) {
        return userDao.findByUserId(userId);
    }

    @Override
    public int findAmountLastMonth() {
        return userDao.findAmountLastMonth();
    }

    @Override
    public int findAmountTotal() {
        return userDao.findAmountTotal();
    }

    @Override
    public Map<Integer, Integer> findUserAmountLastWeek() {
        Map<Integer,Integer> mapAmount = mapSessionMapper.findUserAmountLastWeek();
        return mapAmount;
    }

    @Override
    public List<UserDomain> findAllUser() {
        return userDao.findAllUser();
    }

    @Override
    public List<UserDomain> findUserExcel(String grade, String major, String gender) {
        List<UserDomain> userDomains = filterByConditions(grade, major, gender);
        return  userDomains;
    }

    @Override
    public Map<String, Integer> findIntegrityCount() {
        Map<String,Integer> mapAmount = mapSessionMapper.findIntegrityCount();
        return mapAmount;
    }

    @Override
    public Map<String, Object> findIntegrityBetter() {
        Map<String,Object> mapAmount = mapSessionMapper.findIntegrityBetter();
        return mapAmount;
    }

    @Override
    public Map<String, Object> findIntegrityWorse() {
        Map<String,Object> mapAmount = mapSessionMapper.findIntegrityWorse();
        return mapAmount;
    }

    @Override
    public SchoolUserIntegrity getUserIntegrity(Integer userId) {
        return userIntegrityDao.getUserIntegrity(userId);
    }

    /**
     * 筛选条件通用方法
     *
     * */
    public List<UserDomain> filterByConditions(String userGrade, String userMajor, String userGender){
        List<UserDomain> userDomains;

        if(userGender.equals("全体")&&userGrade.equals("全体")&&!userMajor.equals("全体")){
            System.out.println("专业查询");
            userDomains = userDao.findByMajor(userMajor);
        }
        else if(userGender.equals("全体")&&userMajor.equals("全体")&&!userGrade.equals("全体")){
            System.out.println("年级查询");
            userDomains = userDao.findByGrade(userGrade);
        }
        else if(!userGender.equals("全体")&&userMajor.equals("全体")&&userGrade.equals("全体")){
            System.out.println("性别查询");
            userDomains = userDao.findByGender(userGender);
        }
        else if(userGender.equals("全体")&&!userMajor.equals("全体")&&!userGrade.equals("全体")){
            System.out.println("专业年级查询");
            userDomains = userDao.findByGradeAndMajor(userGrade, userMajor);
        }
        else if(!userGender.equals("全体")&&!userMajor.equals("全体")&&userGrade.equals("全体")){
            System.out.println("专业性别查询");
            userDomains = userDao.findByGenderAndMajor(userGender, userMajor);
        }
        else if(!userGender.equals("全体")&&userMajor.equals("全体")&&!userGrade.equals("全体")){
            System.out.println("年级性别查询");
            userDomains = userDao.findByGenderAndGrade(userGender, userGrade);
        }
        else if(userGender.equals("全体")&&userMajor.equals("全体")&&userGrade.equals("全体")){
            System.out.println("全体查询");
            userDomains = userDao.selectUsers();
        }
        else{
            System.out.println("专业年级性别查询");
            userDomains = userDao.findByGradeAndMajorAndGender(userGrade, userMajor,userGender);
        }
        System.out.println(userDomains);
        return userDomains;
    }


}
