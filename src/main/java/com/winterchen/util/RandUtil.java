package com.winterchen.util;

import java.util.Random;

/**
 * @author liuzipan
 * @date 1/21/2019 8:21 PM
 */
public class RandUtil {
    public static String getRandNum(){
        String randNum = new Random().nextInt(1000000)+"";
        System.out.println("生成"+randNum);
        if(randNum.length()!=6){
            return getRandNum();
        }
        return randNum;
    }
}
