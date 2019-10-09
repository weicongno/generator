package com.weicongno.generator.commons.config.bean;

import java.util.Map;

/**
 * 配置的实体类
 * @version 1.0 createTime:2019/9/27 15:00
 * @author:weicong
 */
public class ConfigBean {

    /**
     * jdbc连接信息
     */
    private JdbcBean jdbc;

    private Map<String, Object> global;

    private FormatBean format;

    public JdbcBean getJdbc() {
        return jdbc;
    }

    public void setJdbc(JdbcBean jdbc) {
        this.jdbc = jdbc;
    }

    public Map<String, Object> getGlobal() {
        return global;
    }

    public void setGlobal(Map<String, Object> global) {
        this.global = global;
    }

    public FormatBean getFormat() {
        return format;
    }

    public void setFormat(FormatBean format) {
        this.format = format;
    }
}
