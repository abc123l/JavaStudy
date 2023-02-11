package com.abc.algorithms.mytry;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author abc
 * @version 1.0
 * 修路问题
 */
public class KruskalAlgorithm {
    char[] vertices;
    int[][] weight;
    static final int INF=Integer.MAX_VALUE;
    ArrayList<Edge> edges=new ArrayList<>();//图中的每条边
    ArrayList<Edge> results=new ArrayList<>();//要修的边

    public static void main(String[] args) {
        char[] vertices={'A','B','C','D','E','F','G'};
        //克鲁斯卡尔算法的邻接矩阵
        int weight[][] = {
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                /*A*/ {   0,  12, INF, INF, INF,  16,  14},//0表示自己到自己的距离
                /*B*/ {  12,   0,  10, INF, INF,   7, INF},
                /*C*/ { INF,  10,   0,   3,   5,   6, INF},
                /*D*/ { INF, INF,   3,   0,   4, INF, INF},
                /*E*/ { INF, INF,   5,   4,   0,   2,   8},
                /*F*/ {  16,   7,   6, INF,   2,   0,   9},
                /*G*/ {  14, INF, INF, INF,   8,   9,   0}};
        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm();
        kruskalAlgorithm.kruskal(vertices,weight);
    }
    public void kruskal(char[] vertices,int[][] weight){
        createGraph(vertices,weight);
        addEdgeToList();
        //把每条边按照权值从小到大排序
        Collections.sort(edges);
        int[] ends=new int[edges.size()];
        //如果不构成回路就添加进去
        for (int i = 0; i < edges.size(); i++) {
            //拿到这条边两个顶点的下标
            int p1 = getPosition(edges.get(i).start);
            int p2 = getPosition(edges.get(i).end);
            //拿到两个顶点的终点
            int m = getEnds(ends, p1);
            int n = getEnds(ends, p2);

            if (m!=n){
                results.add(edges.get(i));
                ends[m]=n;//必不可少，更新终点数组
            }

        }
        System.out.println(results);

    }

    /**
     * 为了防止修改源数据需要另外开辟内存空间
     * @param vertices
     * @param weight
     */
    private void createGraph(char[] vertices,int[][] weight){
        this.vertices=new char[vertices.length];
        System.arraycopy(vertices, 0, this.vertices, 0, vertices.length);


        this.weight=new int[vertices.length][vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            for (int j = i; j < vertices.length; j++) {
                this.weight[i][j]=weight[i][j];
            }
        }



    }
    private void addEdgeToList(){
        for (int i = 0; i < vertices.length; i++) {
            for (int j = i; j < vertices.length; j++) {//j从i开始可以防止添加重复的边比如AB,BA
                if (weight[i][j]!=INF && weight[i][j]!=0){
                    edges.add(new Edge(vertices[i],vertices[j],weight[i][j] ));
                }
            }
        }
    }

    /**
     * 把每个顶点索引化方便判断要添加的边是否有相同终点
     * @param ch
     * @return
     */
    private int getPosition(char ch){
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i]==ch){
                return i;
            }
        }
        return -1;
    }

    /**
     * 判断是否构成回路的核心方法
     * @param ends 各个顶点的终点，动态变化
     * @param i 下标为i的顶点
     * @return 下标为i的顶点的终点
     */
    private int getEnds(int[] ends,int i){
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
