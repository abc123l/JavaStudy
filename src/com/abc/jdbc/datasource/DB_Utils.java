package com.abc.jdbc.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author abc
 * @version 1.0
 * 使用ApacheDBUtils+Druid完成crud
 */
public class DB_Utils {
    @Test
    public void queryMany() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql="select deptno,dname from dept where deptno>=?";
        List<Dept> query =
                queryRunner.query(connection, sql, new BeanListHandler<>(Dept.class),10);

        /**queryRunner.query执行sql语句把resultSet封装到ArrayList中,
         * 注意：得到的resultSet，preparedStatement会在底层自动关闭
         * 参数解读:
         * new BeanListHandler<>(Dept.class):利用反射机制，获取Dept的属性然后封装
         * 10:传给sql语句中的？可以有多个值,可变参数
         */
        JDBCUtilsByDruid.close(null,null,connection);
        System.out.println(query);
    }

    /**
     * 对单个记录的查询，返回单个对象而不是集合
     * @throws SQLException
     */
    @Test
    public void testQuerySingle() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql="select deptno,dname from dept where deptno=?";
        //注意是BeanHandler
        Dept query = queryRunner.query(connection, sql, new BeanHandler<>(Dept.class), 10);
        JDBCUtilsByDruid.close(null,null,connection);
        System.out.println(query);
    }

    /**
     * 返回结果单行单列，封装成一个对象
     */
    @Test
    public void testScalar() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();
        String sql="select dname from dept where deptno=?";
        //ScalarHandler()
        Object obj = queryRunner.query(connection, sql, new ScalarHandler(), 10);
        JDBCUtilsByDruid.close(null,null,connection);
        System.out.println(obj);
    }
    @Test
    public void testDML() throws SQLException {
        Connection connection = JDBCUtilsByDruid.getConnection();
        QueryRunner queryRunner = new QueryRunner();

        //update可以dml
        //String sql="update exam set id=? where grade=?";
        String sql="insert into exam values (?,?)";
        int affectedRows = queryRunner.update(connection, sql, 4, 9);
        System.out.println(affectedRows>0? "执行成功":"执行失败");
        JDBCUtilsByDruid.close(null,null,connection);
    }
}
