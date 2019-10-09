package com.weicongno.generator.commons.config;

import com.weicongno.generator.commons.config.bean.ConfigBean;
import com.weicongno.generator.commons.yaml.YamlUtils;

import java.util.Map;

/**
 * @version 1.0 createTime:2019/9/27 11:57
 * @author:weicong
 */
public class YamlConfig {

    /**
     * 配置文件路径
     */
    public static final String CONFIG_PATH = "generator.yml";

    /**
     * 配置文件的信息
     */
    public static ConfigBean config;

    /**
     * 获取配置文件
     * @return
     */
    public synchronized static ConfigBean getConfig(){
        if(null == config){
            YamlUtils yamlUtils = new YamlUtils();
            config = yamlUtils.parseYamlAs(CONFIG_PATH, ConfigBean.class);
        }
        return config;
    }

}
