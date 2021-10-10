package com.islam.shutterstock.ui.view.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.islam.shutterstock.R
import com.islam.shutterstock.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.core.IsInstanceOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@MediumTest
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HomeScreenFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun launchFragment(){
        launchFragmentInHiltContainer<HomeScreenFragment>(Bundle(), R.style.Theme_Shutterstock)
    }

    @Test
    fun testList() {
        val recyclerView = onView(
            allOf(
                withId(R.id.list),
                withParent(
                    allOf(
                        withId(R.id.listLayout),
                        withParent(IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        recyclerView.check(matches(isDisplayed()))
    }

    @Test
    fun checkTextOnCard() {
        val textView = onView(
            allOf(
                withId(R.id.description),
                withText("description_1"),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.FrameLayout::class.java))),
                isDisplayed()
            )
        )
        textView.check(matches(withText("description_1")))
    }

    @Test
    fun loadResults_fromFakeRepo_returnsList() {
        onView(withId(R.id.list)).check { view, noViewFoundException ->
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            val recyclerView = view as RecyclerView
            assertEquals(25, recyclerView.adapter?.itemCount)
        }
    }

    @Test
    fun checkOneCard_atFirstPosition_CardDataIsCorrect() {
        onView(withId(R.id.list)).check(
            matches(
                atPosition(
                    0,
                    hasDescendant(withText("description_1"))
                )
            )
        )
    }

    private fun atPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?> {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {

            override fun matchesSafely(view: RecyclerView): Boolean {
                val viewHolder = view.findViewHolderForAdapterPosition(position)
                    ?: // has no item on such position
                    return false
                return itemMatcher.matches(viewHolder.itemView)
            }

            override fun describeTo(description: org.hamcrest.Description?) {
                description?.appendText("has item at position $position: ")
                itemMatcher.describeTo(description)
            }
        }
    }

}