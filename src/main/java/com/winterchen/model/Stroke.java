package com.winterchen.model;

import lombok.Data;

/**
 * @Author: liuzipan
 * @Description  行程
 * @Date :16:27 2019/2/13
 * @Modefied By:
 */
@Data
public class Stroke {

    /*行程ID*/
    private Integer schId;

    /*用户ID*/
    private Integer userId;

    /*会面时间*/
    private String  schTime;

    /*聚会地点*/
    private String schPlace;

    /*行程状态*/
    private String schState;


}
