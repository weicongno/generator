package com.weicongno.generator.db.dao;

import com.weicongno.generator.db.bean.FieldBean;

import java.util.List;

/**
 * 字段的数据接口
 * @version 1.0 createTime:2019/5/7 22:46
 * @authro:weicong
 */
public interface FieldDao {

    /**
     *查询表中字段的信息
     * @param tableName
     * @return
     */
    List<FieldBean> queryFieldByTableName(String tableName) throws Exception;
}
