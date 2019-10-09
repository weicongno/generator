package com.weicongno.generator.db.dao.oracle;

import com.weicongno.generator.db.dao.AbstractFieldDao;
import com.weicongno.generator.commons.util.Convert;
import com.weicongno.generator.db.bean.FieldBean;

import java.util.List;
import java.util.Map;

/**
 * oracle的字段
 * @version 1.0 createTime:2019/5/14 21:53
 * @authro:weicong
 */
public class OracleFieldDaoImpl extends AbstractFieldDao {

    public static final String QUERY_FIELD_SQL =
            "SELECT" +
            "    COL.COLUMN_NAME \"fieldName\"," +
            "    COL.DATA_TYPE \"dataType\"," +
            "    COL.DATA_LENGTH \"length\"," +
            "    COL.DATA_PRECISION \"precision\"," +
            "    COL.DATA_SCALE \"scale\"," +
            "    COL.NULLABLE," +
            "    CO.COMMENTS \"commonet\"," +
            "    CON.CONSTRAINT_TYPE " +
            "FROM" +
            "   (" +
            "       SELECT " +
            "           * " +
            "       FROM " +
            "           USER_TAB_COLS " +
            "       WHERE " +
            "            TABLE_NAME = ?" +
            "   ) COL " +
            "    LEFT JOIN (SELECT * FROM USER_COL_COMMENTS WHERE TABLE_NAME = ?) CO " +
            "        ON COL.COLUMN_NAME = CO.COLUMN_NAME " +
            "" +
            "    LEFT JOIN (" +
            "        SELECT AU.CONSTRAINT_TYPE,CU.TABLE_NAME, CU.COLUMN_NAME " +
            "         FROM USER_CONS_COLUMNS CU, USER_CONSTRAINTS AU " +
            "         WHERE CU.CONSTRAINT_NAME = AU.CONSTRAINT_NAME AND AU.TABLE_NAME = CU.TABLE_NAME AND AU.TABLE_NAME = ?) CON " +
            "       ON        " +
            "           COL.COLUMN_NAME = CON.column_name";

    @Override
    protected FieldBean convertMapToFieldBean(Map<String, Object> map) {
        String fieldName = Convert.toString(map.get("fieldName"));
        String commonet = Convert.toString(map.get("commonet"));
        String dataType = Convert.toString(map.get("dataType"));
        Integer length = Convert.toInteger(map.get("length"));
        Integer precision = Convert.toInteger(map.get("precision"));
        Integer scale = Convert.toInteger(map.get("scale"));
        String constraintType = Convert.toString(map.get("CONSTRAINT_TYPE"));

        boolean isPrimaryKey = false;
        boolean isNullAble = true;

        if("P".equals(constraintType)){
            isPrimaryKey = true;
        }
        if("C".equals(constraintType)){
            isNullAble = false;
        }

        if(null != scale){
            length = precision;
        }

        FieldBean fieldBean = new FieldBean();
        fieldBean.setNullAble(isNullAble );
        fieldBean.setPrimaryKey(isPrimaryKey);
        fieldBean.setFieldName(fieldName);
        fieldBean.setCommonet(commonet);
        fieldBean.setDataType(dataType);
        fieldBean.setLength(length);
        //fieldBean.setPrecision(precision);
        fieldBean.setScale(scale);
        return fieldBean;
    }

    @Override
    public List<FieldBean> queryFieldByTableName(String tableName) throws Exception {
        return queryToFieldList(QUERY_FIELD_SQL, getConnection(), tableName, tableName, tableName);
    }
}
