package com.wc.leecode.easy.e189

/**
 * Created by осёз on 2018/2/8.
 */
class Solution189 {
    fun rotate(nums: IntArray, k: Int): Unit {
        var cur = 0
        var next : Int
        var tmp = nums[0]
        var count = 0
        for(i in 0 until nums.size){
            if(count > 0 && nums.size %2 == 0 &&  count % (nums.size/k) == 0 ) {
                cur = (count/k);
                tmp =nums[cur]
            }
            next = (cur + k ) % nums.size
            val ttmp = nums[next]
            nums[next] = tmp
            tmp = ttmp
            cur = next
            count++
        }

        nums.forEach { print(it) }
    }
}