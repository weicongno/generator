package com.weicongno.generator.db.bean;

import java.util.List;

/**
 * 数据库表bean
 * @version 1.0 createTime:2019/5/7 14:00
 * @authro:weicong
 */
public class TableBean {

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表注释
     */
    private String commonets;

    /**
     * 表内的字段
     */
    private List<FieldBean> fields;

    /**
     * 表中标注为主键的字段
     */
    private FieldBean primaryKeyField;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCommonets() {
        return commonets;
    }

    public void setCommonets(String commonets) {
        this.commonets = commonets;
    }

    public List<FieldBean> getFields() {
        return fields;
    }

    public void setFields(List<FieldBean> fields) {
        this.fields = fields;
    }

    public FieldBean getPrimaryKeyField() {
        return primaryKeyField;
    }

    public void setPrimaryKeyField(FieldBean primaryKeyField) {
        this.primaryKeyField = primaryKeyField;
    }
}
