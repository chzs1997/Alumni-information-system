package com.winterchen.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: liuzipan
 * @Description
 * @Date :10:26 2019/5/1
 * @Modefied By:
 */
@Repository
public interface CompanyDao {


    /**
     *
     * 查找最后一个企业ID
     * */
    int findCompanyId();

    /**
     *
     * 插入一条企业信息
     * */
    int insCompany(@Param("companyName") String companyName
                  ,@Param("companyImg") String companyImg
                  ,@Param("companyIntroduction") String companyIntroduction
                  ,@Param("companyHref") String companyHref);
}
