package com.abc.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author abc
 * @version 1.0
 */
public class JDBC01 {
    public static void main(String[] args) throws SQLException {
        // 在项目下创建一个目录为libs，将mysql-connector-java拷贝到该目录下，并右键Add as library
        //1. 注册驱动
        Driver driver = new Driver();
        //2. 得到连接
        //jdbc:mysql:固定写法，用jdbc去连接数据库
        //localhost:3306：ip+端口号
        //abc_db02：数据库名
        String url="jdbc:mysql://localhost:3306/abc_db02";//本质是socket连接
        Properties properties = new Properties();
        //user password 都是固定的key
        properties.setProperty("user","root");
        properties.setProperty("password","abc");

        Connection connect = driver.connect(url, properties);
        //3.执行sql
        //String sql="insert into actor values(null,'abc','1999-12-07','110')";
        String sql="delete from actor where id=1";
        Statement statement = connect.createStatement();//用于执行静态SQL语句并返回其生成的结果的对象。
        int rows = statement.executeUpdate(sql);//如果是dml语句返回受影响的行数
        System.out.println(rows>0? "yes" : "no");
        //4. 一定要关闭连接
        statement.close();
        connect.close();

    }
}
