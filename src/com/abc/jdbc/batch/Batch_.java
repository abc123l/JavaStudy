package com.abc.jdbc.batch;

import com.abc.jdbc.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author abc
 * @version 1.0
 */
public class Batch_ {
    /**
     * public void addBatch() throws SQLException {
     *         if (this.batchedArgs == null) {
     *         第一次创建一个ArrayList里面有个对象数组ElementData放的就是预处理过的sql语句
     *         当ElementData满10条记录之后扩容1.5倍
     *         批处理减少了发送sql语句的网络开销，减少了编译次数（prepareStatement），效率提高
     *             this.batchedArgs = new ArrayList();
     *         }
     *
     *         this.batchedArgs.add(new BatchParams(this.parameterValues, this.parameterStreams, this.isStream, this.streamLengths, this.isNull));
     *     }
     */
    /**
     * 批处理速度非常块
     * @throws SQLException
     */
    @Test
    public void batch() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql="insert into admin values (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            preparedStatement.setString(1,"abc"+i);
            preparedStatement.setString(2,"123"+i);
            //将sql语句加入到批处理
            preparedStatement.addBatch();
            //当有1000条记录时批量执行
            if ((i+1)%1000==0){
                preparedStatement.executeBatch();
                //清空
                preparedStatement.clearBatch();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println(end-start);//54
        JDBCUtils.close(null,preparedStatement,connection);
    }

}
