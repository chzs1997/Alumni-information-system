package com.winterchen.model;

import lombok.Data;

/**
 * @Author: liuzipan
 * @Description 捐赠项目
 * @Date :22:18 2019/5/3
 * @Modefied By:
 */
@Data
public class DonationProject {


    /*项目Id*/
    private Integer projectId;

    /*立项人姓名*/
    private Integer applicantName;

    /*捐赠金额*/
    private Integer donationAmount;

    /*捐赠专业*/
    private String donationMajor;

    /*立项人联系方式*/
    private String applicantContact;

    /*立项人邮箱*/
    private String applicantMail;

    /*立项目的*/
    private String applicantPurpose;
}
