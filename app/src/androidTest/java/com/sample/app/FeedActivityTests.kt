package com.sample.app

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sample.app.data.network.APIClient
import com.sample.app.presentation.feed.FeedActivity
import com.sample.app.presentation.feed.FeedInput
import com.sample.app.presentation.feed.FeedOutput
import com.sample.app.presentation.feed.FeedPresenter
import com.sample.app.presentation.feed.models.FeedItem
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import org.mockito.kotlin.UseConstructor
import org.mockito.kotlin.mock


/**
 * Created by Vdovicenco Alexandr on 04/19/2021.
 */

@RunWith(AndroidJUnit4::class)
class FeedActivityTests: KoinTest {
    lateinit var mockModule: Module

    @Before
    fun setup() {
        mockModule = module(override = true) {
            single<FeedOutput>(override = true) { (view: FeedInput) ->
                mock<FeedPresenter>(useConstructor = UseConstructor.withArguments(view, get<APIClient>()))
            }
            single<APIClient> { mock() }
        }

        loadKoinModules(mockModule)
    }

    @After
    fun tearDown() {
        unloadKoinModules(mockModule)
    }

    @Test
    fun checkFeedLoading() {
        val feedItemStub = FeedItem("", null, null)

        ActivityScenario.launch(FeedActivity::class.java)

        // Init expected result
        val expectedDataSet = arrayOf(feedItemStub)
        val mockPresenter = get<FeedOutput>() as FeedPresenter
        mockPresenter.view.updateUI(expectedDataSet)

        onView(withId(R.id.feed_recycler_view)).check(matches(isDisplayed()))
    }
}