package com.winterchen.model;

import lombok.Data;

/**
 * @Author: liuzipan
 * @Description 管理员
 * @Date :15:13 2019/3/31
 * @Modefied By:
 */
@Data
public class Manager {

    /*管理员id*/
    private Integer managerId;

    /*管理员姓名*/
    private String managerName;

    /*管理员性别*/
    private String managerGender;

    /*管理员密码*/
    private String managerPassword;

    /*管理员身份*/
    private String managerIdentity;


}
