package com.weicongno.generator.java.bean;

import java.util.List;

/**
 * java中的字段信息
 * @version 1.0 createTime:2019/6/25 17:00
 * @authro:weicong
 */
public class FieldBean {

    /**
     * 字段名称
     */
    private String fieldName;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 注释
     */
    private String comment;

    /**
     * 数据类型包名
     */
    private String dataTypePackage;

    private List<AnnotationBean> annotationList;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<AnnotationBean> getAnnotationList() {
        return annotationList;
    }

    public void setAnnotationList(List<AnnotationBean> annotationList) {
        this.annotationList = annotationList;
    }

    public String getDataTypePackage() {
        return dataTypePackage;
    }

    public void setDataTypePackage(String dataTypePackage) {
        this.dataTypePackage = dataTypePackage;
    }
}
