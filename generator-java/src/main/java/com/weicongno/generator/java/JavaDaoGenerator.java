package com.weicongno.generator.java;

import com.weicongno.generator.commons.util.CRUDNameBean;
import com.weicongno.generator.db.bean.TableBean;

import java.io.Writer;

/**
 * java的dao层接口生成
 * @version 1.0 createTime:2019/6/26 11:43
 * @authro:weicong
 */
public interface JavaDaoGenerator {
    /**
     * 获取java的dao层接口的生成后的资源
     * @param tableBean
     * @param crudNameBean
     * @return
     */
    Writer getJavaDaoResource(TableBean tableBean, CRUDNameBean crudNameBean);
}
