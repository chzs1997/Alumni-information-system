package com.winterchen.service;

import com.github.pagehelper.PageInfo;
import com.winterchen.model.UserDomain;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2018/4/19.
 */
@Service
public interface UserService {


    /*注册*/
    int login(String userName, String password, String phone, String userIdNumber);

    /*登录*/
    UserDomain check(String userName, String password);

    /*查询所有用户*/
    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);

    /*根据年级专业查找*/
    PageInfo<UserDomain> findByGrade(int pageNum, int pageSize, String userGrade, String userMajor);

    /*根据用户ID查询用户*/
    UserDomain findByUserId(Integer userId);

    /*根据电话号码查询用户*/
    UserDomain findByPhoneNum(String phoneNum);

    /*根据用户id修改密码*/
    int updatePassword(String userIdNumber,String password);

    /*根据用户身份证号修改密码*/


    /*根据用户身份证查询用户*/
    UserDomain findByuserIdNumber(String userIdNumber);

    /*根据用户身份证号添加其他信息*/
    int add_info(String userIdNumber
                 ,String userGender
                 ,String userGrade
                 ,String userMajor
                 ,String userAddress
                 ,String userCompany
                 ,String userPosition
                 ,String userEducation
                 ,String userBirthPlace);

    UserDomain findUserByUserId(Integer userId);

    /*
    * 上个月注册用户数
     */
    int findAmountLastMonth();

    /*
     * 累计注册用户数
     */
    int findAmountTotal();

    /*
     * 近一周用户注册量
     * */
    Map<Integer,Integer> findUserAmountLastWeek();
}
