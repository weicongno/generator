package com.weicongno.generator.java;

import com.weicongno.generator.commons.config.YamlConfig;
import com.weicongno.generator.commons.config.bean.ConfigBean;
import com.weicongno.generator.commons.config.bean.FormatBean;
import com.weicongno.generator.commons.template.TemplateEngine;
import com.weicongno.generator.commons.template.velocity.VelocityTemplateEngine;
import com.weicongno.generator.commons.util.CRUDNameBean;
import com.weicongno.generator.db.alias.util.AliasUtils;
import com.weicongno.generator.db.bean.FieldBean;
import com.weicongno.generator.db.bean.TableBean;
import com.weicongno.generator.java.bean.AnnotationBean;
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
        templateEngine.put("classAnnotaionList", getAnnotation(tableBean));
        templateEngine.put("importClassList", getImportClassList());
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
        String type = FieldUtils.castDataType(fieldBean);
        String dataType = null;
        String dataTypePackage = null;
        Integer offset = type.lastIndexOf(".");
        //分离包名和类名
        if(-1 != offset){
            dataType = type.substring(offset + 1, type.length());
            dataTypePackage = type.substring(0, offset);
        }
        com.weicongno.generator.java.bean.FieldBean javaFieldBean = new com.weicongno.generator.java.bean.FieldBean();
        javaFieldBean.setDataType(dataType);
        javaFieldBean.setComment(fieldBean.getCommonet());
        javaFieldBean.setFieldName(fieldBean.getAlias());
        javaFieldBean.setAnnotationList(getAnnotation(fieldBean));
        return javaFieldBean;
    }

    /**
     * 获取类上的注解
     * @param tableBean
     * @return
     */
    public List<AnnotationBean> getAnnotation(TableBean tableBean){
        ConfigBean configBean = YamlConfig.getConfig();
        FormatBean formatBean = configBean.getFormat();
        List<AnnotationBean> results = new ArrayList<>();
        if(formatBean.getEnabledMybatisPlus()){
            if(null == tableBean){
                return null;
            }
            AnnotationBean annotationBean = new AnnotationBean();
            annotationBean.setImportClass("com.baomidou.mybatisplus.annotation.TableName");
            annotationBean.setName("TableName");
            List<String> paramList = new ArrayList<String>();
            paramList.add("\"" + tableBean.getTableName() + "\"");
            annotationBean.setParamList(paramList);
            results.add(annotationBean);
        }
        return results;
    }

    /**
     * 获取字段注解
     * @param fieldBean
     * @return
     */
    public List<AnnotationBean> getAnnotation(FieldBean fieldBean){
        if(null == fieldBean){
            return null;
        }
        ConfigBean configBean = YamlConfig.getConfig();
        FormatBean formatBean = configBean.getFormat();
        List<AnnotationBean> results = new ArrayList<>();
        if(formatBean.getEnabledMybatisPlus()){
            if(fieldBean.isPrimaryKey()){
                results.addAll(getMybatisPlusPrimaryKeyAnnotation(fieldBean));
            }else{
                results.addAll(getMybatisPlusFieldAnnotation(fieldBean));
            }
        }
        return results;
    }

    /**
     * 获取mybatis-plus的字段注解
     * @param fieldBean
     * @return
     */
    public List<AnnotationBean> getMybatisPlusFieldAnnotation(FieldBean fieldBean){
        if(null == fieldBean){
            return null;
        }
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setImportClass("com.baomidou.mybatisplus.annotation.TableField");
        annotationBean.setName("TableField");
        List<String> paramList = new ArrayList<String>();
        paramList.add("\"" + fieldBean.getFieldName() + "\"");
        annotationBean.setParamList(paramList);

        List<AnnotationBean> results = new ArrayList<>();
        results.add(annotationBean);
        return results;
    }

    /**
     * 获取mybatis-plus主键的注解
     * @param fieldBean
     * @return
     */
    public List<AnnotationBean> getMybatisPlusPrimaryKeyAnnotation(FieldBean fieldBean){
        if(null == fieldBean){
            return null;
        }
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setImportClass("com.baomidou.mybatisplus.annotation.TableId");
        annotationBean.setName("TableId");
        List<String> paramList = new ArrayList<String>();
        paramList.add("\"" + fieldBean.getFieldName() + "\"");
        annotationBean.setParamList(paramList);

        List<AnnotationBean> results = new ArrayList<>();
        results.add(annotationBean);
        return results;
    }

    /**
     * 获取类导包信息
     * @return
     */
    public List<String> getImportClassList(){
        ConfigBean configBean = YamlConfig.getConfig();
        FormatBean formatBean = configBean.getFormat();
        List<String> results = new ArrayList<>();
        if(formatBean.getEnabledMybatisPlus()){
            results.add("com.baomidou.mybatisplus.annotation.TableField;");
            results.add("com.baomidou.mybatisplus.annotation.TableId;");
            results.add("com.baomidou.mybatisplus.annotation.TableName;");
        }
        return results;
    }
}
