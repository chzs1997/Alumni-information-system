package com.winterchen.model;

import lombok.Data;

import java.sql.Date;

/**
 * @Author: liuzipan
 * @Description
 * @Date :19:01 2019/4/2
 * @Modefied By:
 */
@Data
public class ManagerLog {

    /*管理员登陆id*/
    private Integer loginId;

    /*管理员id*/
    private Integer managerId;

    /*管理员姓名*/
    private String managerName;

    /*管理员身份*/
    private String managerIdentity;

    /*管理员性别*/
    private String managerGender;

    /*登陆时间*/
    private Date loginTime;
}
