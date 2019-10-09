package com.weicongno.generator.db.bean;

import com.weicongno.generator.db.alias.Alias;

/**
 * 数据库中表的字段bean
 * @version 1.0 createTime:2019/5/7 14:06
 * @authro:weicong
 */
public class FieldBean {

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 字段的备注
     */
    private String commonet;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 长度
     */
    private Integer length;

    /**
     * 一般指number类型,后面的长度
     */
    private Integer scale;

    /**
     * 是否允许为null
     */
    private boolean nullAble;

    /**
     * 是否是主键
     */
    private boolean isPrimaryKey;

    /**
     * 字段别名设置
     */
    private String alias;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getCommonet() {
        return commonet;
    }

    public void setCommonet(String commonet) {
        this.commonet = commonet;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public boolean isNullAble() {
        return nullAble;
    }

    public void setNullAble(boolean nullAble) {
        this.nullAble = nullAble;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
