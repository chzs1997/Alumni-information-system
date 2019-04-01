package com.winterchen.dao;

import com.winterchen.model.Donation;

import java.util.List;

/**
 * @Author: liuzipan
 * @Description
 * @Date :16:01 2019/2/13
 * @Modefied By:
 */
public interface DonationDao {
    /*
     * 捐赠信息存储
     * */
    int donationKeep(int userId, int donProperty, String donItem, int donAmount);


    /*
     * 根据用户ID对捐赠信息查询
     * */
    Donation donationSelectByuserId(int userId);

    /*
     * 根据用户Id修改捐赠信息
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

    /*
     * 查询近5个月捐赠数量
     * */
    List<Donation> findAmountLast5Months();
}
