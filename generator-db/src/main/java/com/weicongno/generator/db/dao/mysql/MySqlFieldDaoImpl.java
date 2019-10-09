package com.weicongno.generator.db.dao.mysql;

import com.weicongno.generator.commons.util.Convert;
import com.weicongno.generator.db.bean.FieldBean;
import com.weicongno.generator.db.dao.AbstractFieldDao;
import com.weicongno.generator.db.util.FieldUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * mysql查询字段信息的实现类
 * @version 1.0 createTime:2019/5/7 23:06
 * @authro:weicong
 */
public class MySqlFieldDaoImpl extends AbstractFieldDao {

    public static final String QUERY_SQL_PREFIX = "SHOW FULL COLUMNS FROM ";

    public static final List<String> COLUMN_LIST = Arrays.asList(new String[]{"Field", "Type", "Null", "Key", "Comment"});

    @Override
    public List<FieldBean> queryFieldByTableName(String tableName) throws Exception{
        String sql = getQueryFieldSQL(tableName);
        Connection connection = getConnection();

        List<Map<String, Object>> data = query(sql, connection, COLUMN_LIST);
        List<FieldBean> results = new ArrayList<>(data.size());
        for(Map<String, Object> row : data){
            results.add(convertMapToFieldBean(row));
        }
        return results;
    }

    @Override
    protected FieldBean convertMapToFieldBean(Map<String, Object> map) {
        String fieldName = Convert.toString(map.get("Field"));
        String type = Convert.toString(map.get("Type"));
        String _Null = Convert.toString(map.get("Null"));
        String key = Convert.toString(map.get("Key"));
        String comment = Convert.toString(map.get("Comment"));

        FieldBean fieldBean = new FieldBean();
        fieldBean.setFieldName(fieldName);
        setDataTypeInfo(type, fieldBean);
        fieldBean.setCommonet(comment);
        fieldBean.setNullAble(MySQLFieldConstants.NULL_YES.equals(_Null));
        fieldBean.setPrimaryKey(MySQLFieldConstants.KEY_PRIMARY.equals(key));
        return fieldBean;
    }


    /**
     * 通过类型来设置字段的数据类型信息
     * @param type
     * @param fieldBean
     */
    public void setDataTypeInfo(String type, FieldBean fieldBean){
        if(StringUtils.isBlank(type)){
            return;
        }
        //左括号
        int leftBr = type.indexOf("(");
        //右括号
        int rightBr = type.indexOf(")");
        if(-1 == leftBr && -1 != rightBr){
            throw new RuntimeException("Not validate data type " + type);
        }
        if(-1 == leftBr){
            fieldBean.setDataType(type);
            return;
        }
        String dataTypeParam = type.substring(leftBr + 1, rightBr);
        String dataType = type.substring(0, leftBr);
        List<String> list = FieldUtils.splitDataTypeParams(dataTypeParam);
        fieldBean.setDataType(dataType);
        //如果是枚举类型 或者 没有指定长度
        if("ENUM".equals(dataType.toUpperCase()) || CollectionUtils.isEmpty(list)){
            return;
        }

        Integer length = Convert.toInteger(list.get(0));
        fieldBean.setLength(length);

        //如果参数大于1那么可能就是小数类型(已排除枚举类型)
        if(list.size() > 1){
            Integer scale = Convert.toInteger(list.get(1));
            fieldBean.setScale(scale);
        }
    }

    /**
     * 获取查询表内字段信息的sql
     * @param tableName
     * @return
     */
    public String getQueryFieldSQL(String tableName){
        return QUERY_SQL_PREFIX + tableName;
    }
}
