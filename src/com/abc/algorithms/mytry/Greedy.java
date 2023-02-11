package com.abc.algorithms.mytry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * @author abc
 * @version 1.0
 * 集合覆盖问题
 */
public class Greedy {
    public static void main(String[] args) {
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

        ArrayList<String> selectKeys = new ArrayList<>();
        HashSet<String> tempSet = new HashSet<>();

        while (allAreas.size()>0){
            String maxKey=null;
            int preMaxSize=0;
            for (String key: broadCasts.keySet()){
                tempSet.clear();
                HashSet<String> areas = broadCasts.get(key);
                tempSet.addAll(areas);
                tempSet.retainAll(allAreas);
                if (tempSet.size()>0 && (maxKey==null || tempSet.size()>preMaxSize)){
                    maxKey=key;
                    preMaxSize= tempSet.size();
                }
            }
            if (maxKey!=null){
                selectKeys.add(maxKey);
                allAreas.removeAll(broadCasts.get(maxKey));
            }
        }

        System.out.println(selectKeys);
    }
}
