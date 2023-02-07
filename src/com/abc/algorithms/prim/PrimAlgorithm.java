package com.abc.algorithms.prim;

import java.util.Arrays;

/**
 * @author abc
 * @version 1.0
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data={'A','B','C','D','E','F','G'};
        int vertices= data.length;
        int[][] weight={{9999,5,7,9999,9999,9999,2},//9999表示不连通
                {5,9999,9999,9,9999,9999,3},
                {7,9999,9999,9999,8,9999,9999},
                {9999,9,9999,9999,9999,4,9999},
                {9999,9999,8,9999,9999,5,4},
                {9999,9999,9999,4,5,9999,6},
                {2,3,9999,9999,4,6,9999}
        };
        MGraph graph = new MGraph(7);
        MST mst = new MST();
        mst.createGraph(graph,vertices,data,weight);
        mst.showGraph(graph);

    }
}

class MST{
    //创建图的邻接矩阵

    /**
     * 创建图的邻接矩阵
     * @param graph 图对象
     * @param vertices 顶点个数
     * @param data 顶点的值
     * @param weight 邻接矩阵
     */
    public void createGraph(MGraph graph, int vertices,char[] data,int[][] weight){
        for (int i = 0; i < vertices; i++) {
            graph.data[i]=data[i];
            for (int j = 0; j < vertices; j++) {
                graph.weight[i][j]=weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph){

        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }

    }

}
class MGraph{
    int vertices;//节点个数
    char[] data;//存放节点数据
    int[][] weight;//邻接矩阵，存放边

    public MGraph(int vertices) {
        this.vertices = vertices;
        data=new char[vertices];
        weight=new int[vertices][vertices];
    }
}