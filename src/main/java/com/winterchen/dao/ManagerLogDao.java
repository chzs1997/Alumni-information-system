package com.winterchen.dao;

import com.winterchen.model.ManagerLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: liuzipan
 * @Description
 * @Date :19:03 2019/4/2
 * @Modefied By:
 */
@Repository
public interface ManagerLogDao {

    //登陆日志存储
    int save(@Param("managerId") Integer managerId
             , @Param("managerName") String managerName
             ,@Param("managerIdentity") String managerIdentity
             ,@Param("managerGender") String managerGender);

    //查询近期登陆管理员
    List<ManagerLog> selectUsers();
}
