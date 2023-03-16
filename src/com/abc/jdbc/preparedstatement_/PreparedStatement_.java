package com.abc.jdbc.preparedstatement_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author abc
 * @version 1.0
 */
public class PreparedStatement_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        String admin_name="";
        String admin_pwd="";
        Scanner scanner = new Scanner(System.in);

        //注意要用nextLine(),next输入空格或单引号就表示结束了
        System.out.print("user=");
        admin_name= scanner.nextLine();//回车表示结束
        System.out.print("pwd=");
        admin_pwd= scanner.nextLine();

        Properties properties = new Properties();
        properties.load(new FileInputStream("src//mysql.properties"));
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        Class.forName(driver);//加载驱动
        Connection connection = DriverManager.getConnection(url, user, password);
        //?为占位符
        String sql="select `name`,pwd from admin where `name`=? and pwd=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,admin_name);
        preparedStatement.setString(2,admin_pwd);

        ResultSet resultSet = preparedStatement.executeQuery();//这里括号里面不要写sql,因为sql里面有问号
        if(resultSet.next()){//查询到一条记录
            System.out.println("登陆成功");
        }else {
            System.out.println("登陆失败");
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
