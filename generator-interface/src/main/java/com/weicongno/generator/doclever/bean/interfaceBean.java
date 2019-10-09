package com.weicongno.generator.doclever.bean;

import java.util.List;

/**
 * DOClever接口的bean
 * @version 1.0 createTime:2019/8/15 10:57
 * @author:weicong
 */
public class interfaceBean {

    /**
     * 标志
     */
    private String flag;

    /**
     * 参数
     */
    private List<Object> param;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 请求的url
     */
    private String url;

    /**
     * 备注
     */
    private String remark;

    /**
     * 请求方式
     */
    private String method;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<Object> getParam() {
        return param;
    }

    public void setParam(List<Object> param) {
        this.param = param;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
