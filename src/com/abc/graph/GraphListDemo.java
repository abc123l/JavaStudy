package com.abc.graph;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author abc
 * @version 1.0
 * 将图用链表来存储，详见b站收藏
 */
public class GraphListDemo {
    public static void main(String[] args) {
        GraphList graphList = new GraphList(7);
        graphList.addEdge(0,1);
        graphList.addEdge(1,2);
        graphList.addEdge(1,4);
        graphList.addEdge(1,5);
        graphList.addEdge(2,4);
        graphList.addEdge(3,2);
        graphList.addEdge(4,1);
        graphList.addEdge(4,3);
        graphList.addEdge(5,6);
        for (ArrayList<Integer> graph : GraphList.graphs) {
            System.out.println(graph);
        }
        graphList.DFS();



    }
}
class GraphList{

    static ArrayList<ArrayList<Integer>> graphs;
    static boolean[] isVisited;
    public GraphList(int vertexNum){
        graphs=new ArrayList<>();
        for (int i = 0; i < vertexNum; i++) {
            graphs.add(new ArrayList<>());
        }
        isVisited=new boolean[vertexNum];
    }

    public void addEdge(int start,int end){
        graphs.get(start).add(end);
    }
    public void removeEdge(int start,int end){
        graphs.get(start).remove(end);
    }

    /**
     * 深度优先搜索
     * @param i
     */
    public void DFS(int i){
        if (isVisited[i]){
            return;
        }
        System.out.print((char)(i+65)+"->");//用ascii表转换成了大写字母
        isVisited[i]=true;

        Iterator<Integer> iterator = graphs.get(i).iterator();
        while (iterator.hasNext()){
            int nextNode=iterator.next();
            if (!isVisited[nextNode]){
                DFS(nextNode);
            }
        }
    }

    /**
     * 为了防止有单个节点的出现
     */
    public void DFS(){
        for (int i = 0; i < graphs.size(); i++) {
            if (!isVisited[i]){//提高效率
                DFS(i);
            }
        }
    }

}

