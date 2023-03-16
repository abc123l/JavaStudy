package com.abc.jdbc.transaction;

import com.abc.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author abc
 * @version 1.0
 */
public class Transaction_ {
    @Test
    public void withoutTran() throws Exception {
        Connection connection = JDBCUtils.getConnection();//默认情况下，connection自动提交
        String sql1="update account set money=money+100 where id=1";
        String sql2="update account set money=money-100 where id=2";
        PreparedStatement preparedStatement = connection.prepareStatement(sql1);
        preparedStatement.executeUpdate();
        int i=1/0;//到这路碰到异常后面代码未执行
        preparedStatement=connection.prepareStatement(sql2);
        preparedStatement.executeUpdate();
        JDBCUtils.close(null,preparedStatement,connection);
    }
    @Test
    public void useTransaction() {
        Connection connection=null;
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);//相当于开启事务

            String sql1="update account set money=money+100 where id=1";
            String sql2="update account set money=money-100 where id=2";

            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.executeUpdate();
            //int i=1/0;//抛出异常
            preparedStatement=connection.prepareStatement(sql2);
            preparedStatement.executeUpdate();

            connection.commit();
            JDBCUtils.close(null,preparedStatement,connection);
        } catch (SQLException e) {
            //这里有机会回滚默认回滚到事务开始状态
            System.out.println("有异常发生，回滚撤销执行的sql");
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
}
