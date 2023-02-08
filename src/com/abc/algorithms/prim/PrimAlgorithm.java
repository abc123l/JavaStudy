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
        mst.prim(graph,0);
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

    /**
     *
     * @param graph
     * @param v 从哪个顶点开始生成最小生成树 0->'A'
     */
    public void prim(MGraph graph,int v){
        int[] visited=new int[graph.vertices];//0表示为访问过，1表示访问过
        //把这个节点标记为以访问
        visited[v]=1;
        //记录两个顶点的下标
        int h1=-1;
        int h2=-1;
        int minWeight=9999;//遍历过程中会不断更新
        for (int k = 1; k < graph.vertices; k++) {//vertices个顶点生成vertices-1条边
            //在子图的所有节点中寻找与哪个为访问过的节点最近
            for (int i = 0; i < graph.vertices; i++) {//子图里的节点（已访问）
                for (int j = 0; j < graph.vertices; j++) {//未访问的节点
                    if (visited[i]==1 && visited[j]==0 && graph.weight[i][j]<minWeight){
                        minWeight=graph.weight[i][j];
                        h1=i;
                        h2=j;
                    }
                }
            }
            //找到了在这一次搜索中最小的那条边
            System.out.println("边<"+graph.data[h1]+","+graph.data[h2]+"> 权值:"+graph.weight[h1][h2]);
            visited[h2]=1;//将找到的未访问的节点标记已访问
            minWeight=9999;//下一次搜索要重置minWeight
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