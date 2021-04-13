package com.sample.app

import com.sample.app.data.network.APIClient
import com.sample.app.data.network.models.response.Launch
import com.sample.app.presentation.feed.FeedInput
import com.sample.app.presentation.feed.FeedPresenter
import com.sample.app.presentation.feed.models.FeedItem
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.Mockito

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class FeedPresenterTests {

    @Test
    fun getPastLaunches_onSuccessfulDataFetch_shouldUpdateUI() {
        // Init mock objects
        val feedInputMock = Mockito.mock(FeedInput::class.java)
        val apiServiceMock = Mockito.mock(APIClient::class.java)
        val presenter = FeedPresenter(feedInputMock, apiServiceMock)

        // Init stub object
        val launchStub = Mockito.mock(Launch::class.java)
        val fetchDataStub = arrayListOf(launchStub)
        runBlocking {
            Mockito.`when`(apiServiceMock.getPastLaunches()).thenReturn(fetchDataStub)
        }
        val expectedDataSet = arrayOf(FeedItem(
            launchStub.missionName,
            launchStub. details,
            launchStub.links.flickr.original.firstOrNull()?.toString())
        )

        presenter.getPastLaunches()

        runBlocking {
            Mockito.verify(apiServiceMock).getPastLaunches()
        }
        Mockito.verify(feedInputMock).updateUI(expectedDataSet)
    }
}
