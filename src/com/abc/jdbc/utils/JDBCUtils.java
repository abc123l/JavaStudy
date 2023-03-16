package com.abc.jdbc.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;

/**
 * @author abc
 * @version 1.0
 * 完成mysql的连接和关闭资源
 */
public class JDBCUtils {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src//mysql.properties"));
            user=properties.getProperty("user");
            driver=properties.getProperty("driver");
            url=properties.getProperty("url");
            password=properties.getProperty("password");

            Class.forName(driver);


        } catch (Exception e) {//将编译异常转成运行异常，这样调用者可以选择捕获也可以选择默认处理
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得连接
     * @return 连接
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 关闭资源，如果不需要关闭相关的资源，传入null
     * @param resultSet
     * @param statement
     * @param connection
     */
    public static void close(ResultSet resultSet, Statement statement,Connection connection){
        try {
            if (resultSet!=null){
                resultSet.close();
            }
            if(statement!=null){
                statement.close();
            }
            if (connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
