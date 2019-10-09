package com.weicongno.generator.commons.config;

import com.weicongno.generator.commons.util.PropertiesUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * 配置文件
 * @version 1.0 createTime:2019/6/26 16:17
 * @authro:weicong
 */
public class PropertiesConfig {

    /**
     * 配置文件的路径
     */
    public static String configPath = "generator.properties";

    /**
     * 配置文件
     */
    public static Properties config = null;

    /**
     * 获取配置文件
     * @param configPath
     * @return
     */
    public synchronized static Properties getConfig(String configPath){
        if(null != config){
            return config;
        }
        try {
            config = PropertiesUtils.loadProperties(configPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return config;
    }

    /**
     * 获取配置文件
     * @return
     */
    public static Properties getConfig(){
        return getConfig(configPath);
    }

    /**
     * 获取yaml的配置文件信息
     * @return
     */
    public static Map getYamlConfig(){
        return null;
    }
}
