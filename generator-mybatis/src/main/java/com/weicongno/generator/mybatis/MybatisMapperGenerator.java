package com.weicongno.generator.mybatis;

import com.weicongno.generator.commons.util.CRUDNameBean;
import com.weicongno.generator.db.bean.TableBean;

import java.io.InputStream;
import java.io.Writer;

/**
 * Mybatis的mapper文件生成接口
 * @version 1.0 createTime:2019/5/21 22:46
 * @authro:weicong
 */
public interface MybatisMapperGenerator {

    /**
     * 获得一个mapper的流
     * @param tableBean
     * @param crudNameBean
     * @return
     */
    Writer getMapperResource(TableBean tableBean, CRUDNameBean crudNameBean);
}