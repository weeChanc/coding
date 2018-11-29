package com.wc.leecode.easy


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