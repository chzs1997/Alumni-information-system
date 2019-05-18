package com.winterchen.modelVO;

import lombok.Data;

/**
 * @Author: liuzipan
 * @Description  个人捐赠信息查看
 * @Date :16:53 2019/5/5
 * @Modefied By:
 */
@Data
public class UserDonationVO {

    /*校友ID*/
    private Integer userId;

    /*校友名*/
    private String applicantName;

    /*捐赠金额*/
    private String donationAmount;

    /*捐赠专业*/
    private String donationMajor;

    /*联系方式*/
    private String applicantContact;

    /*邮箱*/
    private String applicantMail;

    /*捐赠目的*/
    private String applicantPurpose;
}
