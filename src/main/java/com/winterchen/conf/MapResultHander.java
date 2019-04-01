package com.winterchen.conf;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;


import java.util.HashMap;
import java.util.Map;

/**
 * @Author: liuzipan
 * @Description
 * @Date :19:43 2019/3/31
 * @Modefied By:
 */
public class MapResultHander implements ResultHandler {
    @SuppressWarnings("rawtypes")
    private final Map mappendResults = new HashMap();

    @SuppressWarnings("unchecked")
    @Override
    public void handleResult(ResultContext context){
        @SuppressWarnings("rawtypes")
        Map map = (Map)context.getResultObject();
        mappendResults.put(map.get("key"),map.get("value")); //xml 配置里面的property的值对应的列
    }

    public Map getMappendResults(){
        return mappendResults;
    }
}
