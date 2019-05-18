package com.winterchen.modelVO;

import lombok.Data;

/**
 * @Author: liuzipan
 * @Description  捐赠信息公示
 * @Date :18:26 2019/5/13
 * @Modefied By:
 */
@Data
public class DonationShowVO {

    /*项目ID*/
    private Integer projectId;

    /*捐赠人姓名*/
    private String applicantName;

    /*捐赠人班级*/
    private String collective;

    /*捐赠金额*/
    private Integer donationAmount;

    /*捐赠时间*/
    private String donationTime;

}
