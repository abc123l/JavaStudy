package com.abc.algorithms.dijkstra;



import java.util.Arrays;

/**
 * @author abc
 * @version 1.0
 * 写的很乱，建议看mytry
 */
public class DijkstraDemo {
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

        IGraph iGraph = new IGraph(vertices, matrix);
        iGraph.showGraph();
        iGraph.dsj(6);

    }
}

class ManageVertices {
    public int[] visitedVertices;//已访问的顶点,0没有访问过，1访问过
    public int[] preVisited;//前驱顶点
    public int[] dis;//距离

    /**
     *
     * @param verticesNum 顶点个数
     * @param index 初始节点
     */
    public ManageVertices(int verticesNum,int index) {
        this.visitedVertices=new int[verticesNum];
        this.preVisited=new int[verticesNum];
        this.dis=new int[verticesNum];

        //初始化dis
        Arrays.fill(dis,65535);
        this.dis[index]=0;//自己到自己为0
        this.visitedVertices[index]=1;//出发顶点被访问过
    }

    /**
     * 返回改下标的节点是否访问过
     * @param index
     * @return
     */
    public boolean isVisited(int index){
        return visitedVertices[index]==1;
    }

    /**
     * 更新出发节点到index顶点的距离
     * @param index
     * @param dis
     */
    public void updateDis(int index,int dis){
        this.dis[index]=dis;
    }

    /**
     * 更新前驱节点
     * @param pre 要更新的前驱节点的下标
     * @param index 更新后前驱节点
     */
    public void updatePre(int pre,int index){
        this.preVisited[pre]=index;
    }

    /**
     * 返回初始节点到index节点的距离
     * @param index
     */
    public int getDis(int index){
        return this.dis[index];
    }

    /**
     * 下一轮应该选哪个顶点
     * @return
     */
    public int updateArr(){
        int min=65535,index=0;
        //在所有没有访问的顶点中选择dis最小的那一个
        for (int i = 0; i < visitedVertices.length; i++) {
            if (visitedVertices[i]==0 && dis[i]<min){
                index=i;
                min=dis[i];//会不断更新min的
            }
        }
        visitedVertices[index]=1;
        return index;
    }
    public void showResults(){
        System.out.println("访问过的节点：");
        for (int visitedVertex : visitedVertices) {
            System.out.print(visitedVertex+"\t");
        }

        System.out.println("\n前驱节点：");
        for (int i : preVisited) {
            System.out.print((char)(i+65)+"\t");
        }
        System.out.println("\n距离：");
        for (int di : dis) {
            System.out.print(di+"\t");
        }
    }

}

class IGraph {
    private char[] vertices;
    private int[][] matrix;
    ManageVertices manageVertices;
    public IGraph(char[] vertices, int[][] matrix) {
        this.vertices = vertices;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }

    /**
     *
     * @param index 初始顶点
     */
    public void dsj(int index){
        manageVertices = new ManageVertices(vertices.length, index);
        update(index);
        for (int i = 1; i < vertices.length; i++) {
            int newIndex = manageVertices.updateArr();//新的访问顶点
            update(newIndex);
        }
        manageVertices.showResults();
    }

    /**
     * 更新index到周围顶点的距离和前驱节点
     * @param index
     */
    private void update(int index){
        int len=0;
        for (int i = 0; i < matrix[index].length; i++) {
            len= manageVertices.getDis(index)+matrix[index][i];
            //如果i没有访问过，并且G经过index到i的距离小于之前G到i的距离就更新G到i的距离
            if (!manageVertices.isVisited(i) && len<manageVertices.getDis(i)){
                manageVertices.updateDis(i,len);
                manageVertices.updatePre(i,index);
            }

        }
    }
}