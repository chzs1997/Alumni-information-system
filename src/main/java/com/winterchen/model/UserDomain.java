package com.winterchen.model;

import lombok.Data;

@Data
public class UserDomain {

    /*用户id*/
    private Integer userId;

    /*用户名*/
    private String userName;

    /*用户性别*/
    private String userGender;

    /*用户学历*/
    private String userEducation;

    /*用户籍贯*/
    private String userBirthPlace;

    /*用户学号*/
    private String userStudentId;

    /*用户年级*/
    private String userGrade;

    /*用户专业*/
    private String userMajor;

    /*用户邮箱*/
    private String userMail;

    /*用户院系*/
    private String userDepartment;

    /*用户身份证号*/
    private String userIdNumber;

    /*用户所在公司*/
    private String userCompany;

    /*用户职位*/
    private String userPosition;

    /*用户工作地址*/
    private String userAddress;

    /*用户密码*/
    private String password;

    /*联系方式*/
    private String phone;

    /*用户头像*/
    private String userImg;

}