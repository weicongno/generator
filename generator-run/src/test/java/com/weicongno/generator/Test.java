package com.weicongno.generator;

import com.weicongno.generator.commons.parsing.GenericTokenParser;
import com.weicongno.generator.commons.parsing.TokenHandler;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @version 1.0 createTime:2019/9/17 16:58
 * @author:weicong
 */
public class Test {
/*
    public static void main(String[] args){
        GenericTokenParser genericTokenParser = new GenericTokenParser("{", "}", new Test.SimpleTokenHandler());
        String text = genericTokenParser.parse("你好{name},今天{name}天气不错");
        System.out.println(text);
    }
*/

    static class SimpleTokenHandler implements TokenHandler{

        @Override
        public String handleToken(String var1) {
            System.out.println(var1);
            return "null";
        }
    }

    public static void main(String[] args){
        List<String> list = Arrays.asList(new String[]{"1", "2", "3", "4", "5"});
        for(String str : list){
            System.out.println(str);
        }
    }
}
