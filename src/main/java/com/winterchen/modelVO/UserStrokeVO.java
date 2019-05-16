package com.winterchen.modelVO;

import lombok.Data;

/**
 * @Author: liuzipan
 * @Description
 * @Date :20:41 2019/4/30
 * @Modefied By:
 */
@Data
public class UserStrokeVO {

    /*校友ID*/
    private Integer userId;

    /*校友名*/
    private String userName;

    /*校友年级*/
    private String userGrade;

    /*校友专业*/
    private String userMajor;

    /*校友班主任*/
    private String userHeadTeacher;

    /*校友邮箱*/
    private String userMail;

    /*联系方式*/
    private String phone;

    /*行程ID*/
    private Integer schId;

    /*会面时间*/
    private String  schTime;

    /*聚会地点*/
    private String schPlace;

    /*行程状态*/
    private String schState;
}
