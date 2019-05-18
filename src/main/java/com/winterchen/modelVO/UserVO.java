package com.winterchen.modelVO;

import lombok.Data;

/**
 * @Author: liuzipan
 * @Description  展示信息
 * @Date :9:45 2019/4/30
 * @Modefied By:
 */
@Data
public class UserVO {

    /*校友ID*/
    private Integer userId;

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

    /*联系方式*/
    private String phone;

}
