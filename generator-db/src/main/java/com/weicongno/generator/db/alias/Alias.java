package com.weicongno.generator.db.alias;

import com.weicongno.generator.db.bean.FieldBean;
import com.weicongno.generator.db.bean.TableBean;

/**
 * @version 1.0 createTime:2019/6/24 11:28
 * @authro:weicong
 */
public interface Alias {

    /**
     * 获取别名
     * @param fieldBean
     * @return
     */
    public String getAlias(FieldBean fieldBean);
}
