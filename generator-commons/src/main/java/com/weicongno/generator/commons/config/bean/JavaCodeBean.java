package com.weicongno.generator.commons.config.bean;

import java.util.Map;

/**
 * @version 1.0 createTime:2019/9/27 15:39
 * @author:weicong
 */
public class JavaCodeBean {

    private Map<String, String> mapper;

    private Map<String, String> method;

    private Map<String, String> bean;

    public Map<String, String> getMapper() {
        return mapper;
    }

    public void setMapper(Map<String, String> mapper) {
        this.mapper = mapper;
    }

    public Map<String, String> getMethod() {
        return method;
    }

    public void setMethod(Map<String, String> method) {
        this.method = method;
    }

    public Map<String, String> getBean() {
        return bean;
    }

    public void setBean(Map<String, String> bean) {
        this.bean = bean;
    }
}
