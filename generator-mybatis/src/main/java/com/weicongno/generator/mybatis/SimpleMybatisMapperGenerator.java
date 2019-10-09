package com.weicongno.generator.mybatis;

import com.weicongno.generator.commons.template.TemplateEngine;
import com.weicongno.generator.commons.template.velocity.VelocityTemplateEngine;
import com.weicongno.generator.commons.util.CRUDNameBean;
import com.weicongno.generator.commons.util.CRUDNameCreateor;
import com.weicongno.generator.db.alias.util.AliasUtils;
import com.weicongno.generator.db.bean.FieldBean;
import com.weicongno.generator.db.bean.TableBean;
import org.apache.commons.collections.CollectionUtils;

import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * 简单的mybatis mapper的生成类
 * @version 1.0 createTime:2019/5/31 16:04
 * @authro:weicong
 */
public class SimpleMybatisMapperGenerator implements MybatisMapperGenerator{

    @Override
    public Writer getMapperResource(TableBean tableBean, CRUDNameBean crudNameBean){
        if(null == tableBean){
            throw new RuntimeException("tableBean is null");
        }
        if(CollectionUtils.isEmpty(tableBean.getFields())){
            throw new RuntimeException("fields is empty");
        }
        AliasUtils.setAlias(tableBean.getFields());
        TemplateEngine templateEngine = getTemplateEngine("templates/xml/mapper/mapper.vm");
        templateEngine.put("tableAlign", tableBean.getTableName());
        templateEngine.put("tableName", tableBean.getTableName());
        templateEngine.put("fieldList", tableBean.getFields());
        templateEngine.put("crudName", crudNameBean);
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
