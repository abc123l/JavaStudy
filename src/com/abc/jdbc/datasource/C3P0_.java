package com.abc.jdbc.datasource;

import com.abc.jdbc.utils.JDBCUtils;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author abc
 * @version 1.0
 * C3P0使用
 */
public class C3P0_ {
    /**
     * 方式1
     * @throws Exception
     */
    @Test
    public void testC3P0_01() throws Exception {
        //创建一个数据源对象
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        //通过配置文件获取相关信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src//mysql.properties"));
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        //设置数据源对象的参数
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setPassword(password);
        //设置初始化连接数
        comboPooledDataSource.setInitialPoolSize(10);
        comboPooledDataSource.setMaxPoolSize(50);
        long start = System.currentTimeMillis();
        //测试连接时间，耗时518ms
        for (int i = 0; i < 5000; i++) {
            Connection connection = comboPooledDataSource.getConnection();
            connection.close();
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    /** 简便方式连接，通过配置文件
     * 将c3p0-config.xml拷贝到src目录下
     * 该文件指定了连接数据库和连接池的相关参数
     * 速度也非常快
     */
    @Test
    public void testC3P0_02() throws SQLException {
        ComboPooledDataSource link = new ComboPooledDataSource("link");
        Connection connection = link.getConnection();
        connection.close();//记得关闭java到连接池的连接
    }
}
