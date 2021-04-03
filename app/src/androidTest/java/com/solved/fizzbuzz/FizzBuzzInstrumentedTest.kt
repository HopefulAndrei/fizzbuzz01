package com.solved.fizzbuzz

import android.util.Log
import android.widget.TextView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented tests, which will execute on an Android device or emulator.
 *
 * @see FizzBuzzUtilTest -> unit tests
 */
@RunWith(AndroidJUnit4::class)
class FizzBuzzInstrumentedTest {

    private lateinit var mActivity: MainActivity

    @Before
    fun getContext() {
        launchActivity<MainActivity>().onActivity {
            mActivity = it
        }
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.solved.fizzbuzz", context.packageName)
    }

    @Test
    fun checkInt1() {
        onView(withId(R.id.button_go)).perform(click())
        onView(withId(R.id.result)).check(matches(withText(mActivity.resources.getStringArray(R.array.error_msgs)[0])))

        onView(withId(R.id.int1)).perform(typeText("0"), closeSoftKeyboard())
        onView(withId(R.id.button_go)).perform(click())
        onView(withId(R.id.result)).check(matches(withText(mActivity.resources.getStringArray(R.array.error_msgs)[1])))

        onView(withId(R.id.int1)).perform(clearText()).perform(typeText("10000"), closeSoftKeyboard())
        onView(withId(R.id.int1)).check(matches(withText("1000")))
    }

    @Test
    fun checkInt2() {
        onView(withId(R.id.int1)).perform(typeText("1"))
        onView(withId(R.id.button_go)).perform(click())
        onView(withId(R.id.result)).check(matches(withText(mActivity.resources.getStringArray(R.array.error_msgs)[2])))

        onView(withId(R.id.int2)).perform(clearText()).perform(typeText("0"), closeSoftKeyboard())
        onView(withId(R.id.button_go)).perform(click())
        onView(withId(R.id.result)).check(matches(withText(mActivity.resources.getStringArray(R.array.error_msgs)[3])))

        onView(withId(R.id.int2)).perform(clearText()).perform(typeText("10000"), closeSoftKeyboard())
        onView(withId(R.id.int2)).check(matches(withText("1000")))
    }

    @Test
    fun checkLimit() {
        onView(withId(R.id.int1)).perform(typeText("1"))
        onView(withId(R.id.int2)).perform(typeText("2"), closeSoftKeyboard())
        onView(withId(R.id.button_go)).perform(click())
        onView(withId(R.id.result)).check(matches(withText(mActivity.resources.getStringArray(R.array.error_msgs)[4])))

        onView(withId(R.id.limit)).perform(typeText("2"), closeSoftKeyboard())
        onView(withId(R.id.button_go)).perform(click())
        onView(withId(R.id.result)).check(matches(withText(mActivity.resources.getStringArray(R.array.error_msgs)[5])))

        onView(withId(R.id.limit)).perform(clearText()).perform(typeText("11000"), closeSoftKeyboard())
        onView(withId(R.id.limit)).check(matches(withText("1100")))
    }

    @Test
    fun checkStr1() {
        onView(withId(R.id.int1)).perform(typeText("1"))
        onView(withId(R.id.int2)).perform(typeText("2"))
        onView(withId(R.id.limit)).perform(typeText("3"), closeSoftKeyboard())

        onView(withId(R.id.button_go)).perform(click())
        onView(withId(R.id.result)).check(matches(withText(mActivity.resources.getStringArray(R.array.error_msgs)[7])))

        onView(withId(R.id.str1)).perform(typeText("   "), closeSoftKeyboard())
        onView(withId(R.id.button_go)).perform(click())
        onView(withId(R.id.result)).check(matches(withText(mActivity.resources.getStringArray(R.array.error_msgs)[7])))
    }

    @Test
    fun checkStr2() {
        onView(withId(R.id.int1)).perform(typeText("1"))
        onView(withId(R.id.int2)).perform(typeText("2"))
        onView(withId(R.id.limit)).perform(typeText("3"))
        onView(withId(R.id.str1)).perform(typeText("abc"), closeSoftKeyboard())

        onView(withId(R.id.button_go)).perform(click())
        onView(withId(R.id.result)).check(matches(withText(mActivity.resources.getStringArray(R.array.error_msgs)[8])))

        onView(withId(R.id.str2)).perform(typeText("   "), closeSoftKeyboard())
        onView(withId(R.id.button_go)).perform(click())
        onView(withId(R.id.result)).check(matches(withText(mActivity.resources.getStringArray(R.array.error_msgs)[8])))
    }

    /**
     * @see FizzBuzzUtilTest.testFizzBuzz
     * Every UI test below is also found as a unit test in the method linked above
     */
    @Test
    fun checkFizzBuzz() {
        onView(withId(R.id.int1)).perform(typeText("1"))
        onView(withId(R.id.int2)).perform(typeText("1"))
        onView(withId(R.id.limit)).perform(typeText("3"))
        onView(withId(R.id.str1)).perform(typeText("a"))
        onView(withId(R.id.str2)).perform(typeText("B"), closeSoftKeyboard())
        onView(withId(R.id.button_go)).perform(click())
        onView(withId(R.id.result)).check(matches(withText("aB,aB,aB,")))

        onView(withId(R.id.int1)).perform(clearText()).perform(typeText("1"))
        onView(withId(R.id.int2)).perform(clearText()).perform(typeText("2"))
        onView(withId(R.id.limit)).perform(clearText()).perform(typeText("3"), closeSoftKeyboard())
        onView(withId(R.id.button_go)).perform(click())
        onView(withId(R.id.result)).check(matches(withText("a,aB,a,")))

        onView(withId(R.id.int1)).perform(clearText()).perform(typeText("2"))
        onView(withId(R.id.int2)).perform(clearText()).perform(typeText("1"))
        onView(withId(R.id.limit)).perform(clearText()).perform(typeText("3"), closeSoftKeyboard())
        onView(withId(R.id.button_go)).perform(click())
        onView(withId(R.id.result)).check(matches(withText("a,aB,a,")))

        onView(withId(R.id.int1)).perform(clearText()).perform(typeText("2"))
        onView(withId(R.id.int2)).perform(clearText()).perform(typeText("2"))
        onView(withId(R.id.limit)).perform(clearText()).perform(typeText("3"), closeSoftKeyboard())
        onView(withId(R.id.button_go)).perform(click())
        onView(withId(R.id.result)).check(matches(withText("1,aB,3,")))

        onView(withId(R.id.int1)).perform(clearText()).perform(typeText("2"))
        onView(withId(R.id.int2)).perform(clearText()).perform(typeText("4"))
        onView(withId(R.id.limit)).perform(clearText()).perform(typeText("5"), closeSoftKeyboard())
        onView(withId(R.id.button_go)).perform(click())
        onView(withId(R.id.result)).check(matches(withText("1,a,3,aB,5,")))

        onView(withId(R.id.int1)).perform(clearText()).perform(typeText("4"))
        onView(withId(R.id.int2)).perform(clearText()).perform(typeText("2"))
        onView(withId(R.id.limit)).perform(clearText()).perform(typeText("5"), closeSoftKeyboard())
        onView(withId(R.id.button_go)).perform(click())
        onView(withId(R.id.result)).check(matches(withText("1,a,3,aB,5,")))

        onView(withId(R.id.int1)).perform(clearText()).perform(typeText("2"))
        onView(withId(R.id.int2)).perform(clearText()).perform(typeText("4"))
        onView(withId(R.id.limit)).perform(clearText()).perform(typeText("5"))
        onView(withId(R.id.str1)).perform(clearText()).perform(typeText(" a "))
        onView(withId(R.id.str2)).perform(clearText()).perform(typeText(" B "), closeSoftKeyboard())
        onView(withId(R.id.button_go)).perform(click())
        Log.e("Result", mActivity.findViewById<TextView>(R.id.result).text.toString())
        /* the result below is different from the unit test, because in MainActivity we make a trim()
           on str1 and str2 when calling FizzBuzzUtil.fizzBuzz() */
        onView(withId(R.id.result)).check(matches(withText("1,a,3,aB,5,")))
    }

}