package com.winterchen.dao;

import com.winterchen.modelVO.DonationShowVO;
import com.winterchen.modelVO.UserDonationVO;
import com.winterchen.modelVO.UserRecommendVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liuzipan
 * @Description
 * @Date :22:22 2019/5/3
 * @Modefied By:
 */
@Repository
public interface DonationProjectDao {

    /**
     * 捐赠立项
     * */
    Integer projectApplicant(@Param("applicantName") String applicantName
                            ,@Param("donationAmount")  Integer donationAmount
                            ,@Param("donationMajor")  String donationMajor
                            ,@Param("applicantContact")  String  applicantContact
                            ,@Param("applicantMail")   String applicantMail
                            ,@Param("applicantPurpose")    String applicantPurpose);

    /**
     *
     * 个人捐赠查询
     * */
    List<UserDonationVO> findDonationByUserId(@Param("userId") Integer userId);


    /**
     * 查询上月捐赠金额
     * */
    int findAmountLastMonth();

    /**
     * 查询合计捐赠金额
     * */
    int findAmountTotal();

    /**
     *
     * 捐赠信息公示
     * */
    List<DonationShowVO> donationShow(@Param("num") Integer num);
}
