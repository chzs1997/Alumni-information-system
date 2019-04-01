package com.winterchen.model;

import lombok.Data;

import java.util.Date;

/**
 * @Author: liuzipan
 * @Description
 * @Date :16:27 2019/2/13
 * @Modefied By:
 */
@Data
public class Stroke {

    /*行程ID*/
    private Integer schID;

    /*用户ID*/
    private Integer userId;

    /*会面时间*/
    private Date  schTime;

    /*聚会地点*/
    private String schPlace;

    /*创建时间*/
    private Date createTime;

    /*更新时间*/
    private Date updateTime;
}
