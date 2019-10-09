package com.weicongno.generator.java.util;

/**
 * 数据类型枚举类
 * @version 1.0 createTime:2019/6/25 17:07
 * @authro:weicong
 */
public enum DataTypeEnum {

    /**
     * 小数使用的数据类型
     */
    DECIMAL("java.math.BigDecimal"),

    /**
     * 整数使用的数据类型
     */
    INTEGER("java.lang.Integer"),

    /**
     * 日期使用的数据类型
     */
    DATE("java.util.Date"),


    /**
     * 字符串使用的数据类型
     */
    STRING("java.lang.String"),

    /**
     * 布尔使用的数据类型
     */
    BOOLEAN("java.lang.Boolean");

    DataTypeEnum(String dataType) {
        this.dataType = dataType;
    }

    /**
     * 数据类型
     */
    private String dataType;

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
