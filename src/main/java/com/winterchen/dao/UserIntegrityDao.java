package com.winterchen.dao;

import com.winterchen.model.SchoolUserIntegrity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: liuzipan
 * @Description
 * @Date :15:52 2019/4/22
 * @Modefied By:
 */
@Repository
public interface UserIntegrityDao {

    /**
     *
     *
     * 插入一条新的记录*/
    int addUser(@Param("userName") String userName
               ,@Param("password") String password
               ,@Param("phone") String phone
               ,@Param("userMail") String userMail);

    /**
     *
     * 检测信息是否填写
     * */
    int renewInfo();

    /**
     *
     * 计算完整度
     * */
    int countIntegrity();

    /**
     *
     *
     * 检查校友信息度
     * */
    SchoolUserIntegrity getUserIntegrity(@Param("userId") Integer userId);



}
