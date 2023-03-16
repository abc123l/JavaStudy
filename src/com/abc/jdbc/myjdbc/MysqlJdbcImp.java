package com.abc.jdbc.myjdbc;

/**
 * @author abc
 * @version 1.0
 * 模拟mysql数据库实现了JDBC接口，这个文件相当于jar包
 */
public class MysqlJdbcImp implements JDBCInterface{

    @Override
    public void getConnection() {
        System.out.println("数据库已连接");
    }

    @Override
    public void crud() {
        System.out.println("增删改查");
    }

    @Override
    public void close() {
        System.out.println("关闭连接");
    }
}
