package com.weicongno.generator.commons.template;

import com.weicongno.generator.commons.template.enums.LoaderMode;
import com.weicongno.generator.commons.template.velocity.VelocityTemplateEngine;

/**
 * 模板引擎建立者
 * @version 1.0 createTime:2019/6/13 09:58
 * @authro:weicong
 */
public class TemplateEngineBuilder {

    /**
     * 生成一个模板引擎的对象
     * @param temPath
     * @return
     */
    public static TemplateEngine buildTemplateEngine(String temPath){
        return new VelocityTemplateEngine(temPath);
    }

    /**
     * 生成一个模板引擎的对象
     * @param loaderMode
     * @param rootPath
     * @param temPath
     * @return
     */
    public static TemplateEngine buildTemplateEngine(LoaderMode loaderMode, String rootPath, String temPath){
        return new VelocityTemplateEngine(loaderMode, rootPath, temPath);
    }
}
