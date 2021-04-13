package com.sample.app.data.network

import com.sample.app.data.network.models.response.Launch

/**
 * Created by Vdovicenco Alexandr on 04/13/2021.
 */

interface APIClient {
    suspend fun getPastLaunches(): ArrayList<Launch>
}
