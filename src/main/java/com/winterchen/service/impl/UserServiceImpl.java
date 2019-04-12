package com.winterchen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.dao.MapSessionMapper;
import com.winterchen.dao.UserDao;
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
    MapSessionMapper mapSessionMapper;

    /*注册*/
    @Override
    public int login(String userName, String password, String phone, String userMail) {
        return userDao.login(userName, password, phone, userMail);
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
    public PageInfo<UserDomain> findByGrade(int pageNum, int pageSize, String userGrade, String userMajor) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserDomain> userDomains;
        if(userGrade.equals("全体")&&!userMajor.equals("全体")){
            System.out.println("选择1");
            userDomains = userDao.findByMajor(userMajor);
        }
        else if(userMajor.equals("全体")&&!userGrade.equals("全体")){
            System.out.println("选择2");
            userDomains = userDao.findByGrade(userGrade);
        }
        else if(userMajor.equals("全体")&&userGrade.equals("全体")){
            System.out.println("选择3");
            userDomains = userDao.selectUsers();
        }
        else{
            System.out.println("选择4");
            userDomains = userDao.findByGradeAndMajor(userGrade, userMajor);
        }
        System.out.println(userDomains);
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

    @Override
    public UserDomain findByuserIdNumber(String userIdNumber) {
        return userDao.findByuserIdNumber(userIdNumber);
    }

    @Override
    public UserDomain findByuserMail(String userMail) {
        return userDao.findByuserMail(userMail);
    }

    @Override
    public int add_info(String userMail, String userGender, String userGrade, String userMajor, String userAddress, String userCompany, String userPosition, String userEducation, String userBirthPlace) {
        return userDao.add_info(userMail, userGender, userGrade, userMajor, userAddress, userCompany, userPosition, userEducation, userBirthPlace);
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
}
