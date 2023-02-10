package com.abc.algorithms.mytry;

import java.util.Arrays;

/**
 * @author abc
 * @version 1.0
 * 最短路径求解
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] matrix = new int[vertices.length][vertices.length];
        final int N = 65535;// 表示不可以连接
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        DijGraph dijGraph = new DijGraph(vertices, matrix);
        dijGraph.dij(6);

    }
}
class DijGraph {
    char[] vertices;
    int[][] matrix;

    //Dijkstra三要素
    int[] dis;//初始节点到各个顶点的距离，动态更新
    int[] pre;//前驱节点
    int[] visitedVertices;//已经访问过的节点
    public DijGraph(char[] vertices, int[][] matrix) {
        this.vertices = vertices;
        this.matrix = matrix;
        dis=new int[vertices.length];
        Arrays.fill(dis,65535);
        pre=new int[vertices.length];
        visitedVertices=new int[vertices.length];

    }

    /**
     * 总的思路就是两步反复循环：找到下一个操作节点记为已经访问，以这个操作节点更新dis和pre。直到所有节点都被访问
     * @param index
     */
    public void dij(int index){
        //对初始节点单独处理
        dis[index]=0;
        visitedVertices[index]=1;
        update(index);

        while (!isAllVisited()){
            int nextNode = findNextNode();//找下一个顶点
            update(nextNode);//更新
        }


        System.out.println("访问过的节点：");
        for (int visitedVertex : visitedVertices) {
            System.out.print(visitedVertex+"\t");
        }

        System.out.println("\n前驱节点：");
        for (int i : pre) {
            System.out.print((char)(i+65)+"\t");
        }
        System.out.println("\n距离：");
        for (int di : dis) {
            System.out.print(di+"\t");
        }
    }

    /**
     * 寻找下一个操作节点，遍历所有节点找到没有被访问的且从初始节点到该节点距离最小的节点，返回作为下一个操作节点
     * 并且把该节点记为已经访问
     * @return
     */
    private int findNextNode(){

        int minLength=65535,index=0;
        for (int i = 0; i < vertices.length; i++) {
            if (visitedVertices[i]==0 && dis[i]<minLength){
                index=i;
                minLength=dis[i];
            }
        }
        visitedVertices[index]=1;//非常重要
        return index;
    }

    /**
     * 对该节点的所有相邻的没有访问过的节点开始遍历，若初始节点经过index到i的距离小于之前初始节点到i的距离
     * 就把初始节点到i的距离更新并且设置i节点的前驱节点为index
     * @param index
     */
    private void update(int index){

        for (int i = 0; i < matrix[index].length; i++) {

            if (visitedVertices[i]==0 && dis[index]+matrix[index][i]<dis[i]){
                dis[i]=dis[index]+matrix[index][i];
                pre[i]=index;
            }

        }

    }

    public boolean isAllVisited(){
        for (int visitedVertex : visitedVertices) {
            if (visitedVertex==0){//0是没有被访问
                return false;
            }
        }
        return true;
    }
}