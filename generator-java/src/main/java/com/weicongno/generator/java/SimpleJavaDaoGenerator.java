package com.weicongno.generator.java;

import com.weicongno.generator.commons.template.TemplateEngine;
import com.weicongno.generator.commons.template.velocity.VelocityTemplateEngine;
import com.weicongno.generator.commons.util.CRUDNameBean;
import com.weicongno.generator.db.bean.TableBean;
import org.apache.commons.collections.CollectionUtils;

import java.io.Writer;

/**
 * 简单的java dao层代码生成
 * @version 1.0 createTime:2019/6/26 11:47
 * @authro weicong
 */
public class SimpleJavaDaoGenerator implements JavaDaoGenerator{


    @Override
    public Writer getJavaDaoResource(TableBean tableBean, CRUDNameBean crudNameBean) {
        if(null == tableBean){
            throw new RuntimeException("tableBean is null");
        }
        if(CollectionUtils.isEmpty(tableBean.getFields())){
            throw new RuntimeException("fields is empty");
        }
        TemplateEngine templateEngine = getTemplateEngine("templates/java/dao.vm");
        templateEngine.put("crudNameBean", crudNameBean);
        return templateEngine.writer();
    }

    /**
     * 生成一个模板引擎的生成模板
     * @param path
     * @return
     */
    public TemplateEngine getTemplateEngine(String path){
        return new VelocityTemplateEngine(path);
    }
}
