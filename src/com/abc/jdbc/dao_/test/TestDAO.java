package com.abc.jdbc.dao_.test;

import com.abc.jdbc.dao_.dao.DeptDAO;
import com.abc.jdbc.dao_.domain.Dept;
import org.junit.Test;

import java.util.List;

/**
 * @author abc
 * @version 1.0
 */
public class TestDAO {
    public static void main(String[] args) {


    }

    public void t00(){
        DeptDAO deptDAO = new DeptDAO();
        List<Dept> depts = deptDAO.queryMulti
                ("select * from dept where deptno >= ?", Dept.class, 10);
        for (Dept dept : depts) {
            System.out.println(dept);
        }
    }
    @Test
    public void t01(){
        DeptDAO deptDAO = new DeptDAO();
        Dept dept = deptDAO.
                querySingle("select * from dept where deptno = ?", Dept.class, 10);
        System.out.println(dept);
    }
    @Test
    public void t02(){
        DeptDAO deptDAO = new DeptDAO();
        //返回的类型是java设计者设计的原始的数据类型，用不着反射，也就不用传入class类对象
        Object o = deptDAO.queryScalar("select dname from dept where deptno = ?", 10);
        System.out.println(o);
    }

}
