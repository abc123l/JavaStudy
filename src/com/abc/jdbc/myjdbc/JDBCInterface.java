package com.abc.jdbc.myjdbc;

/**
 * @author abc
 * @version 1.0
 * java厂商规定的接口
 */
public interface JDBCInterface {
    public void getConnection();
    public void crud();
    public void close();
}
