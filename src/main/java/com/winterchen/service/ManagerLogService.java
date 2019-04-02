package com.winterchen.service;

import com.github.pagehelper.PageInfo;
import com.winterchen.model.ManagerLog;
import org.springframework.stereotype.Service;

/**
 * @Author: liuzipan
 * @Description
 * @Date :19:34 2019/4/2
 * @Modefied By:
 */
@Service
public interface ManagerLogService {

    //登陆日志存储
    int save(Integer managerId,String managerName,String managerIdentity, String managerGender);

    //最近登陆情况查询
    PageInfo<ManagerLog> selectLog(int pageNum, int pageSize);
}
