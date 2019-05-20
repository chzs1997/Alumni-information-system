package com.winterchen.util;

import java.util.*;

/**
 * @Author: liuzipan
 * @Description 相关度排序
 * @Date :23:59 2019/5/17
 * @Modefied By:
 */
public class IntegrityRank {

    public static HashMap<String,Double> rank(int state, Map<String,Double> hashMap){
        //1、按顺序保存map中的元素，使用LinkedList类型
        List<Map.Entry<String, Double>> keyList = new LinkedList<Map.Entry<String, Double>>(hashMap.entrySet());
        //2、按照自定义的规则排序
        Collections.sort(keyList, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1,
                               Map.Entry<String, Double> o2) {
                if(state == 1){
                    if (o2.getValue().compareTo(o1.getValue()) > 0) {
                        return 1;
                    } else if (o2.getValue().compareTo(o1.getValue()) < 0) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
                else{
                    if (o2.getValue().compareTo(o1.getValue()) > 0) {
                        return -1;
                    } else if (o2.getValue().compareTo(o1.getValue()) < 0) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        });
        //3、将LinkedList按照排序好的结果，存入到HashMap中
        HashMap<String, Double> result = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : keyList) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
