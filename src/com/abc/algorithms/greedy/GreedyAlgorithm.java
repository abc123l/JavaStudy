package com.abc.algorithms.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author abc
 * @version 1.0
 * 贪心算法求覆盖集合最大问题
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建电台
        HashMap<String, HashSet<String>> broadCasts = new HashMap<>();
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        broadCasts.put("K1",hashSet1);
        broadCasts.put("K2",hashSet2);
        broadCasts.put("K3",hashSet3);
        broadCasts.put("K4",hashSet4);
        broadCasts.put("K5",hashSet5);
        //存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        //存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时的交集
        //
        // 集合，存放在遍历过程中电台覆盖地区和当前还没有覆盖地区的交集
        HashSet<String> tempSet = new HashSet<>();


        while (allAreas.size()!=0){
            //每进行一次while需要:
            String maxKey=null;//定义一个maxKey，保存一次遍历过程中，能够覆盖最大未覆盖地区的对应电台的key
            int preMaxSize =0;//用于存放上一次交集的大小，方便更新maxKey

            for (String key:broadCasts.keySet()){
                tempSet.clear();//在循环中每一次求交集都要把临时交集集合清空
                HashSet<String> areas = broadCasts.get(key);
                tempSet.addAll(areas);//把areas全部加入到tempSet
                tempSet.retainAll(allAreas);//tempSet与allAreas取交集再重新赋给tempSet
                //如果此交集数量大于0并且（maxKey==null代表刚进入一次循环或者tempSet.size()>preMaxSize体现贪心算法
                // 代表当前key的交集的数量大于之前key的交集的数量，就要更新key）
                if (tempSet.size()>0 && (maxKey==null || tempSet.size()> preMaxSize)){
                    maxKey=key;
                    preMaxSize = tempSet.size();
                }

            }
            if (maxKey!=null){
                selects.add(maxKey);
                //将maxKey所指向的电台所覆盖的地区从allAreas去除
                allAreas.removeAll(broadCasts.get(maxKey));
            }
        }

        System.out.println(selects);
    }
}
