package com.abc.jdbc.dao_.domain;

/**
 * @author abc
 * @version 1.0
 * 为模拟ApacheDBUtils创建的类，最终要加入ArrayList
 * Dept对象和Dept表记录相对应
 * 此类属于JavaBean
 */
public class Dept {
    private Integer deptno;
    private String dname;
    private String loc;

    public Dept() {//一定要给无参构造器，反射需要
    }

    public Dept(Integer deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
