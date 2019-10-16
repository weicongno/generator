package com.weicongno.generator.doclever.bean;

/**
 * 请求结果实体类
 * @version 1.0 createTime:2019/10/16 09:45
 * @author:weicong
 */
public class OutParamBean {

    /**
     * 字段名
     */
    private String name;

    /**
     * 类型:0-字符串
     */
    private Integer type;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否必须：1-必须，0不必须
     */
    private Integer must;

    /**
     * mock
     */
    private String mock;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getMust() {
        return must;
    }

    public void setMust(Integer must) {
        this.must = must;
    }

    public String getMock() {
        return mock;
    }

    public void setMock(String mock) {
        this.mock = mock;
    }
}
