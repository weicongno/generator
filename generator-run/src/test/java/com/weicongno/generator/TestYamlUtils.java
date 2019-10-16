package com.weicongno.generator;

import com.weicongno.generator.commons.config.bean.ConfigBean;
import com.weicongno.generator.commons.yaml.YamlUtils;
import org.junit.Test;

import java.util.Map;

/**
 * @version 1.0 createTime:2019/9/17 17:38
 * @author:weicong
 */
public class TestYamlUtils {

    @Test
    public void test(){
        YamlUtils yamlUtils = new YamlUtils();
        Map map = yamlUtils.parseYaml("generator.yml.bak");
        System.out.println(map);
    }

    @Test
    public void test1(){
        YamlUtils yamlUtils = new YamlUtils();
        Object o = yamlUtils.parseYamlAs("generator.yml.bak", ConfigBean.class);
        System.out.println(o);
    }
}
