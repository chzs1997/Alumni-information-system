package com.winterchen.model;

import lombok.Data;

/**
 * @Author: liuzipan
 * @Description  校友
 * @Date :16:27 2019/2/13
 * @Modefied By:
 */

@Data
public class UserDomain {

    /*校友id*/
    private Integer userId;

    /*校友编号*/
    private String userBkey;

    /*校友名*/
    private String userName;

    /*校友性别*/
    private String userGender;

    /*校友学历*/
    private String userEducation;

    /*校友籍贯*/
    private String userBirthPlace;

    /*校友年级*/
    private String userGrade;

    /*校友专业*/
    private String userMajor;

    /*校友毕业年份*/
    private String userGraduateYear;

    /*校友班主任*/
    private String userHeadTeacher;

    /*校友邮箱*/
    private String userMail;

    /*校友所在公司*/
    private String userCompany;

    /*校友职位*/
    private String userPosition;

    /*校友工作地址*/
    private String userAddress;

    /*校友密码*/
    private String password;

    /*联系方式*/
    private String phone;

    /*微信号*/
    private String userWX;

    /*校友头像*/
    private String userImage;

}