package com.winterchen.util;

import com.winterchen.modelVO.UserRecommendVO;

import java.util.List;

/**
 * @Author: liuzipan
 * @Description
 * @Date :23:31 2019/5/4
 * @Modefied By:
 */
public class CorrelationCal {

    public static List<UserRecommendVO> Correlation(UserRecommendVO user, List<UserRecommendVO> userRecommend){
        String userSelf [] = {user.getUserGrade(),user.getUserMajor(),user.getUserWorkProvince(),user.getUserWorkCity(),user.getUserCompany(),user.getUserPosition()};

        for(int i = 0;i<userRecommend.size();i++){
            int similarity[] = {0,0,0,0,0,0};
            double sum = 0.0;
            String userRecom [] = {userRecommend.get(i).getUserGrade()
                                  ,userRecommend.get(i).getUserMajor()
                                  ,userRecommend.get(i).getUserWorkProvince()
                                  ,userRecommend.get(i).getUserWorkCity()
                                  ,userRecommend.get(i).getUserCompany()
                                  ,userRecommend.get(i).getUserPosition()};
            for (int j = 0;j<6;j++){
                System.out.println("自身："+userSelf[j]);
                System.out.println("推荐对象："+userRecom[j]);
                if(userSelf[j].equals(userRecom[j])){
                    similarity[j] = 1;
                }
                System.out.println(similarity[j]);
            }
            sum = similarity[0]*0.0833+similarity[1]*0.25+similarity[2]*0.0741+similarity[3]*0.1481+similarity[4]*0.2963+similarity[5]*0.1481;
            userRecommend.get(i).setUserScore(sum);
        }
        return userRecommend;
    }
}
