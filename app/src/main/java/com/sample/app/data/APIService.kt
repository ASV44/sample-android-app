package com.sample.app.data

import com.sample.app.data.models.Launch
import retrofit2.http.GET

/**
 * Created by Vdovicenco Alexandr on 03/02/2021.
 */

interface APIService {
    @GET("launches/past")
    suspend fun getPastLaunches(): ArrayList<Launch>
}