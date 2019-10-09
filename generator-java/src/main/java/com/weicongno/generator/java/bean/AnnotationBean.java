package com.weicongno.generator.java.bean;


import com.weicongno.generator.commons.util.Convert;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @version 1.0 createTime:2019/10/9 11:29
 * @author:weicong
 */
public class AnnotationBean {

    /**
     * 注解名称
     */
    private String name;

    /**
     * 导包类
     */
    private String importClass;

    /**
     * 注解的参数
     */
    private List<String> paramList;

    @Override
    public String toString() {
        StringBuilder append = new StringBuilder("@");
        append.append(name);
        if(CollectionUtils.isNotEmpty(paramList)){
            append.append("(").append(Convert.listToString(getParamList(), ",")).append(")");
        }
        return append.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImportClass() {
        return importClass;
    }

    public void setImportClass(String importClass) {
        this.importClass = importClass;
    }

    public List<String> getParamList() {
        return paramList;
    }

    public void setParamList(List<String> paramList) {
        this.paramList = paramList;
    }
}
