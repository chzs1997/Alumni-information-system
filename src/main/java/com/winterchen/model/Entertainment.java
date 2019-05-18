package com.winterchen.model;

import lombok.Data;

/**
 * @Author: liuzipan
 * @Description 食宿
 * @Date :10:23 2019/5/1
 * @Modefied By:
 */
@Data
public class Entertainment {

    /*食宿id*/
    private Integer entertainmentId;

    /*食宿类型*/
    private Integer entertainmentType;

    /*食宿名称*/
    private String entertainmentName;

    /*食宿图片*/
    private String entertainmentImg;

    /*食宿地址*/
    private String entertainmentAddress;

    /*食宿联系方式*/
    private String entertainmentContact;

    /*食宿介绍*/
    private String entertainmentIntroduction;
}
