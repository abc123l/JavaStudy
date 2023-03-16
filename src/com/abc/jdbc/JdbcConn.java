package com.abc.jdbc;

import com.mysql.jdbc.Driver;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author abc
 * @version 1.0
 * 连接的5种方式
 */
public class JdbcConn {
    @Test
    public void connect01() throws SQLException {
        Driver driver = new Driver();
        String url="jdbc:mysql://localhost:3306/abc_db02";
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","abc");
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }

    /**
     * 反射是动态加载，减少依赖性
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws SQLException
     */
    @Test
    public void connect02() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver o = (Driver)aClass.newInstance();
        String url="jdbc:mysql://localhost:3306/abc_db02";
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","abc");
        Connection connect = o.connect(url, properties);
        System.out.println(connect);
    }

    /**
     * 用DriverManager统一进行管理
     */
    @Test
    public void connect03() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class<?> aClass = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();
        String url="jdbc:mysql://localhost:3306/abc_db02";
        String user="root";
        String password="abc";
        DriverManager.registerDriver(driver);//注册driver驱动
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);


    }

    /** 用的最多
     * 自动注册Driver驱动
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Test
    public void connect04() throws ClassNotFoundException, SQLException {
        /**     Class.forName("com.mysql.jdbc.Driver")的源码
         *     static {
         *         try {
         *             DriverManager.registerDriver(new Driver());//底层注册Driver驱动已经完成
         *         } catch (SQLException var1) {
         *             throw new RuntimeException("Can't register driver!");
         *         }
         *     }
         */
       Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/abc_db02";
        String user="root";
        String password="abc";
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    /**
     * 读Properties获取连接
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Test
    public void conn05() throws IOException, ClassNotFoundException, SQLException {

        Properties properties = new Properties();
        properties.load(new FileInputStream("src//mysql.properties"));
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }



}
