package com.weicongno.generator.doclever.enums;

/**
 * DOClever数据类型枚举类
 * @version 1.0 createTime:2019/10/16 10:39
 * @author:weicong
 */
public enum  DataTypeEnum {
    /**
     * 字符串
     */
    STRING(0),

    /**
     * 数字
     */
    NUMBER(1),

    /**
     * 布尔
     */
    BOOLEAN(2),

    /**
     * 数组
     */
    ARRAY(3);
    private int type;

    DataTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
