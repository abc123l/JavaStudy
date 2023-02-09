package com.abc.algorithms.kruskal;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author abc
 * @version 1.0
 */
public class KruskalDemo {
    private int edgeNum;//边的个数
    private char[] vertices;//顶点集合
    private int[][] matrix;//邻接矩阵
    private static final int INF=Integer.MAX_VALUE;//表示两条边不联通
    private static ArrayList<Edge> edges=new ArrayList<>();

    public static void main(String[] args) {
        char[] vertices={'A','B','C','D','E','F','G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};

        KruskalDemo kruskalDemo = new KruskalDemo(vertices, matrix);

        kruskalDemo.kruskal();



    }

    public void kruskal(){
        ArrayList<Edge> result = new ArrayList<>();


        addEdgeToList();
        int[] ends=new int[edges.size()];
        //对各个边进行排序
        Collections.sort(edges);
        //将边添加到最小生成树中并判断是否构成了回路
        for (int i = 0; i < edges.size(); i++) {
            //拿到某条边的起点和终点
            int p1 = getPosition(edges.get(i).start);
            int p2 = getPosition(edges.get(i).end);

            //得到这两个顶点在子图中的终点
            int m = getEnd(ends,p1);
            int n = getEnd(ends,p2);
            if (m!=n){//终点不是同一个，不构成回路
                ends[m]=n;
                result.add(edges.get(i));
            }
        }
        System.out.println(result);

    }


    public KruskalDemo(char[] vertices,int[][] matrix){
        int vlen= vertices.length;
        //这样是为了在进行算法的过程中不改变原始数据，因为不共享内存地址
        //初始化顶点
        this.vertices=new char[vlen];
        for (int i = 0; i < vlen; i++) {
            this.vertices[i]=vertices[i];
        }
        //初始化邻接矩阵,并统计边
        this.matrix=new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j]=matrix[i][j];
                if (matrix[i][j]!=INF && matrix[i][j]!=0){
                    edgeNum++;
                }
            }
        }
    }

    public void showMatrix(){
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                System.out.printf("%12d",matrix[i][j]);
            }
            System.out.println();
        }
    }

    public void addEdgeToList(){
        for (int i = 0; i < vertices.length; i++) {
            for (int j = i; j < vertices.length; j++) {//只遍历右上三角
                if (matrix[i][j]!=INF && matrix[i][j]!=0) {
                    edges.add(new Edge(vertices[i], vertices[j], matrix[i][j]));
                }
            }
        }

    }

    /**
     * 获取某个顶点的下标
     * @param ch
     * @return
     */
    public int getPosition(char ch){
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i]==ch){
                return i;
            }
        }
        return -1;
    }

    /**
     * 返回下标为i的顶点的终点，不太理解
     * @param ends 记录各个顶点的终点是哪个，在遍历过程中逐步形成
     * @param i 下标为i的顶点
     * @return
     */
    public int getEnd(int[] ends,int i){
        while (ends[i]!=0){
            i=ends[i];
        }
        return i;
    }


}
class Edge implements Comparable<Edge>{
    char start;
    char end;
    int weight;

    public Edge(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }


    @Override
    public int compareTo(Edge o) {
        return this.weight-o.weight;
    }
}
