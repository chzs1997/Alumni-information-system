package com.winterchen.service.impl;

import com.winterchen.dao.ManagerDao;
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


    @Override
    public Manager login(String managerName, String managerPassword) {
        return managerDao.login(managerName, managerPassword);
    }
}
