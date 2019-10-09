package com.weicongno.generator.db;

import com.weicongno.generator.db.bean.FieldBean;
import com.weicongno.generator.db.bean.TableBean;
import com.weicongno.generator.db.dao.FieldDao;
import com.weicongno.generator.db.dao.mysql.MySqlFieldDaoImpl;
import com.weicongno.generator.db.dao.oracle.OracleFieldDaoImpl;
import com.weicongno.generator.db.enums.DbTypeEnum;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * @version 1.0 createTime:2019/6/26 16:46
 * @authro:weicong
 */
public class DbTableReader {

    /**
     * 读取数据库中的字段信息
     * @return
     */
    public static TableBean readDbTable(String tableName, DbTypeEnum dbTypeEnum) throws Exception {
        FieldDao fieldDao = null;
        if(DbTypeEnum.MYSQL_5_5.equals(dbTypeEnum)){
            fieldDao = new MySqlFieldDaoImpl();
        }else if(DbTypeEnum.ORACLE_11G.equals(dbTypeEnum)){
            fieldDao = new OracleFieldDaoImpl();
        }else{
            throw new RuntimeException("not support database");
        }
        TableBean tableBean = new TableBean();
        List<FieldBean> fieldList = fieldDao.queryFieldByTableName(tableName);

        FieldBean primaryKeyField = getPrimaryKeyField(fieldList);

        tableBean.setFields(fieldList);
        tableBean.setTableName(tableName);
        tableBean.setPrimaryKeyField(primaryKeyField);

        return tableBean;
    }

    /**
     * 获取主键的字段
     * @param fieldList
     * @return
     */
    public static FieldBean getPrimaryKeyField(List<FieldBean> fieldList){
        if(CollectionUtils.isEmpty(fieldList)){
            return null;
        }
        for(FieldBean fieldBean : fieldList){
            if(fieldBean.isPrimaryKey()){
                return fieldBean;
            }
        }
        return null;
    }
}
