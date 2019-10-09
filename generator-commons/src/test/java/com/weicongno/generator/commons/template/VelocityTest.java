package com.weicongno.generator.commons.template;

import com.weicongno.generator.commons.template.velocity.VelocityTemplateEngine;
import org.junit.Test;

import java.io.StringWriter;

/**
 * @version 1.0 createTime:2019/5/7 14:28
 * @authro:weicong
 */
public class VelocityTest {

    @Test
    public void testVelocity(){
        TemplateEngine templateEngine = new VelocityTemplateEngine("test.vm");
        templateEngine.put("text", "HelloMyNameIsYeGuiHong");
        templateEngine.put("letterUtils", new LetterUtils());
        StringWriter writer = templateEngine.writer();
        System.out.println(writer.toString());
    }

    @Test
    public void testLetterUtils(){
        LetterUtils letterUtils = new LetterUtils();
        System.out.println(letterUtils.isUpperLetter('a'));
    }
}

