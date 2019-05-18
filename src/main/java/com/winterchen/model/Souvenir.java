package com.winterchen.model;

import lombok.Data;

import java.sql.Date;

/**
 * @Author: liuzipan
 * @Description  纪念品
 * @Date :18:39 2019/4/19
 * @Modefied By:
 */
@Data
public class Souvenir {

    /*纪念品id*/
    private Integer souvenirId;

    /*纪念品姓名*/
    private String souvenirName;

    /*纪念品图片*/
    private String souvenirImage;

    /*纪念品被点赞次数*/
    private Integer souvenirPraiseCounts;

    /*纪念品发行时间*/
    private Date souvenirTime;

    /*纪念品意义*/
    private String souvenirMeaning;
}
