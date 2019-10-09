package com.weicongno.generator.commons.util;


import org.apache.commons.lang.StringUtils;

/**
 * @version 1.0 createTime:2019/6/25 11:57
 * @authro:weicong
 */
@Deprecated
public class CRUDNameCreateor {

    /**
     * bean的包名
     */
    public static final String BEAN_PACKAGE_NAME = "bean";

    /**
     * bean的类名称后缀
     */
    public static final String BEAN_CLASS_SUFFIX = "Bean";

    /**
     * dao层的包名
     */
    public static final String DAO_PACKAGE_NAME = "mapper";

    /**
     * dao实现类的包名
     */
    public static final String DAO_IMP_PACKAGE_NAME = "mapper.impl";

    /**
     * 添加方法的前缀
     */
    public static final String INSERT_METHOD_NAME_PREFIX = "add";

    /**
     * 修改方法的前缀
     */
    public static final String UPDATE_METHOD_NAME_PREFIX = "update";

    /**
     * 删除方法的前缀
     */
    public static final String DELETE_METHOD_NAME_PREFIX = "delete";

    /**
     * 删除方法的后缀
     */
    public static final String DELETE_METHOD_NAME_SUFFIX = "ById";

    /**
     * 查询方法的前缀
     */
    public static final String SELECT_METHOD_NAME_PREFIX = "query";

    /**
     * 查询方法的后缀
     */
    public static final String SELECT_METHOD_NAME_SUFFIX = "ByParam";

    /**
     * 创建CRUD的名称信息
     * @param packagePreifx
     * @param beanName
     * @param serivceName
     * @return
     */
    public static CRUDNameBean createCRUDNameBean(String packagePreifx, String beanName, String serivceName){
        if(StringUtils.isBlank(packagePreifx)){
            packagePreifx = "com";
        }
        if(StringUtils.isBlank(beanName)){
            beanName = "";
        }
        if(StringUtils.isBlank(serivceName)){
            serivceName = "empty_service_name";
        }
        CRUDNameBean crudNameBean = new CRUDNameBean();
        beanName = firstLetterToUpperCast(beanName);

        //如果bean名后面携带后缀去掉
        if(beanName.endsWith(BEAN_CLASS_SUFFIX)){
            beanName = beanName.substring(0, beanName.length() - BEAN_CLASS_SUFFIX.length());
        }

        //开头大写的bean名
        String cBeanName = capitalizeFirstLetter(beanName);

        crudNameBean.setPackagePrefix(packagePreifx);
        crudNameBean.setBeanName(cBeanName + BEAN_CLASS_SUFFIX);
        crudNameBean.setObjName(beanName);
        crudNameBean.setBeanPackage(packagePreifx + "." +
                BEAN_PACKAGE_NAME + "." +
                serivceName);
        crudNameBean.setBeanClassName(crudNameBean.getBeanPackage() + "." + crudNameBean.getBeanName());
        crudNameBean.setInsertMethodName(INSERT_METHOD_NAME_PREFIX + cBeanName );
        crudNameBean.setUpdateMethodName(UPDATE_METHOD_NAME_PREFIX + cBeanName);
        crudNameBean.setDeleteMethodName(DELETE_METHOD_NAME_PREFIX + cBeanName + DELETE_METHOD_NAME_SUFFIX);
        crudNameBean.setSelectMethodName(SELECT_METHOD_NAME_PREFIX + cBeanName + SELECT_METHOD_NAME_SUFFIX);

        crudNameBean.setDaoInterfacePakcage(packagePreifx + "." +
                DAO_PACKAGE_NAME + "." +
                serivceName);
        crudNameBean.setDaoInterfaceName(cBeanName + capitalizeFirstLetter(DAO_PACKAGE_NAME));
        return crudNameBean;
    }

    /**
     * 开头大写
     * @param data
     * @return
     */
    public static String capitalizeFirstLetter(String data) {
        String firstLetter = data.substring(0,1).toUpperCase();
        String restLetters = data.substring(1);
        return firstLetter + restLetters;
    }

    /**
     * 开头字母小写
     * @param data
     * @return
     */
    public static String firstLetterToUpperCast(String data){
        String firstLetter = data.substring(0,1).toLowerCase();
        String restLetters = data.substring(1);
        return firstLetter + restLetters;
    }
}