package com.winterchen.service;

import com.winterchen.model.Donation;
import com.winterchen.model.DonationProject;
import com.winterchen.modelVO.DonationShowVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: liuzipan
 * @Description
 * @Date :15:49 2019/2/20
 * @Modefied By:
 */
@Service
public interface DonationService {
    /**
    * 捐赠信息存储
    * */
    int donationKeep(int userId, int donProperty, String donItem, int donAmount);


    /**
     * 根据用户ID对行程信息查询
     * */
    Donation donationSelectByuserId(int userId);

    /**
     * 根据用户Id修改行程信息
     * */
    int donationUpdateByuserId(int userId, int donProperty, String donItem, int donAmount);

    /**
    * 查询上月捐赠金额
    * */
    int findAmountLastMonth();

    /**
     * 查询合计捐赠金额
     * */
    int findAmountTotal();

    /**
    * 查询近5个月实名捐赠数量
    * */
    Map<Integer,Integer> findAmountLast5Months();

    /**
     * 查询近5个月匿名捐赠数量
     * */
    Map<Integer,Integer> findAmountLast5MonthsAnonymous();

    /**
     *
     * 捐赠信息公示
     * */
    List<DonationShowVO> donationShow(Integer num);

    /**
     *
     * 查询所有用户捐赠情况
     * */

    List<DonationProject> selectAllDonation();


    /**
     *
     * 添加用户捐赠信息
     * */

    int addUserDonation(String userMail, Integer donationAmount, String donationMajor, String applicantPurpose);

    /**
     * 查询某个邮箱最后一条数据
     * */
    DonationProject selectNewDonation(String userMail);

    /**
     * 编辑某个捐赠数据
     * */
    int editUserDonation(Integer donationAmount, String donationMajor, String applicantPurpose, Integer ProjectId);

    /**
     * 删除某个捐赠数据
     * */
    int deleteUserDonation(Integer projectId);

}
