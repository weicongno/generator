package com.weicongno.generator.db.alias;

import com.weicongno.generator.db.alias.util.AliasConfig;
import com.weicongno.generator.db.alias.util.AliasUtils;
import com.weicongno.generator.db.bean.FieldBean;

/**
 * 简单的别名实现
 * @version 1.0 createTime:2019/6/24 11:30
 * @authro:weicong
 */
public class SimpleAlias implements Alias{

    @Override
    public String getAlias(FieldBean fieldBean) {
        AliasConfig aliasConfig = new AliasConfig();
        String fieldName = fieldBean.getFieldName();
        //是否转换成小写形式
        if(aliasConfig.isLowerCase()){
            fieldName = fieldName.toLowerCase();
        }

        //驼峰式转换
        if (aliasConfig.isUnderlineCast()){
            fieldName = AliasUtils.underlineCast(fieldName);
        }
        return fieldName;
    }

}
