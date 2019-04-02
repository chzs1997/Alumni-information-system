package com.winterchen.model;

import lombok.Data;

import java.sql.Date;

/**
 * @Author: liuzipan
 * @Description
 * @Date :18:59 2019/4/2
 * @Modefied By:
 */
@Data
public class LoginLog {

    /*用户登陆id*/
    private Integer loginId;

    /*用户id*/
    private Integer userId;

    /*用户姓名*/
    private String userName;

    /*登陆时间*/
    private Date loginTime;

}
