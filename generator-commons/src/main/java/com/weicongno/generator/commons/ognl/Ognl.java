package com.weicongno.generator.commons.ognl;

import ognl.OgnlException;

/**
 * OGNL表达式的工具类
 * @version 1.0 createTime:2019/9/17 17:29
 * @author:weicong
 */
public class Ognl {

    /**
     * 获取值
     * @param exprission
     * @param var1
     * @return
     */
    public static Object getValue(String exprission, Object var1){
        try {
            return ognl.Ognl.getValue(exprission, var1);
        } catch (OgnlException e) {
            e.printStackTrace();
        }
        return null;
    }
}
