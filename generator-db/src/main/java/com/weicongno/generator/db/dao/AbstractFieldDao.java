package com.weicongno.generator.db.dao;

import com.weicongno.generator.commons.db.util.DbUtils;
import com.weicongno.generator.db.bean.FieldBean;
import org.apache.commons.collections.CollectionUtils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 抽象的字段查询数据类
 * @version 1.0 createTime:2019/5/7 23:12
 * @authro:weicong
 */
public abstract class AbstractFieldDao implements FieldDao {

    /**
     * 获取jdbc的连接
     * @return
     * @throws Exception
     */
    protected Connection getConnection() throws SQLException, ClassNotFoundException, IOException {
        return DbUtils.getConnection();
    }

    /**
     * 查询
     * @param sql
     * @param connection
     * @param param
     * @return
     */
    protected List<Map<String, Object>> query(String sql, Connection connection, Object ...param) throws SQLException {
        return DbUtils.query(sql, connection, param);
    }

    /**
     * 查询
     * @param sql
     * @param connection
     * @param columns
     * @param param
     * @return
     */
    protected List<Map<String, Object>> query(String sql, Connection connection, List<String> columns, Object ...param) throws SQLException {
        return DbUtils.query(sql, columns, connection, param);
    }

    /**
     * 查询并且转换成Field的集合
     * @param sql
     * @param connection
     * @param param
     * @return
     * @throws Exception
     */
    protected List<FieldBean> queryToFieldList(String sql, Connection connection, Object ...param) throws Exception {
        List<Map<String, Object>> list = query(sql, connection, param);
        return convertToFieldList(list);
    }

    /**
     * 将map的集合转换成Field的集合
     * @param list
     * @return
     * @throws Exception
     */
    protected  List<FieldBean> convertToFieldList(List<Map<String, Object>> list) throws Exception {
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        FieldBean fieldBean;
        List<FieldBean> fieldList = new ArrayList<>(list.size());
        for(Map<String, Object> map : list){
            fieldBean = convertMapToFieldBean(map);
            fieldList.add(fieldBean);
        }
        return fieldList;
    }

    /**
     * 格式化map中的FieldBean信息
     * @param map
     * @return
     */
    abstract protected FieldBean convertMapToFieldBean(Map<String, Object> map);
}
