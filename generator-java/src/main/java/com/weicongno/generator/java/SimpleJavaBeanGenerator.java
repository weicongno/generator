package com.weicongno.generator.java;

import com.weicongno.generator.commons.template.TemplateEngine;
import com.weicongno.generator.commons.template.velocity.VelocityTemplateEngine;
import com.weicongno.generator.commons.util.CRUDNameBean;
import com.weicongno.generator.db.alias.util.AliasUtils;
import com.weicongno.generator.db.bean.FieldBean;
import com.weicongno.generator.db.bean.TableBean;
import com.weicongno.generator.java.util.FieldUtils;
import org.apache.commons.collections.CollectionUtils;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * 简单的javabean的生成器
 * @version 1.0 createTime:2019/6/25 16:52
 * @authro:weicong
 */
public class SimpleJavaBeanGenerator implements JavaBeanGenerator{
    @Override
    public Writer getJavaBeanResource(TableBean tableBean, CRUDNameBean crudNameBean) {
        if(null == tableBean){
            throw new RuntimeException("tableBean is null");
        }
        if(CollectionUtils.isEmpty(tableBean.getFields())){
            throw new RuntimeException("fields is empty");
        }
        TemplateEngine templateEngine = getTemplateEngine("templates/java/bean.vm");

        templateEngine.put("fieldList", convertDbFields(tableBean.getFields()));
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

    /**
     * 将数据库中的为字段信息进行转换
     * @param fieldList
     * @return
     */
    public List<com.weicongno.generator.java.bean.FieldBean> convertDbFields(List<FieldBean> fieldList){
        if(CollectionUtils.isEmpty(fieldList)){
            return null;
        }
        AliasUtils.setAlias(fieldList);
        List<com.weicongno.generator.java.bean.FieldBean> list = new ArrayList<>(fieldList.size());

        for(FieldBean fieldBean : fieldList){
            list.add(convertDbField(fieldBean));
        }
        return list;
    }

    /**
     * 转换数据库中的字段信息
     * @param fieldBean
     * @return
     */
    public com.weicongno.generator.java.bean.FieldBean convertDbField(FieldBean fieldBean){
        String dataType = FieldUtils.castDataType(fieldBean);
        com.weicongno.generator.java.bean.FieldBean javaFieldBean = new com.weicongno.generator.java.bean.FieldBean();
        javaFieldBean.setDataType(dataType);
        javaFieldBean.setComment(fieldBean.getCommonet());
        javaFieldBean.setFieldName(fieldBean.getAlias());
        return javaFieldBean;
    }
}
