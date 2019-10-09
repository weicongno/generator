package com.weicongno.generator.commons.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * properties的工具类
 * @version 1.0 createTime:2019/6/24 11:41
 * @authro:weicong
 */
public class PropertiesUtils {

    /**
     * 读取classpath中的properties文件
     * @param path
     * @return
     */
    public static Properties loadProperties(String path) throws IOException {
        InputStream inputStream = null;

        inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }

    /**
     * 加载classpath下面的properties文件
     * @param path
     * @param clazz
     * @return
     * @throws IOException
     */
    public static Properties loadProperties(String path, Class clazz)throws IOException{
        InputStream inputStream = clazz.getClassLoader().getResourceAsStream(path);
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties;
    }
}
