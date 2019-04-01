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

    //用户登陆
    Manager login(String managerName, String managerPassword);
}
