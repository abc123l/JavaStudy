package com.abc.jdbc.resultset_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author abc
 * @version 1.0
 * select语句
 */
public class ResultSet_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src//mysql.properties"));
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        Class.forName(driver);//加载驱动
        Connection connection = DriverManager.getConnection(url, user, password);//建立连接
        Statement statement = connection.createStatement();

        String sql="select id,name,borndate from actor";

        ResultSet resultSet = statement.executeQuery(sql);//返回结果集对象
        //使用while取出数据
        while (resultSet.next()){
            int id = resultSet.getInt(1);//第一列
            String name = resultSet.getString(2);
            Date date = resultSet.getDate(3);
            System.out.println(id+"\t"+name+"\t"+date);
        }


        //关闭连接
        resultSet.close();
        statement.close();
        connection.close();
        


    }
}
