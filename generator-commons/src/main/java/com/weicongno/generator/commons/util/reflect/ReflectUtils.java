package com.weicongno.generator.commons.util.reflect;

import java.lang.reflect.Field;

/**
 * 反射的工具类
 * @version 1.0 createTime:2019/10/2 14:23
 * @author:weicong
 */
public class ReflectUtils {

    /**
     * 获取对象中的属性
     * @param target
     * @param propertyName
     * @param <T>
     * @return
     */
    public static <T>T getProperty(Object target, String propertyName) throws NoSuchFieldException, IllegalAccessException {
        if(null == target){
            return null;
        }
        Class clazz = target.getClass();
        Field field = clazz.getDeclaredField(propertyName);
        field.setAccessible(true);
        return (T) field.get(target);
    }
}
