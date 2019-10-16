package com.weicongno.generator.java.util;

import com.weicongno.generator.db.bean.FieldBean;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 字段的工具类
 * @version 1.0 createTime:2019/6/25 17:05
 * @authro:weicong
 */
public class FieldUtils {

    /**
     * 数据库的数据类型对应的java的数据类型
     */
    public static final Map<String, DataTypeEnum> DB_DATA_TYPE_MAP = new HashMap<>();

    static{
        DB_DATA_TYPE_MAP.put("VARCHAR", DataTypeEnum.STRING);
        DB_DATA_TYPE_MAP.put("VARCHAR2", DataTypeEnum.STRING);
        DB_DATA_TYPE_MAP.put("CHAR", DataTypeEnum.STRING);
        DB_DATA_TYPE_MAP.put("CLOB", DataTypeEnum.STRING);
        DB_DATA_TYPE_MAP.put("LONG", DataTypeEnum.STRING);
        DB_DATA_TYPE_MAP.put("BLOB", DataTypeEnum.STRING);
        DB_DATA_TYPE_MAP.put("NVARCHAR2", DataTypeEnum.STRING);
        DB_DATA_TYPE_MAP.put("NVARCHAR", DataTypeEnum.STRING);
        DB_DATA_TYPE_MAP.put("ENUM", DataTypeEnum.STRING);
        DB_DATA_TYPE_MAP.put("BINARY", DataTypeEnum.STRING);
        DB_DATA_TYPE_MAP.put("VARBINARY", DataTypeEnum.STRING);
        DB_DATA_TYPE_MAP.put("TEXT", DataTypeEnum.STRING);
        DB_DATA_TYPE_MAP.put("BIT", DataTypeEnum.STRING);

        DB_DATA_TYPE_MAP.put("INT", DataTypeEnum.INTEGER);
        DB_DATA_TYPE_MAP.put("NUMBER", DataTypeEnum.INTEGER);
        DB_DATA_TYPE_MAP.put("BIGINT", DataTypeEnum.LONG);
        DB_DATA_TYPE_MAP.put("TINYINT", DataTypeEnum.INTEGER);


        DB_DATA_TYPE_MAP.put("DOUBLE", DataTypeEnum.DECIMAL);
        DB_DATA_TYPE_MAP.put("FLOAT", DataTypeEnum.DECIMAL);
        DB_DATA_TYPE_MAP.put("DECIMAL", DataTypeEnum.DECIMAL);


        DB_DATA_TYPE_MAP.put("DATETIME", DataTypeEnum.DATE);
        DB_DATA_TYPE_MAP.put("DATE", DataTypeEnum.DATE);
        DB_DATA_TYPE_MAP.put("TIMESTAMP", DataTypeEnum.DATE);
        DB_DATA_TYPE_MAP.put("TIME", DataTypeEnum.DATE);

        DB_DATA_TYPE_MAP.put("BOOL", DataTypeEnum.BOOLEAN);
        DB_DATA_TYPE_MAP.put("BOOLEAN", DataTypeEnum.BOOLEAN);
    }

    /**
     * 对数据库中的数据类型进行转换
     * @param fieldBean
     * @return
     */
    public static String castDataType(FieldBean fieldBean){
        if(null == fieldBean){
            return null;
        }
        if(StringUtils.isEmpty(fieldBean.getDataType())){
            throw new RuntimeException("数据类型为空！" + fieldBean.getFieldName());
        }
        String dataType = fieldBean.getDataType().toUpperCase();
        DataTypeEnum dataTypeEnum = DB_DATA_TYPE_MAP.get(dataType);

        if(DataTypeEnum.INTEGER.equals(dataTypeEnum)){
            if(null != fieldBean.getScale()){
                dataTypeEnum = DataTypeEnum.DECIMAL;
            }
        }

        return dataTypeEnum.getDataType();
    }
}
