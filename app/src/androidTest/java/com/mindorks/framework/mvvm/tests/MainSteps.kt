package com.mindorks.framework.mvvm.tests

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import com.mindorks.framework.mvvm.R
import com.mindorks.framework.mvvm.RecyclerViewItemCountAssertions
import com.mindorks.framework.mvvm.ui.main.view.MainActivity
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then

@LargeTest
class MainSteps {

    @Given("The app started")
    fun the_app_started() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Then("I see list of users")
    fun checkUserList() {
        Thread.sleep(1000L)
        onView(ViewMatchers.withId(R.id.recyclerView))
            .check(RecyclerViewItemCountAssertions.isNotEmpty())
    }
}
