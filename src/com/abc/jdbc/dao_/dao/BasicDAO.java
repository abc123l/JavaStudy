package com.abc.jdbc.dao_.dao;

import com.abc.jdbc.datasource.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author abc
 * @version 1.0
 *
 */
public class BasicDAO<T>{

    private QueryRunner queryRunner=new QueryRunner();
    public int update(String sql,Object... parameters){
        Connection connection=null;

        try {
            connection= JDBCUtilsByDruid.getConnection();
            int update = queryRunner.update(connection, sql, parameters);
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCUtilsByDruid.close(null,null,connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * 返回多个结果
     * @param sql
     * @param clazz 利用反射创建对象
     * @param parameters sql里的可变形参
     * @return
     */
    public List<T> queryMulti(String sql,Class<T> clazz,Object... parameters){
        Connection connection=null;

        try {
            connection=JDBCUtilsByDruid.getConnection();
            return queryRunner.query(connection,sql,new BeanListHandler<T>(clazz),parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCUtilsByDruid.close(null,null,connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 返回单个结果
     * @param sql
     * @param clazz
     * @param parameters
     * @return
     */
    public T querySingle(String sql,Class<T> clazz,Object... parameters){
        Connection connection=null;

        try {
            connection=JDBCUtilsByDruid.getConnection();
            return queryRunner.query(connection,sql,new BeanHandler<T>(clazz),parameters);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCUtilsByDruid.close(null,null,connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 返回单行单列,单值
     * @param sql
     * @param parameters
     * @return
     */
    public Object queryScalar(String sql,Object... parameters){
        Connection connection=null;

        try {
            connection=JDBCUtilsByDruid.getConnection();
            return queryRunner.query(connection,sql,new ScalarHandler(),parameters);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                JDBCUtilsByDruid.close(null,null,connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
