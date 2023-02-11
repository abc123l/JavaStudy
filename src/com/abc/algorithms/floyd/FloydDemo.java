package com.abc.algorithms.floyd;

import java.util.Arrays;

/**
 * @author abc
 * @version 1.0
 * 最短路径（任意两个顶点） 时间复杂度n^3
 * 比较简单就不try了
 */
public class FloydDemo {
    public static void main(String[] args) {
        // 测试看看图是否创建成功
        char[] vertices = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        //创建邻接矩阵
        int[][] matrix = new int[vertices.length][vertices.length];
        final int N = 65535;
        matrix[0] = new int[] { 0, 5, 7, N, N, N, 2 };
        matrix[1] = new int[] { 5, 0, N, 9, N, N, 3 };
        matrix[2] = new int[] { 7, N, 0, N, 8, N, N };
        matrix[3] = new int[] { N, 9, N, 0, N, 4, N };
        matrix[4] = new int[] { N, N, 8, N, 0, 5, 4 };
        matrix[5] = new int[] { N, N, N, 4, 5, 0, 6 };
        matrix[6] = new int[] { 2, 3, N, N, 4, 6, 0 };

        FloGraph floGraph = new FloGraph(vertices, matrix);
        floGraph.floyd();
    }
}
class FloGraph{
    char[] vertices;
    int[][] dis;//各个顶点间的距离，动态变化

    int[][] pre;//前驱顶点的下标

    public FloGraph(char[] vertices, int[][] dis) {
        this.vertices = vertices;
        this.dis = dis;
        pre=new int[vertices.length][vertices.length];
        //初始化前驱矩阵
        for (int i = 0; i < pre.length; i++) {
            Arrays.fill(pre[i],i);
        }

    }

    public void floyd(){

        for (int k = 0; k < vertices.length; k++) {//中间顶点
            for (int i = 0; i < vertices.length; i++) {//初始顶点
                for (int j = 0; j < vertices.length; j++) {//目标顶点
                    //如果从i经过k到j的距离比之前从i到j的距离更小就更新两者之间的距离并且更新k为前驱节点
                    if (dis[i][k]+dis[k][j]<dis[i][j]){
                        dis[i][j]=dis[i][k]+dis[k][j];
                        pre[i][j]=pre[k][j];
                    }
                }
            }
        }
        show();
    }
    public void show(){

        for (int i = 0; i < dis.length; i++) {
            for (int j = 0; j < dis.length; j++) {

                System.out.print(" 从"+vertices[i]+"到"+vertices[j]+"的距离："+dis[i][j]);

            }
            System.out.println();
        }
        System.out.println("前驱矩阵为：");
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                System.out.print(vertices[pre[i][j]]+"\t");
            }
            System.out.println();
        }

    }

}