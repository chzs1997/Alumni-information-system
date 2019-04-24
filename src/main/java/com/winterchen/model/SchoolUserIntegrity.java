package com.winterchen.model;

import lombok.Data;

/**
 * @Author: liuzipan
 * @Description
 * @Date :15:45 2019/4/22
 * @Modefied By:
 */
@Data
public class SchoolUserIntegrity {

    /*校友id*/
    private Integer userId;

    /*校友性别*/
    private Integer genderInfo;

    /*校友姓名*/
    private Integer nameInfo;

    /*校友密码*/
    private Integer passwordInfo;

    /*校友学号*/
    private Integer studentIdInfo;

    /*校友年级*/
    private Integer gradeInfo;

    /*校友毕业年份*/
    private Integer graduateYearInfo;

    /*校友班主任*/
    private Integer headTeacherInfo;

    /*校友专业*/
    private Integer majorInfo;

    /*校友联系方式*/
    private Integer contactInfo;

    /*校友邮箱*/
    private Integer mailInfo;

    /*校友学历*/
    private Integer educationInfo;

    /*校友工作地址*/
    private Integer addressInfo;

    /*校友所在公司*/
    private Integer companyInfo;

    /*校友工作职位*/
    private Integer positionInfo;

    /*校友籍贯*/
    private Integer birthPlaceInfo;

    /*校友头像*/
    private Integer imgInfo;

    /*校友信息完整度*/
    private float userInfoIntegrity;

}
