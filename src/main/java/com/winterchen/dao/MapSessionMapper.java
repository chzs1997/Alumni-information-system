package com.winterchen.dao;

import com.winterchen.conf.MapResultHander;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author: liuzipan
 * @Description  特殊映射处理
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

    @SuppressWarnings("unchecked")
    public Map<Integer,Integer> findAmountLast5Months(){
        MapResultHander handler = new MapResultHander();

        this.getSqlSession().select(DonationDao.class.getName()+".findAmountLast5Months",handler);
        Map<Integer,Integer> map = handler.getMappendResults();
        return map;
    }

    @SuppressWarnings("unchecked")
    public Map<Integer,Integer> findAmountLast5MonthsAnonymous(){
        MapResultHander handler = new MapResultHander();

        this.getSqlSession().select(DonationDao.class.getName()+".findAmountLast5MonthsAnonymous",handler);
        Map<Integer,Integer> map = handler.getMappendResults();
        return map;
    }

    @SuppressWarnings("findNewsCommentCountsLastMonth")
    public Map<Integer,Integer> findNewsCommentCountsLastMonth(){
        MapResultHander handler = new MapResultHander();
        this.getSqlSession().select(NewsDao.class.getName()+".findNewsCommentCountsLastMonth",handler);
        Map<Integer,Integer> map = handler.getMappendResults();
        return map;
    }

    @SuppressWarnings("findNewsViewCountsLastMonth")
    public Map<Integer,Integer> findNewsViewCountsLastMonth(){
        MapResultHander handler = new MapResultHander();
        this.getSqlSession().select(NewsDao.class.getName()+".findNewsViewCountsLastMonth",handler);
        Map<Integer,Integer> map = handler.getMappendResults();
        return map;
    }

    @SuppressWarnings("findIntegrityCount")
    public Map<String,Integer> findIntegrityCount(){
        MapResultHander handler = new MapResultHander();
        this.getSqlSession().select(UserIntegrityDao.class.getName()+".findIntegrityCount",handler);
        Map<String,Integer> map = handler.getMappendResults();
        return map;
    }

    @SuppressWarnings("findIntegrityBetter")
    public Map<String,Double> findIntegrityBetter(){
        MapResultHander handler = new MapResultHander();
        this.getSqlSession().select(UserIntegrityDao.class.getName()+".findIntegrityBetter",handler);
        Map<String,Double> map = handler.getMappendResults();
        return map;
    }

    @SuppressWarnings("findIntegrityWorse")
    public Map<String,Double> findIntegrityWorse(){
        MapResultHander handler = new MapResultHander();
        this.getSqlSession().select(UserIntegrityDao.class.getName()+".findIntegrityWorse",handler);
        Map<String,Double> map = handler.getMappendResults();
        return map;
    }
}
