package com.abc.jdbc.myjdbc;

/**
 * @author abc
 * @version 1.0
 * 模拟完成对mysql的操作
 */
public class TestJdbc {
    public static void main(String[] args) {
        //用接口调用方法，动态绑定
        JDBCInterface jdbcInterface = new MysqlJdbcImp();
        jdbcInterface.getConnection();
        jdbcInterface.crud();
        jdbcInterface.close();
    }
}
