package com.example.list.uitest

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.list.R
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
class FirstTestActivityTest{

    @Test
    fun launch_Activity_main() {
        val activityScenario = ActivityScenario.launch(FirstTestActivity::class.java)

        onView(withId(R.id.root)).check(matches(isDisplayed()))
    }
}