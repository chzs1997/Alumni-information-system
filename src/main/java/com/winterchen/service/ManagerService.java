package com.winterchen.service;

import com.winterchen.model.Manager;
import org.springframework.stereotype.Service;

/**
 * @Author: liuzipan
 * @Description
 * @Date :15:16 2019/3/31
 * @Modefied By:
 */
@Service
public interface ManagerService {

    /***
    *用户登陆
    */
    Manager login(String managerName, String managerPassword);

    /**
     * 查找最后一条娱乐ID
     * */
     int findEntertainmentId();

     /**
      *
      * 插入一条周边食宿信息
      * */
     int insEntertainment(int serviceType, String serviceTitle, String serviceAddress, String serviceContact, String imgName, String serviceIntroduction);


    /**
     * 查找最后一个纪念品ID
     * *
     */
     int findSouvenirId();

     /**
      *
      * 插入一条新的纪念品信息
      * */
     int insSouvenir(String serviceTitle, String imgName, String serviceTime, String serviceIntroduction);

     /**
      *
      * 查找最后一个企业ID
      * */
     int findCompanyId();

     /**
      *
      * 插入一条企业信息
      * */
     int insCompany(String serviceTitle, String imgName, String serviceIntroduction, String serviceHref);

     /**
      * 密码修改
      * */
     int alterPassword(int managerId,String managerPassword);


}
