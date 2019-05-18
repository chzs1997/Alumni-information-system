package com.winterchen.modelVO;

import lombok.Data;

/**
 * @Author: liuzipan
 * @Description 校友推荐信息
 * @Date :22:02 2019/5/4
 * @Modefied By:
 */
@Data
public class UserRecommendVO {

    /*校友ID*/
    private Integer userId;

    /*校友名*/
    private String userName;

    /*校友年级*/
    private String userGrade;

    /*校友专业*/
    private String userMajor;

    /*校友工作省份*/
    private String userWorkProvince;

    /*校友工作城市*/
    private String userWorkCity;

    /*校友工作公司*/
    private String userCompany;

    /*校友工作职位*/
    private String userPosition;

    /*校友邮箱*/
    private String userMail;

    /*联系方式*/
    private String phone;

    /*校友微信号*/
    private String userWX;

    /*相关度得分*/
    private double userScore;

}
