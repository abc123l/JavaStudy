package com.abc.jdbc.preparedstatement_;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author abc
 * @version 1.0
 * PreparedStatement的DML语句
 */
public class PSDML {
    public static void main(String[] args) throws Exception{
        String admin_name="";
        String admin_pwd="";
        Scanner scanner = new Scanner(System.in);
        System.out.print("user=");
        admin_name=scanner.nextLine();
        System.out.print("pwd=");
        admin_pwd=scanner.nextLine();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src//mysql.properties"));
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        //String sql="insert into admin values (?,?)";
        //String sql="update admin  set pwd=? where `name`=?";
        String sql="delete  from admin where `name`=? and pwd=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,admin_name);
        preparedStatement.setString(2,admin_pwd);

        int affectedRows = preparedStatement.executeUpdate();
        if(affectedRows>=1){
            System.out.println("修改成功");
        }else {
            System.out.println("修改失败");
        }

        preparedStatement.close();
        connection.close();
        scanner.close();
    }
}
