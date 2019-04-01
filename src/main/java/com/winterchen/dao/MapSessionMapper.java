package com.winterchen.dao;

import com.winterchen.conf.MapResultHander;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: liuzipan
 * @Description
 * @Date :20:07 2019/3/31
 * @Modefied By:
 */
@Repository
public class MapSessionMapper extends SqlSessionDaoSupport {

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    @SuppressWarnings("unchecked")
    public Map<Integer,Integer> findUserAmountLastWeek(){
        MapResultHander handler = new MapResultHander();

        this.getSqlSession().select(UserDao.class.getName()+".findUserAmountLastWeek",handler);
        Map<Integer,Integer> map = handler.getMappendResults();
        return map;
    }
}
