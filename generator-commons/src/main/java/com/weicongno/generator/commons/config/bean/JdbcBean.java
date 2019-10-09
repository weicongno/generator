package com.weicongno.generator.commons.config.bean;

/**
 * @version 1.0 createTime:2019/9/27 14:59
 * @author:weicong
 */
public class JdbcBean {

    /**
     * jdbc驱动
     */
    private String driver;

    /**
     * 连接地址
     */
    private String url;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
