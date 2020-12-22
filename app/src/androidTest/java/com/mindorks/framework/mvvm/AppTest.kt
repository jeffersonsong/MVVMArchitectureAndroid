package com.mindorks.framework.mvvm

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import com.mindorks.framework.mvvm.ui.main.view.MainActivity
import org.junit.Before
import org.junit.Test

@LargeTest
class AppTest {

    @Before
    fun applicationStarted() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun checkUserList() {
        Thread.sleep(1000L)
        onView(withId(R.id.recyclerView)).check(RecyclerViewItemCountAssertions.isNotEmpty())
    }
}