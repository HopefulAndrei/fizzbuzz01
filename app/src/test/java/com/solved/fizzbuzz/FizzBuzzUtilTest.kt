package com.solved.fizzbuzz

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.random.Random

/**
 * Local unit tests, which will execute on the development machine (host).
 *
 * @see FizzBuzzInstrumentedTest -> UI tests
 */
class FizzBuzzUtilTest {

    @Test
    fun testCheckArguments() {

        //str1 should be an integer
        assertEquals(0, FizzBuzzUtil.checkArguments("", "2", "3", "abc", "efg"))
        assertEquals(0, FizzBuzzUtil.checkArguments("12a", "2", "3", "abc", "efg"))
        //str1 should be positive
        assertEquals(1, FizzBuzzUtil.checkArguments("-1", "2", "3", "abc", "efg"))
        assertEquals(1, FizzBuzzUtil.checkArguments("0", "2", "3", "abc", "efg"))

        //str2 should be an integer
        assertEquals(2, FizzBuzzUtil.checkArguments("1", "", "3", "abc", "efg"))
        assertEquals(2, FizzBuzzUtil.checkArguments("1", "12a", "3", "abc", "efg"))
        //str2 should be positive
        assertEquals(3, FizzBuzzUtil.checkArguments("1", "-1", "3", "abc", "efg"))
        assertEquals(3, FizzBuzzUtil.checkArguments("1", "0", "3", "abc", "efg"))

        //limit should be an integer
        assertEquals(4, FizzBuzzUtil.checkArguments("1", "2", "", "abc", "efg"))
        assertEquals(4, FizzBuzzUtil.checkArguments("1", "2", "12a", "abc", "efg"))
        //limit should be greater than int1 and int2
        assertEquals(5, FizzBuzzUtil.checkArguments("1", "2", "-1", "abc", "efg"))
        assertEquals(5, FizzBuzzUtil.checkArguments("1", "2", "0", "abc", "efg"))
        assertEquals(5, FizzBuzzUtil.checkArguments("1", "2", "2", "abc", "efg"))
        //limit should be less than MAX_LIMIT
        assertEquals(6, FizzBuzzUtil.checkArguments("1", "2", (FizzBuzzUtil.MAX_LIMIT+1).toString(), "abc", "efg"))
        assertEquals(6, FizzBuzzUtil.checkArguments("1", "2", (FizzBuzzUtil.MAX_LIMIT*2).toString(), "abc", "efg"))

        //str1 should be non-empty
        assertEquals(7, FizzBuzzUtil.checkArguments("1", "2", FizzBuzzUtil.MAX_LIMIT.toString(), "", "efg"))
        assertEquals(7, FizzBuzzUtil.checkArguments("1", "2", FizzBuzzUtil.MAX_LIMIT.toString(), "   ", "efg"))
        //str2 should be non-empty
        assertEquals(8, FizzBuzzUtil.checkArguments("1", "2", FizzBuzzUtil.MAX_LIMIT.toString(), "abc", ""))
        assertEquals(8, FizzBuzzUtil.checkArguments("1", "2", FizzBuzzUtil.MAX_LIMIT.toString(), "abc", "   "))
    }


    /**
     * @see FizzBuzzInstrumentedTest.checkFizzBuzz
     * Every unit test below is also found as a UI test in the method linked above
     */
    @Test
    fun testFizzBuzz() {
        assertEquals("aB,aB,aB,", FizzBuzzUtil.FizzBuzz(1, 1, 3, "a", "B"))
        assertEquals("a,aB,a,", FizzBuzzUtil.FizzBuzz(1, 2, 3, "a", "B"))
        assertEquals("a,aB,a,", FizzBuzzUtil.FizzBuzz(2, 1, 3, "a", "B"))
        assertEquals("1,aB,3,", FizzBuzzUtil.FizzBuzz(2, 2, 3, "a", "B"))
        assertEquals("1,a,3,aB,5,", FizzBuzzUtil.FizzBuzz(2, 4, 5, "a", "B"))
        assertEquals("1,a,3,aB,5,", FizzBuzzUtil.FizzBuzz(4, 2, 5, "a", "B"))
        assertEquals("1, a ,3, a  B ,5,", FizzBuzzUtil.FizzBuzz(2, 4, 5, " a " , " B "))
    }

    @Test
    fun testMaps() {

        //first we check that our maps are initially empty
        for (i in 1..FizzBuzzUtil.MAX_LIMIT) {
            assertEquals(0, FizzBuzzUtil.getInt1(i))
            assertEquals(0, FizzBuzzUtil.getInt2(i))
            assertEquals(0, FizzBuzzUtil.getLimit(i))
        }

        // obtain 3 different values int1 != int2; int1 < limit; int2 < limit
        val int1 = Random.nextInt(FizzBuzzUtil.MAX_LIMIT)
        var int2: Int
        do {
            int2 = Random.nextInt(FizzBuzzUtil.MAX_LIMIT)
        } while (int2 == int1)
        var limit: Int
        do {
            limit = Random.nextInt(FizzBuzzUtil.MAX_LIMIT+1)
        } while (limit <= int1 && limit <= int2)


        //we check that we put int1 in the correct map, not other maps
        FizzBuzzUtil.putInt1(int1)
        assertEquals(1, FizzBuzzUtil.getInt1(int1))
        assertEquals(0, FizzBuzzUtil.getInt2(int1))
        assertEquals(0, FizzBuzzUtil.getLimit(int1))

        //we check that we put int2 in the correct map, not other maps
        FizzBuzzUtil.putInt2(int2)
        assertEquals(0, FizzBuzzUtil.getInt1(int2))
        assertEquals(1, FizzBuzzUtil.getInt2(int2))
        assertEquals(0, FizzBuzzUtil.getLimit(int2))

        //we check that we put limit in the correct map, not other maps
        FizzBuzzUtil.putLimit(limit)
        assertEquals(0, FizzBuzzUtil.getInt1(limit))
        assertEquals(0, FizzBuzzUtil.getInt2(limit))
        assertEquals(1, FizzBuzzUtil.getLimit(limit))

        //one more time, we put int1, int2 and limit in the respective maps
        FizzBuzzUtil.putInt1(int1)
        FizzBuzzUtil.putInt2(int2)
        FizzBuzzUtil.putLimit(limit)
        //we expect to find int1, int2, limit two times in their maps, and never in other maps
        assertEquals(2, FizzBuzzUtil.getInt1(int1))
        assertEquals(0, FizzBuzzUtil.getInt2(int1))
        assertEquals(0, FizzBuzzUtil.getLimit(int1))
        assertEquals(0, FizzBuzzUtil.getInt1(int2))
        assertEquals(2, FizzBuzzUtil.getInt2(int2))
        assertEquals(0, FizzBuzzUtil.getLimit(int2))
        assertEquals(0, FizzBuzzUtil.getInt1(limit))
        assertEquals(0, FizzBuzzUtil.getInt2(limit))
        assertEquals(2, FizzBuzzUtil.getLimit(limit))
    }
}