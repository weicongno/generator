package com.weicongno.generator.db.alias.util;

import com.weicongno.generator.db.alias.Alias;
import com.weicongno.generator.db.alias.SimpleAlias;
import com.weicongno.generator.db.bean.FieldBean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * 别名的工具类
 * @version 1.0 createTime:2019/6/25 10:31
 * @authro:weicong
 */
public class AliasUtils {

    /**
     * 下划线
     */
    public static final char UNDERLINE = '_';

    /**
     * 设置别名
     * @param fieldList
     * @param alias
     */
    public static void setAlias(List<FieldBean> fieldList, Alias alias){
        if(CollectionUtils.isEmpty(fieldList)){
            return;
        }
        if(null == alias){
            return;
        }
        for(FieldBean fieldBean : fieldList){
            fieldBean.setAlias(alias.getAlias(fieldBean));

        }
    }

    /**
     * 设置别名
     * @param fieldList
     */
    public static void setAlias(List<FieldBean> fieldList){
        setAlias(fieldList, new SimpleAlias());
    }

    /**
     * 下划线转换
     * @param str
     * @return
     */
    public static String underlineCast(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        StringBuilder result = new StringBuilder(str.substring(0, 1).toLowerCase());
        char item;
        for(int i = 1; i < str.length(); i++){
            item = str.charAt(i);

            if(item == UNDERLINE){
                continue;
            }
            if(0 == i){
                if(UNDERLINE != item){
                    result.append(item);
                }
                continue;
            }
            //如果上一个是下划线
            if(UNDERLINE == str.charAt(i - 1)){
                result.append(String.valueOf(item).toUpperCase());
            }else {
                result.append(item);
            }
        }
        return result.toString();
    }
}
