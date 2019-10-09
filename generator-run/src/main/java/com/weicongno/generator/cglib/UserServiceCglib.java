package com.weicongno.generator.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/**
 * @version 1.0 createTime:2019/9/25 14:44
 * @author:weicong
 */
public class UserServiceCglib implements MethodInterceptor {
    private Object target;

    public Object getInstance(Object target) {
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 设置回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    /**
     * 实现MethodInterceptor接口中重写的方法
     *
     * 回调方法
     */
    @Override
    public Object intercept(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("事务开始。。。");
        Object result = proxy.invokeSuper(object, args);
        System.out.println(method.getDeclaringClass());
        /*Class[] arr = method.getDeclaringClass().getInterfaces();
        Class clazz = arr[0];
        TypeVariable<Class>[] types = clazz.getTypeParameters();
        System.out.println(types[0]);
        System.out.println(types[1]);*/
        Type[] type=method.getDeclaringClass().getGenericInterfaces();
        System.out.println(type);
//ParameterizedType参数化类型，即泛型
        ParameterizedType p=(ParameterizedType)type[0];
//getActualTypeArguments获取参数化类型的数组，泛型可能有多个
        Class c=(Class) p.getActualTypeArguments()[0];
        System.out.println(c);
        System.out.println(p.getActualTypeArguments()[1]);
        System.out.println("事务结束。。。");
        return result;
    }
}
