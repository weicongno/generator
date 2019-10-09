package com.weicongno.generator.commons.template;

import java.io.StringWriter;
import java.util.Map;

/**
 * 生成代码的模板引擎类
 * @version 1.0 createTime:2019/5/7 14:18
 * @authro:weicong
 */
public interface TemplateEngine {
    /**
     * 向模板引擎注入参数
     * @param key
     * @param param
     */
    void put(String key, Object param);

    /**
     * 向模板引擎注入参数
     * @param param
     */
    void put(Map<String, Object> param);

    /**
     * 输出
     * @return
     */
    StringWriter writer();
}
