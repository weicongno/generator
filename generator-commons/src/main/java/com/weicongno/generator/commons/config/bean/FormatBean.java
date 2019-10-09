package com.weicongno.generator.commons.config.bean;

/**
 * 代码生成的格式实体类
 * @version 1.0 createTime:2019/9/27 15:36
 * @author:weicong
 */
public class FormatBean {

    /**
     * 是否开启驼峰式
     */
    private Boolean underlineCast;

    /**
     * 是否全部转换成小写
     */
    private Boolean lowerCast;

    /**
     * java代码中的部分
     */
    private JavaCodeBean javaCode;

    /**
     * 是否启用Mybatis-plus
     */
    private Boolean enabledMybatisPlus;

    public Boolean getUnderlineCast() {
        return underlineCast;
    }

    public void setUnderlineCast(Boolean underlineCast) {
        this.underlineCast = underlineCast;
    }

    public Boolean getLowerCast() {
        return lowerCast;
    }

    public void setLowerCast(Boolean lowerCast) {
        this.lowerCast = lowerCast;
    }

    public JavaCodeBean getJavaCode() {
        return javaCode;
    }

    public void setJavaCode(JavaCodeBean javaCode) {
        this.javaCode = javaCode;
    }

    public Boolean getEnabledMybatisPlus() {
        return enabledMybatisPlus;
    }

    public void setEnabledMybatisPlus(Boolean enabledMybatisPlus) {
        this.enabledMybatisPlus = enabledMybatisPlus;
    }
}
