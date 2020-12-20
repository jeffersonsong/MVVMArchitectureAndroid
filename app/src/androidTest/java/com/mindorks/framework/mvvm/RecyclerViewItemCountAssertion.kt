package com.mindorks.framework.mvvm

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Matcher
import org.hamcrest.Matchers.greaterThan


class RecyclerViewItemCountAssertion(private val matcher: Matcher<Int>): ViewAssertion {
    companion object {
        fun withItemCount(expectedCount: Int): RecyclerViewItemCountAssertion? {
            return withItemCount(`is`(expectedCount))
        }

        fun isEmpty(): RecyclerViewItemCountAssertion? {
            return withItemCount(0)
        }

        fun isNotEmpty(): RecyclerViewItemCountAssertion? {
            return withItemCount(greaterThan(0))
        }

        fun withItemCount(matcher: Matcher<Int>): RecyclerViewItemCountAssertion? {
            return RecyclerViewItemCountAssertion(matcher)
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
