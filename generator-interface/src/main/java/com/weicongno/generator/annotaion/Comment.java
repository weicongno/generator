package com.weicongno.generator.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 字段注释的注解类
 * @version 1.0 createTime:2019/8/15 10:38
 * @author:weicong
 */
@Target(ElementType.FIELD)
public @interface Comment {

    /**
     * 注释名
     * @return
     */
    String name() default "";
}
