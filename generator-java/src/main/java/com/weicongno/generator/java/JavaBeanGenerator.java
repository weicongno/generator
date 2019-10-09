package com.weicongno.generator.java;

import com.weicongno.generator.commons.util.CRUDNameBean;
import com.weicongno.generator.db.bean.TableBean;

import java.io.Writer;

/**
 * java的bean代码生成器
 * @version 1.0 createTime:2019/6/25 16:48
 * @authro:weicong
 */
public interface JavaBeanGenerator {

    /**
     * 获取javabean的生成后的资源
     * @param tableBean
     * @param crudNameBean
     * @return
     */
    Writer getJavaBeanResource(TableBean tableBean, CRUDNameBean crudNameBean);
}
