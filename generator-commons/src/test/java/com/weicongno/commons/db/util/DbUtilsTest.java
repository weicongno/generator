package com.weicongno.commons.db.util;

import com.weicongno.generator.commons.db.util.DbUtils;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @version 1.0 createTime:2019/5/21 20:28
 * @authro:weicong
 */
public class DbUtilsTest {

    @Test
    public void testConnection() throws SQLException, IOException, ClassNotFoundException {
        System.out.println(DbUtils.getConnection());
    }
}
