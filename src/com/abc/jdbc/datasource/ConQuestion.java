package com.abc.jdbc.datasource;

import com.abc.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author abc
 * @version 1.0
 */
public class ConQuestion {
    /**
     * 传统方式连接数据库5000次
     * @throws SQLException
     */
    @Test
    public void testCon() throws SQLException {
        //非常耗时
        for (int i = 0; i < 5000; i++) {
            Connection connection = JDBCUtils.getConnection();
            JDBCUtils.close(null,null,connection);
        }

    }
}
