package com.winterchen.dao;


import com.winterchen.model.UserDomain;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

    /**
     *
     *
     * 插入一条新的记录*/
    int insert(UserDomain record);

    /**
     *
     *
     * 查询所有用户*/
    List<UserDomain> selectUsers();

    /**
     *
     *
     * 根据年级查找*/
    List<UserDomain> findByGrade(@Param("userGrade") String userGrade);

    /**
     *
     *
     * 根据专业查找*/
    List<UserDomain> findByMajor(@Param("userMajor") String userMajor);

    /**
     *
     * 根据性别查找*/
    List<UserDomain> findByGender(@Param("userGender") String userGender);

    /**
     *
     * 根据年级和专业查找*/
    List<UserDomain> findByGradeAndMajor(@Param("userGrade") String userGrade
                                         ,@Param("userMajor") String userMajor);

    /**
     *
     *
     * 根据性别和专业查找*/
    List<UserDomain> findByGenderAndMajor(@Param("userGender") String userGender
            ,@Param("userMajor") String userMajor);

    /**
     *
     * 根据年级和性别查找*/
    List<UserDomain> findByGenderAndGrade(@Param("userGender") String userGender
            ,@Param("userGrade") String userGrade);

    /**
     *
     *
     * 根据年级和性别和专业查找*/
    List<UserDomain> findByGradeAndMajorAndGender(@Param("userGrade") String userGrade
            ,@Param("userMajor") String userMajor
            ,@Param("userGender") String userGender);

    /**
     *
     *
     * 根据用户ID查询用户*/
    UserDomain findByUserId(@Param("userId") Integer userId);

    /**
     *
     *
     * 根据电话号码查询用户*/
    UserDomain findByPhoneNum(@Param("phoneNum") String phoneNum);

    /**
     *
     *
     * 根据用户Id修改密码*/
    int updatePassword(@Param("userId") Integer userId
                      ,@Param("password") String password);

    /**
     *
     * 注册*/
    int login(@Param("userName") String userName
             ,@Param("password") String password
             ,@Param("phone") String phone
             ,@Param("userMail") String userMail);

    /**
     *
     * 根据用户身份证号修改密码*/
    int assignUserBkey(String phone);

    /**
     *
     * 登录*/
    UserDomain check(
              @Param("userName") String userName
             ,@Param("password") String password);

    /**
     *
     *
     * 根据用户身份证号查询用户*/
    UserDomain findByuserIdNumber(@Param("userIdNumber") String userIdNumber);

    /**
     *
     *
     * 根据用户身份证号添加其他信息*/
    int add_info(
            @Param("userMail") String userMail
            ,@Param("userGender") String userGender
            ,@Param("userGrade") String userGrade
            ,@Param("userMajor") String userMajor
            ,@Param("userGraduateYear") String userGraduateYear
            ,@Param("userHeadTeacher") String userHeadTeacher
            ,@Param("userAddress") String userAddress
            ,@Param("userCompany") String userCompany
            ,@Param("userPosition") String userPosition
            ,@Param("userEducation") String userEducation
            ,@Param("userBirthPlace") String userBirthPlace);

    /**
     * 更改信息
     * */
    int updateMessage(@Param("userName") String userName
                      ,@Param("userGender") String userGender
                      ,@Param("userBirthPlace")String userBirthPlace
                      ,@Param("phone") String phone
                      ,@Param("userMail")String userMail
                      ,@Param("userStudentId")String userStudentId
                      ,@Param("userMajor")String userMajor
                      ,@Param("userGrade")String userGrade
                      ,@Param("userEducation")String userEducation
                      ,@Param("userGraduateYear")String userGraduateYear
                      ,@Param("userHeadTeacher")String userHeadTeacher
                      ,@Param("userAddress")String userAddress
                      ,@Param("userCompany")String userCompany
                      ,@Param("userPosition")String userPosition
                      ,@Param("userImage") String userImage
                      ,@Param("userId")int userId);

    /**
     * 上个月注册用户数
     */
    int findAmountLastMonth();

    /**
     * 累计注册用户数
     */
    int findAmountTotal();

    /**
     * 近一周用户注册量
     * */
    @SuppressWarnings("unchecked")
    Map<String,Integer> findUserAmountLastWeek();

    /**
     *
     * 根据用户邮箱验证*/
    UserDomain findByuserMail(@Param("userMail") String userMail);

    /**
     *
     * 遍历所有用户
     *
     * */
    List<UserDomain> findAllUser();
}