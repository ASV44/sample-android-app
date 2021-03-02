package com.sample.app.data.models

import com.sample.app.data.APIService
import com.sample.app.data.ApiError
import com.sample.app.data.HttpErrorInterceptor
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Vdovicenco Alexandr on 03/02/2021.
 */

private const val READ_TIMEOUT: Long = 3000
private const val WRITE_TIMEOUT: Long = 3000
private const val CONNECTION_TIMEOUT: Long = 3000

class APICommunication {
    private val client = buildClient()

    private var retrofit = Retrofit.Builder()
        .baseUrl("https://api.spacexdata.com/v4/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val apiService = retrofit.create(APIService::class.java)

    private fun buildClient(): OkHttpClient {
        val errorInterceptor = HttpErrorInterceptor {
            retrofit.responseBodyConverter(ApiError::class.java, emptyArray())
        }

        return OkHttpClient.Builder()
            .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectionPool(ConnectionPool(20, 5, TimeUnit.MINUTES))
            .addInterceptor(errorInterceptor)
            .build()
    }

    suspend fun getPastLaunches(): ArrayList<Launch> {
        return apiService.getPastLaunches()
    }
}