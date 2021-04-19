package com.sample.app.dependency_injection

import com.sample.app.data.network.APIClient
import com.sample.app.data.network.APICommunication
import com.sample.app.presentation.feed.FeedInput
import com.sample.app.presentation.feed.FeedOutput
import com.sample.app.presentation.feed.FeedPresenter
import org.koin.dsl.module

/**
 * Created by Vdovicenco Alexandr on 04/19/2021.
 */

val appModule = module {
    single<APIClient> { APICommunication() }
    single<FeedOutput> { (view: FeedInput) -> FeedPresenter(view, get()) }
}
