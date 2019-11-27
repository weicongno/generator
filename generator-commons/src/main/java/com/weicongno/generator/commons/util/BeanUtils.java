package com.weicongno.generator.commons.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Bean的工具类
 * @version 1.0 createTime:2019/5/7 23:14
 * @authro:weicong
 */
public class BeanUtils {

    /**
     * 将map转换成bean
     * @param map
     * @param beanClass
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     */
    public static <T>T mapToBean(Map<String, Object> map, Class<T> beanClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        T bean;
        bean = beanClass.newInstance();
        org.apache.commons.beanutils.BeanUtils.populate(bean, map);
        return bean;
    }
}
