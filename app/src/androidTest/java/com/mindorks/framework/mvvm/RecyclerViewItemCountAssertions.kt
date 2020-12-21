package com.mindorks.framework.mvvm

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matcher
import org.hamcrest.Matchers.greaterThan


class RecyclerViewItemCountAssertions(private val matcher: Matcher<Int>): ViewAssertion {
    companion object {
        fun withItemCount(expectedCount: Int): RecyclerViewItemCountAssertions? {
            return withItemCount(`is`(expectedCount))
        }

        fun isEmpty(): RecyclerViewItemCountAssertions? {
            return withItemCount(0)
        }

        fun isNotEmpty(): RecyclerViewItemCountAssertions? {
            return withItemCount(greaterThan(0))
        }

        fun withItemCount(matcher: Matcher<Int>): RecyclerViewItemCountAssertions? {
            return RecyclerViewItemCountAssertions(matcher)
        }
    }

    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assertThat(adapter!!.itemCount, matcher)
    }
}
