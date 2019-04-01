package com.winterchen.service;

import com.winterchen.model.Donation;
import org.springframework.stereotype.Service;

/**
 * @Author: liuzipan
 * @Description
 * @Date :15:49 2019/2/20
 * @Modefied By:
 */
@Service
public interface DonationService {
    /*
    * 捐赠信息存储
    * */
    int donationKeep(int userId, int donProperty, String donItem, int donAmount);


    /*
     * 根据用户ID对行程信息查询
     * */
    Donation donationSelectByuserId(int userId);

    /*
     * 根据用户Id修改行程信息
     * */
    int donationUpdateByuserId(int userId, int donProperty, String donItem, int donAmount);

    /*
    * 查询上月捐赠金额
    * */
    int findAmountLastMonth();

    /*
     * 查询合计捐赠金额
     * */
    int findAmountTotal();
}
