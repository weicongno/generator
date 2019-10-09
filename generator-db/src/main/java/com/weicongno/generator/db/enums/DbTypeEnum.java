package com.weicongno.generator.db.enums;

/**
 * 数据库类型枚举类
 * @version 1.0 createTime:2019/6/26 16:41
 * @authro:weicong
 */
public enum DbTypeEnum {
    /**
     * MYSQL5.5版本
     */
    MYSQL_5_5("MYSQL", "5.5"),

    /**
     * ORACLE 11G 版本
     */
    ORACLE_11G("ORACLE", "11G");
    /**
     * 数据库的名称
     */
    private String dbName;

    /**
     * 版本
     */
    private String version;

    DbTypeEnum(String dbName, String version){
        this.dbName = dbName;
        this.version = version;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
