package com.winterchen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.dao.LoginLogDao;
import com.winterchen.model.LoginLog;
import com.winterchen.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liuzipan
 * @Description
 * @Date :19:33 2019/4/2
 * @Modefied By:
 */
@Service(value = "LoginLogService")
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogDao loginLogDao;

    @Override
    public int save(Integer userId,String userName) {
        int i = loginLogDao.save(userId,userName);
        return i;
    }

    @Override
    public PageInfo<LoginLog> selectLog(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<LoginLog> loginUser = loginLogDao.selectUsers();
        PageInfo result = new PageInfo(loginUser);
        return result;
    }

    @Override
    public LoginLog addFailure(String userName) {
        loginLogDao.addFailure(userName);
        return loginLogDao.findState(userName);
    }

    @Override
    public int insert(Integer userId, String userName) {
        return loginLogDao.insert(userId, userName);
    }

    @Override
    public int addNewUser(String userMail) {
        return loginLogDao.addNewUser(userMail);
    }
}
