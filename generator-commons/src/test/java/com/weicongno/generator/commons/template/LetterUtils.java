package com.weicongno.generator.commons.template;

/**
 * @version 1.0 createTime:2019/5/7 15:03
 * @authro:weicong
 */
public class LetterUtils {
    /**
     * 判断是否是大写字母
     * @param c
     * @return
     */
    public boolean isUpperLetter(char c){
        //判断是否是字母
        if(Character.isLetter(c)){
            return Character.isUpperCase(c);
        }
        return false;
    }
}
