package com.weicongno.generator.commons.db.util;

import com.weicongno.generator.commons.config.YamlConfig;
import com.weicongno.generator.commons.config.bean.ConfigBean;
import com.weicongno.generator.commons.config.bean.JdbcBean;
import com.weicongno.generator.commons.util.Convert;
import com.weicongno.generator.commons.yaml.YamlUtils;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据库连接的工具类
 * @version 1.0 createTime:2019/5/7 15:28
 * @authro:weicong
 */
public class DbUtils {

    /**
     * 获取jdbc的连接
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException, IOException {
        ConfigBean configBean = YamlConfig.getConfig();
        JdbcBean jdbcBean = configBean.getJdbc();
        String dbUrl = jdbcBean.getUrl();
        String dbUserName = jdbcBean.getUsername();
        String dbPassword = jdbcBean.getPassword();
        String jdbcName = jdbcBean.getDriver();
        Class.forName(jdbcName);
        Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
        return con;
    }

    /**
     * 获取结果集
     * @param co
     * @return
     */
    public static ResultSet getResultSet(PreparedStatement co) throws SQLException {
        ResultSet rs = co.executeQuery();
        return rs;
    }

    /**
     * 关闭连接
     * @param con
     * @throws SQLException
     */
    public static void closeCon(Connection con) throws SQLException{
        if(con!=null){
            con.close();
        }
    }

    /**
     * 查询
     * @param sql
     * @param connection
     * @param parameters
     * @return
     * @throws SQLException
     */
    public static List<Map<String, Object>> query(String sql, Connection connection, Object ...parameters) throws SQLException {
        PreparedStatement preparedStatement = preparedStatement(sql, connection, parameters);
        ResultSet resultSet = getResultSet(preparedStatement);
        return resultSetToMapList(resultSet);
    }

    /**
     * 查询
     * @param sql
     * @param columns 需要获取的字段
     * @param connection
     * @param parameters
     * @return
     */
    public static List<Map<String, Object>> query(String sql, List<String> columns, Connection connection, Object ... parameters) throws SQLException {
        PreparedStatement preparedStatement = preparedStatement(sql, connection, parameters);
        ResultSet resultSet = getResultSet(preparedStatement);
        return resultSetToMapList(resultSet, columns);
    }

    /**
     * 获取一个PreparedStatement
     * @param sql
     * @param co
     * @param parameters
     * @return
     * @throws SQLException
     */
    public static PreparedStatement preparedStatement(String sql,Connection co,Object ...parameters) throws SQLException {
        PreparedStatement ps = co.prepareStatement(sql);
        for(int i = 0;i < parameters.length;i++){
            ps.setObject(i + 1, parameters[i]);
        }
        return ps;
    }

    /**
     * 将ResultSet转换成Map的list
     * @param resultSet
     * @param columns 需要获取的字段
     * @return
     */
    public static List<Map<String, Object>> resultSetToMapList(ResultSet resultSet, List<String> columns) throws SQLException {
        if(null == resultSet){
            return null;
        }
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> item;//每一行的数据
        Object value;//值
        while(resultSet.next()){
            item = new HashMap<String, Object>();
            for(String column : columns){
                value = resultSet.getObject(column);
                item.put(column, value);
            }
            result.add(item);
        }
        return result;
    }

    /**
     * 将ResultSet转换成Map的list
     * @param resultSet
     * @return
     */
    public static List<Map<String, Object>> resultSetToMapList(ResultSet resultSet) throws SQLException {
        if(null == resultSet){
            return null;
        }
        ResultSetMetaData metaData = resultSet.getMetaData();

        //列数
        int rowCount = metaData.getColumnCount();

        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        Map<String, Object> item;//每一行的数据
        String columnName;//列名
        Object value;//值
        while(resultSet.next()){
            item = new HashMap<String, Object>();
            for (int i = 0; i < rowCount; i++){
                columnName = metaData.getColumnName(i + 1);
                value = resultSet.getObject(columnName);
                item.put(columnName, value);
            }
            result.add(item);
        }
        return result;
    }
}
