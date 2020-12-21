package com.mindorks.framework.mvvm

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mindorks.framework.mvvm.ui.main.view.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class AppTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

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