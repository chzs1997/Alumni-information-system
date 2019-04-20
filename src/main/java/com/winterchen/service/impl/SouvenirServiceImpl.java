package com.winterchen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.winterchen.dao.SouvenirDao;
import com.winterchen.model.Souvenir;
import com.winterchen.service.SouvenirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: liuzipan
 * @Description
 * @Date :21:41 2019/4/19
 * @Modefied By:
 */
@Service(value = "SouvenirService")
public class SouvenirServiceImpl implements SouvenirService {

    @Autowired
    private SouvenirDao souvenirDao;

    @Override
    public PageInfo<Souvenir> findAllSouvenir(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<Souvenir> souvenir = souvenirDao.selectAllSouvenir();
        PageInfo result = new PageInfo(souvenir);
        return result;
    }

    @Override
    public int praiseAdd(int souvenirId, int souvenirPraise) {
        return souvenirDao.praiseAdd(souvenirId, souvenirPraise);
    }

    @Override
    public Object selectCounts(int souvenirId) {
        Souvenir i = souvenirDao.selectCounts(souvenirId);
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("souvenirPraiseCounts",i.getSouvenirPraiseCounts());
        return hashMap;
    }
}
