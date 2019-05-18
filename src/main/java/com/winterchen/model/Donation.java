package com.winterchen.model;

import lombok.Data;

import java.util.Date;

/**
 * @Author: liuzipan
 * @Description 捐赠
 * @Date :15:47 2019/2/13
 * @Modefied By:
 */
@Data
public class Donation {

    /*捐赠ID*/
    private Integer donId;

    /*用户ID*/
    private Integer userId;

    /*捐赠类型
    *
    * 1.实名捐赠 2.匿名捐赠
    * */
    private Integer donProperty;

    /*捐赠项目*/
    private String donItem;

    /*捐赠时间*/
    private Date donTime;

    /*捐赠金额*/
    private long donAmount;
}
