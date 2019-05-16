package com.winterchen.service.impl;

import com.winterchen.dao.CompanyDao;
import com.winterchen.dao.EntertainmentDao;
import com.winterchen.dao.ManagerDao;
import com.winterchen.dao.SouvenirDao;
import com.winterchen.model.Manager;
import com.winterchen.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: liuzipan
 * @Description
 * @Date :15:17 2019/3/31
 * @Modefied By:
 */
@Service(value = "ManagerService")
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private EntertainmentDao entertainmentDao;

    @Autowired
    private SouvenirDao souvenirDao;

    @Autowired
    private CompanyDao companyDao;


    @Override
    public Manager login(String managerName, String managerPassword) {
        return managerDao.login(managerName, managerPassword);
    }

    @Override
    public int findEntertainmentId() {
        return entertainmentDao.findEntertainmentId();
    }

    @Override
    public int insEntertainment(int serviceType, String serviceTitle, String serviceAddress, String serviceContact, String imgName, String serviceIntroduction) {
        return entertainmentDao.insEntertainment(serviceType, serviceTitle, serviceAddress, serviceContact, imgName, serviceIntroduction);
    }

    @Override
    public int findSouvenirId() {
        return souvenirDao.findSouvenirId();
    }

    @Override
    public int insSouvenir(String serviceTitle, String imgName, String serviceTime, String serviceIntroduction) {
        return souvenirDao.insSouvenir(serviceTitle, imgName, serviceTime, serviceIntroduction);
    }

    @Override
    public int findCompanyId() {
        return companyDao.findCompanyId();
    }

    @Override
    public int insCompany(String serviceTitle, String imgName, String serviceIntroduction, String serviceHref) {
        return companyDao.insCompany(serviceTitle, imgName, serviceIntroduction, serviceHref);
    }

    @Override
    public int alterPassword(int managerId, String managerPassword) {
        return managerDao.alterPassword(managerId, managerPassword);
    }
}
