package com.weicongno.generator.cglib;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0 createTime:2019/9/25 15:53
 * @author:weicong
 */
public class Utils {

    public static List<Class> getGenericInterfaces(Class clazz, Class interfaceClass){
        Type[] types = clazz.getGenericInterfaces();
        if(null == types){
            return null;
        }
        if(0 == types.length){
            return null;
        }

        List<Class> result = new ArrayList<Class>(types.length);
        ParameterizedType each;
        for(Type type : types){
            if(type instanceof ParameterizedType){
                each = (ParameterizedType) type;
                if(each.getTypeName().equals(interfaceClass.getName())){
                    return getActualTypeArguments(each);
                }
            }
        }
        return null;
    }



    public static List<Class> getActualTypeArguments(ParameterizedType parameterizedType){
        Type[] typeArr = parameterizedType.getActualTypeArguments();
        if(null == typeArr){
            return null;
        }
        if(0 == typeArr.length){
            return null;
        }
        List<Class> result = new ArrayList<Class>();
        for(Type type : typeArr){
            result.add(type.getClass());
        }
        return result;
    }

    public static void main(String[] args){
        List list = getGenericInterfaces(UserServiceImpl.class, UserService.class);
        System.out.println(list);
    }
}
