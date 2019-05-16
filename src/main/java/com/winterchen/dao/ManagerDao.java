package com.winterchen.dao;

import com.winterchen.model.Manager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: liuzipan
 * @Description
 * @Date :15:18 2019/3/31
 * @Modefied By:
 */
@Repository
public interface ManagerDao {

    /**
     *     用户登陆
     */
    Manager login(@Param("managerName") String managerName
                  ,@Param("managerPassword") String managerPassword);

    /**
     * 密码修改
     * */
    int alterPassword(@Param("managerId") int managerId,@Param("managerPassword") String managerPassword);
}
