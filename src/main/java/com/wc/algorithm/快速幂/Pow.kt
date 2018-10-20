package com.wc.algorithm.¿ìËÙÃİ

import com.wc.uitls.Solution
import java.math.BigInteger

/**
 * Created by îñ¸ç on 2018/4/23.
 */
class QuickPow : Solution {
    override fun slove(vararg args: Any): Any {
        val x = args[0] as Long
        var n = args[1] as Long

        var result = BigInteger.valueOf(1)
        var cur = BigInteger.valueOf(x)

        while (n != 0L) {
            if (n % 2 == 1L) result = result.multiply(cur)
            n = n shr 1
            cur = cur.multiply(cur)
        }
        return result.toString()
    }

}

class NormalPow : Solution {

    override fun slove(vararg args: Any): Any {
        val x = BigInteger.valueOf(args[0] as Long)
        var n = args[1] as Long
        var res = BigInteger.valueOf(1)
        while (n > 0) {
            res = res.multiply(x)
            n--
        }
        return res
    }
}
