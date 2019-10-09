package com.weicongno.generator.commons.template.velocity;

import com.weicongno.generator.commons.template.TemplateEngine;
import com.weicongno.generator.commons.template.enums.LoaderMode;
import com.weicongno.generator.commons.template.velocity.util.VelocityUtils;

import java.io.StringWriter;
import java.util.Map;

/**
 * 基于velocity的模板引擎
 * @version 1.0 createTime:2019/5/7 14:24
 * @authro:weicong
 */
public class VelocityTemplateEngine implements TemplateEngine {
    private VelocityUtils velocityUtils;

    /**
     * @param tempPath 模板文件的路径
     */
    public VelocityTemplateEngine(String tempPath) {
        velocityUtils = new VelocityUtils(tempPath);
    }

    /**
     *
     * @param mode
     * @param rootPath
     * @param temPath
     */
    public VelocityTemplateEngine(LoaderMode mode, String rootPath, String temPath){
        velocityUtils = new VelocityUtils(mode, rootPath, temPath);
    }

    @Override
    public void put(String key, Object param) {
        velocityUtils.put(key, param);
    }

    @Override
    public StringWriter writer() {
        StringWriter w = new StringWriter();
        velocityUtils.writerTemplate(w);
        return w;
    }

    @Override
    public void put(Map<String, Object> param) {
        velocityUtils.put(param);
    }

}
