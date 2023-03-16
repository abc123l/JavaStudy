package com.abc.jdbc.datasource;

import com.abc.jdbc.utils.JDBCUtils;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author abc
 * @version 1.0
 * Druid连接池
 */
public class Druid_ {
    /**
     * 引入Druid的jar包和配置文件
     * 比C3P0快
     */
    @Test
    public void testDruid() throws Exception {
        //读入Druid的配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream("src//druid.properties"));

        //创建一个指定参数的数据库连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        connection.close();
    }
    @Test
    public void test01() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
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
        JDBCUtilsByDruid.close(resultSet,preparedStatement,connection);

    }

    /**
     * 此方法模拟ApacheDBUtils
     */
    @Test
    public ArrayList<Dept> resultSetToArrayList() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        ArrayList<Dept> list=new ArrayList<>();
        //用来存储resultset的元素，就算关闭了连接数据还在,也就是可以复用
        String sql="select * from dept";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int deptno = resultSet.getInt("deptno");
            String dname = resultSet.getString("dname");
            String loc = resultSet.getString("loc");
            //把得到的记录封装到dept对象，放入到list集合
            list.add(new Dept(deptno,dname,loc));
        }
        JDBCUtilsByDruid.close(resultSet,preparedStatement,connection);

        return list;
    }

}
