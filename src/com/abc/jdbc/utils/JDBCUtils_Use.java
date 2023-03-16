package com.abc.jdbc.utils;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author abc
 * @version 1.0
 */
public class JDBCUtils_Use {

    /**
     * 测试dml
     * @throws SQLException
     */
    @Test
    public void test() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql="update admin set pwd=? where `name`=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,"123");
        preparedStatement.setString(2,"abc");

        int affectedRows = preparedStatement.executeUpdate();
        System.out.println(affectedRows>0? "修改成功":"修改失败");
        JDBCUtils.close(null,preparedStatement,connection);
    }

    /**
     * select语句
     * @throws SQLException
     */
    @Test
    public void test01() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection.getClass());
        String sql="select * from dept";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int deptno = resultSet.getInt("deptno");
            String dname = resultSet.getString("dname");
            String loc = resultSet.getString("loc");
            System.out.println(deptno+"\t"+dname+"\t"+loc);
        }
        JDBCUtils.close(resultSet,preparedStatement,connection);

    }
}
