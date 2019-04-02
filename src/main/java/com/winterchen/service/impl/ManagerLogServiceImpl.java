package com.winterchen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.dao.ManagerLogDao;
import com.winterchen.model.ManagerLog;
import com.winterchen.service.ManagerLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuzipan
 * @Description
 * @Date :19:34 2019/4/2
 * @Modefied By:
 */
@Service(value = "ManagerLogService")
public class ManagerLogServiceImpl implements ManagerLogService {

    @Autowired
    private ManagerLogDao managerLogDao;

    @Override
    public int save(Integer managerId, String managerName, String managerIdentity, String managerGender) {
        return managerLogDao.save(managerId,managerName,managerIdentity,managerGender);
    }

    @Override
    public PageInfo<ManagerLog> selectLog(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<ManagerLog> loginUser = managerLogDao.selectUsers();
        PageInfo result = new PageInfo(loginUser);
        return result;
    }
}
