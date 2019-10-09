package com.weicongno.generator.db.util;

import com.weicongno.generator.commons.util.PropertiesUtils;
import com.weicongno.generator.db.bean.FieldBean;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 数据库字段的处理工具类
 * @version 1.0 createTime:2019/6/15 00:13
 * @authro:weicong
 */
public class FieldUtils {

    /**
     * 分隔数据类型的参数
     * @param dataTypeParam
     * @return
     */
    public static List<String> splitDataTypeParams(String dataTypeParam){
        if(StringUtils.isBlank(dataTypeParam)){
            return null;
        }
        String[] params = dataTypeParam.split(",");
        if(0 == params.length){
            return null;
        }
        List<String> results = new ArrayList<>(params.length);
        for(String param : params){
            param = formatDataTypeParam(param);
            if(null == param){
                continue;
            }
            results.add(param);
        }
        return results;
    }

    /**
     * 对数据类型的参数进行格式化，比如双引号和单引号去掉
     * @param dataTypeParam
     * @return
     */
    public static String formatDataTypeParam(String dataTypeParam){
        if(StringUtils.isBlank(dataTypeParam)){
            return null;
        }
        dataTypeParam = dataTypeParam.trim();
        if(0 == dataTypeParam.length()){
            return null;
        }
        String[] symbols = {"'", "\""};//需要过滤掉的符号
        for(String symbol : symbols){
            dataTypeParam = dataTypeParam.replaceAll(symbol, "");
        }
        return dataTypeParam;
    }

    /**
     * 设置字段的数据类型信息，包括数据类型和长度等信息
     * @param dataType
     * @param dataTypeParams
     * @param fieldBean
     * @return
     */
    public static String setFieldTypeInfo(String dataType, List<String> dataTypeParams, FieldBean fieldBean){

        return null;
    }
}
