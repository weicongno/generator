package com.weicongno.generator.commons.json;

import com.alibaba.fastjson.JSON;

/**
 * @version 1.0 createTime:2019/10/16 10:25
 * @author:weicong
 */
public class JsonUtils {

    /**
     * 对象转json
     * @param object
     * @return
     */
    public static String toString(Object object){
        return JSON.toJSONString(object);
    }
}
