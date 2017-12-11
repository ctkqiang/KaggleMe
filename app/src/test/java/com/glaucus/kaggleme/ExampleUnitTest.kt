package com.glaucus.kaggleme

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    fun bracketMatch(string: String): List<Pair<Int, Int>> {
        val originIndex = arrayListOf<Int>()
        val match = arrayListOf<Pair<Int, Int>>()

        for (index in string.indices) {
            val char = string[index]
            if (char == '(') originIndex.add(index)
            if (char == ')') {
                if (originIndex.isEmpty()) continue
                val curIndex = originIndex.last()
                match.add(curIndex to index)
                originIndex.remove(curIndex)
            }
        }
        return match
    }


    @Test
    fun testMatch() {
        val str = "a(dwefwe((fwefew)(fwefwe)))"
        println(bracketMatch(str))
    }
}
