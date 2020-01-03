package com.weicongno.generator.commons.util;

/**
 * CRUD的基本名称bean
 * @version 1.0 createTime:2019/6/25 11:54
 * @authro:weicong
 */
public class CRUDNameBean {

    /**
     * 包名前缀
     */
    @Deprecated
    private String packagePrefix;

    /**
     * bean所在的包
     */
    private String beanPackage;

    /**
     * bean的名称（不含包名不含bean的后缀名）
     */
    private String objName;

    /**
     * bean的名称
     */
    private String beanName;

    /**
     * bean类的全部限定名
     */
    private String beanClassName;

    /**
     * 插入方法名称
     */
    private String insertMethodName;

    /**
     * 批量插入方法名称
     */
    private String insertBatchMethodName;

    /**
     * 修改方法名称
     */
    private String updateMethodName;

    /**
     * 删除方法名称
     */
    private String deleteMethodName;

    /**
     * 查询语句方法名称
     */
    private String selectMethodName;

    /**
     * dao层接口的名称
     */
    private String daoInterfaceName;

    /**
     * dao接口的所在包
     */
    private String daoInterfacePakcage;

    public String getPackagePrefix() {
        return packagePrefix;
    }

    public void setPackagePrefix(String packagePrefix) {
        this.packagePrefix = packagePrefix;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getInsertMethodName() {
        return insertMethodName;
    }

    public void setInsertMethodName(String insertMethodName) {
        this.insertMethodName = insertMethodName;
    }

    public String getUpdateMethodName() {
        return updateMethodName;
    }

    public void setUpdateMethodName(String updateMethodName) {
        this.updateMethodName = updateMethodName;
    }

    public String getDeleteMethodName() {
        return deleteMethodName;
    }

    public void setDeleteMethodName(String deleteMethodName) {
        this.deleteMethodName = deleteMethodName;
    }

    public String getSelectMethodName() {
        return selectMethodName;
    }

    public void setSelectMethodName(String selectMethodName) {
        this.selectMethodName = selectMethodName;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public String getBeanPackage() {
        return beanPackage;
    }

    public void setBeanPackage(String beanPackage) {
        this.beanPackage = beanPackage;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getDaoInterfaceName() {
        return daoInterfaceName;
    }

    public void setDaoInterfaceName(String daoInterfaceName) {
        this.daoInterfaceName = daoInterfaceName;
    }

    public String getDaoInterfacePakcage() {
        return daoInterfacePakcage;
    }

    public void setDaoInterfacePakcage(String daoInterfacePakcage) {
        this.daoInterfacePakcage = daoInterfacePakcage;
    }

    public String getInsertBatchMethodName() {
        return insertBatchMethodName;
    }

    public void setInsertBatchMethodName(String insertBatchMethodName) {
        this.insertBatchMethodName = insertBatchMethodName;
    }
}
