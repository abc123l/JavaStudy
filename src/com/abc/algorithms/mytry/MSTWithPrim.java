package com.abc.algorithms.mytry;

import java.util.Arrays;

/**
 * @author abc
 * @version 1.0
 */
public class MSTWithPrim {
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
        PrimGraph iGraph = new PrimGraph(vertices, data, weight);
        iGraph.showGraph();
        iGraph.createMSTWithPrim(0);
    }
}
class PrimGraph {
    int vertices;
    char[] data;
    int[][] weight;

    public PrimGraph(int vertices, char[] data, int[][] weight) {
        this.vertices = vertices;
        this.data = data;
        this.weight = weight;
    }

    public void createMSTWithPrim(int v){
        int[] visited=new int[vertices];
        visited[v]=1;
        int minWeight=9999;
        int select1=-1;//在某次搜索中已选中的
        int select2=-1;//在某次搜索中未选中的
        for (int i = 1; i < vertices; i++) {//vertices-1条边

            for (int j = 0; j < vertices; j++) {
                for (int k = 0; k < vertices; k++) {
                    if (visited[j]==1 && visited[k]==0 && weight[j][k]<minWeight){
                        minWeight=weight[j][k];
                        //把最后选中的子图中的顶点和即将要选的顶点保存下来方便后面打印
                        select1=j;
                        select2=k;
                    }
                }
            }
            System.out.println("顶点<"+data[select1]+", "+data[select2]+"> 距离："+weight[select1][select2]);
            visited[select2]=1;
            minWeight=9999;


        }

    }

    public void showGraph(){
        for (int[] ints : weight) {
            System.out.println(Arrays.toString(ints));
        }

    }
}