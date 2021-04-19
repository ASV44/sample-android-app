package com.sample.app

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sample.app.presentation.feed.FeedActivity
import com.sample.app.presentation.feed.FeedOutput
import com.sample.app.presentation.feed.models.FeedItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


/**
 * Created by Vdovicenco Alexandr on 04/19/2021.
 */

@RunWith(AndroidJUnit4::class)
class FeedActivityTests: KoinTest {
    @get:Rule
    var rule = ActivityScenarioRule(FeedActivity::class.java)

    private val mockPresenter = mock<FeedOutput>()

    @Before
    fun setup() {
        val mockModule = module(override = true) {
            single<FeedOutput> { mockPresenter }
        }

        loadKoinModules(mockModule)
    }


    @Test
    fun checkFeedLoading() {
        val feedItemStub = FeedItem("", null, null)

        whenever(mockPresenter.getPastLaunches()).then {  }

        onView(withId(R.id.feed_recycler_view)).check(matches(isDisplayed()))
    }
}