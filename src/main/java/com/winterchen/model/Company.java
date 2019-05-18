package com.winterchen.model;

import lombok.Data;

/**
 * @Author: liuzipan
 * @Description  企业
 * @Date :10:13 2019/5/1
 * @Modefied By:
 */
@Data
public class Company {

    /*公司id*/
    private Integer companyId;

    /*公司名称*/
    private String companyName;

    /*公司简介*/
    private String companyIntroduction;

    /*公司链接*/
    private String companyHref;

    /*公司图片*/
    private String companyImg;

}
