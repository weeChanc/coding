package com.wc.leecode.easy.e26

/**
 * Created by осёз on 2018/2/8.
 */
class Solution26 {
    fun removeDuplicates(nums: IntArray): Int {
        var count = 0
        for(i in 1 until nums.size){
            if(nums[i] == nums[i-1] ) count ++
            else{
                nums[i-1] = nums[i]
            }
        }
        return nums.size-count
    }
}