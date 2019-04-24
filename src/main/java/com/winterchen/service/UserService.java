package com.winterchen.service;

import com.github.pagehelper.PageInfo;
import com.winterchen.model.SchoolUserIntegrity;
import com.winterchen.model.UserDomain;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2018/4/19.
 */
@Service
public interface UserService {


    /**
     *
     *
     * 注册*/
    int login(String userName, String password, String phone, String userMail);

    /**
     *
     * 登录*/
    UserDomain check(String userName, String password);

    /**
     *
     *
     * 查询所有用户(分页查询)*/
    PageInfo<UserDomain> findAllUser(int pageNum, int pageSize);

    /**
     *
     *
     * 根据年级专业查找*/
    PageInfo<UserDomain> findByGrade(int pageNum, int pageSize, String userGrade, String userMajor, String userGender);

    /**
     *
     * 根据用户ID查询用户*/
    UserDomain findByUserId(Integer userId);

    /**
     *
     *
     * 根据电话号码查询用户*/
    UserDomain findByPhoneNum(String phoneNum);

    /**
     *
     * 根据用户id修改密码*/
    int updatePassword(Integer userId, String password);

    /**
     *
     * 根据用户身份证号修改密码*/
    int assignUserBkey(String phone);

    /**
     *
     * 根据用户身份证查询用户*/
    UserDomain findByuserIdNumber(String userIdNumber);

    /**
     *
     * 根据用户邮箱验证*/
    UserDomain findByuserMail(String userMail);

    /**
    *
    根据用户身份证号添加其他信息*/
    int add_info(String userMail
                 ,String userGender
                 ,String userGrade
                 ,String userMajor
                 ,String userGraduateYear
                 ,String userHeadTeacher
                 ,String userAddress
                 ,String userCompany
                 ,String userPosition
                 ,String userEducation
                 ,String userBirthPlace);


    /**
    * 更改信息
    * */
    int updateMessage(String userName
                      ,String userGender
                      ,String userBirthPlace
                      ,String phone
                      ,String userMail
                      ,String userStudentId
                      ,String userMajor
                      ,String userGrade
                      ,String userEducation
                      ,String userGraduateYear
                      ,String userHeadTeacher
                      ,String userAddress
                      ,String userCompany
                      ,String userPosition
                      ,String userImage
                      ,int userId);

    UserDomain findUserByUserId(Integer userId);

    /**
    * 上个月注册用户数
     */
    int findAmountLastMonth();

    /**
     * 累计注册用户数
     */
    int findAmountTotal();

    /**
     * 近一周用户注册量
     * */
    Map<Integer,Integer> findUserAmountLastWeek();


    /**
     *
     * 遍历所有用户
     *
     * */
    List<UserDomain> findAllUser();


    /**
     *
     * 遍历根据条件筛选用户(用于Excel下载)
     *
     * */
    List<UserDomain> findUserExcel(String grade, String major, String gender);

    /**
     *
     *
     * 用户信息度汇总
     * */
    Map<String,Integer> findIntegrityCount();

    /**
     *
     *
     * 校友信息完善度较好排名
     * */
    Map<String,Object> findIntegrityBetter();

    /**
     *
     *
     * 校友信息完善度较差排名
     * */
    Map<String,Object> findIntegrityWorse();

    /**
     *
     *
     * 校友信息完善度较差排名
     * */
    SchoolUserIntegrity getUserIntegrity(Integer userId);
}
