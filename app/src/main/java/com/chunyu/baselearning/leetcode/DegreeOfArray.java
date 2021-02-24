package com.chunyu.baselearning.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* LeetCode 697
* */
public class DegreeOfArray {
    // 1. 先查找数组中出现次数最多的元素
    public int findShortestSubArray(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                int temp;
                if (map.get(num) == null) {
                    temp = 0;
                } else {
                    temp = map.get(num);
                }
                map.put(num, temp + 1);
            } else {
                map.put(num, 1);
            }
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });

        int maxNum = list.get(0).getKey();

        int start = 0, end = 0;

        int i = 0;
        while (i < nums.length) {
            if (maxNum == nums[i]) {
                start = i;
                for (int j = i; j < nums.length; j++) {
                    if (nums[j] == maxNum) {
                        end = j;
                    }
                }
                break;
            } else {
                i++;
            }
        }

        return end - start + 1;
    }

}
