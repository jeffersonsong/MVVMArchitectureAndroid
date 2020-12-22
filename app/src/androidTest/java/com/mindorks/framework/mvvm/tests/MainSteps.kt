package com.mindorks.framework.mvvm.tests

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.RecyclerViewItemCountAssertions
import com.mindorks.framework.mvvm.ui.main.view.MainActivity
import cucumber.api.CucumberOptions
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.ClassRule
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class MainSteps {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Given("The app started")
    fun the_app_started() {

    }

    @Then("I see list of users")
    fun checkUserList() {
        Thread.sleep(1000L)
        onView(ViewMatchers.withId(R.id.recyclerView))
            .check(RecyclerViewItemCountAssertions.isNotEmpty())
    }
}
