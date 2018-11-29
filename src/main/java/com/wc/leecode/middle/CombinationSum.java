package com.wc.leecode.middle;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    class Solution {
        public List<List<Integer>> combinationSum(int[] candidates, int target) {

            List<List<Integer>> result = new ArrayList<>();
            for (int i = 0; i < candidates.length; i++) {
                result.addAll(getCandidates(candidates, target - candidates[i], new ArrayList<Integer>(), i));
            }

            return result;
        }

        public List<List<Integer>> getCandidates(int[] candidates,
                                                 int target, ArrayList<Integer> choice, int pos) {
            List<List<Integer>> result = new ArrayList<>();
            if (target < 0) return result;

            choice.add(candidates[pos]);
            if (target == 0) {
                result.add(choice);
                return result;
            }

            for (int i = 0; i < candidates.length; i++) {
                result.addAll(getCandidates(candidates, target - candidates[i],
                        (ArrayList<Integer>) (choice).clone(), i));
            }
            return result;
        }
    }

}
