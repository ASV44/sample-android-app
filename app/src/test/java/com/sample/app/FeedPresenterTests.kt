package com.sample.app

import com.sample.app.data.network.APIClient
import com.sample.app.data.network.models.response.Launch
import com.sample.app.data.network.models.response.Links
import com.sample.app.presentation.feed.FeedInput
import com.sample.app.presentation.feed.FeedPresenter
import com.sample.app.presentation.feed.models.FeedItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.net.URL

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@ExperimentalCoroutinesApi
class FeedPresenterTests {
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getPastLaunches_onSuccessfulDataFetch_shouldUpdateUI() = runBlockingTest {
        // Init mock objects
        val feedInputMock = mock<FeedInput>()
        val apiServiceMock = mock<APIClient>()
        val presenter = FeedPresenter(feedInputMock, apiServiceMock)

        // Init stub objects
        val linksStub = mock<Links>()
        whenever(linksStub.flickr).thenReturn(Links.Flickr(arrayListOf(URL("http://test.com"))))

        val launchStub = mock<Launch>()
        whenever(launchStub.missionName).thenReturn("test name")
        whenever(launchStub.links).thenReturn(linksStub)

        val fetchDataStub = arrayListOf(launchStub)
        whenever(apiServiceMock.getPastLaunches()).thenReturn(fetchDataStub)

        // Init expected result
        val expectedDataSet = arrayOf(
                FeedItem(
                        launchStub.missionName,
                        launchStub. details,
                        launchStub.links.flickr.original.firstOrNull()?.toString()
                )
        )

        // Perform tested action
        presenter.getPastLaunches()

        // Verify required methods was called with required parameters
        verify(apiServiceMock).getPastLaunches()
        verify(feedInputMock).showProgress()
        verify(feedInputMock).updateUI(expectedDataSet)
    }
}
