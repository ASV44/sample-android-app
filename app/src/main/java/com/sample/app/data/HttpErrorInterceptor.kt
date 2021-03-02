package com.sample.app.data

import com.google.gson.JsonParseException
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * Created by Vdovicenco Alexandr on 03/02/2021.
 */

class HttpErrorInterceptor(
    private val apiErrorResponseConverter: () -> Converter<ResponseBody, ApiError>
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        return if (!response.isSuccessful) {
            val apiError = try {
                response.body()?.let { apiErrorResponseConverter().convert(it) } ?: ApiError()
            } catch (e: JsonParseException) {
                ApiError()
            }
            when (response.code()) {
                400 -> throw BadRequestException(apiError)
                401 -> throw UnauthorizedAccessException(apiError)
                else -> response
            }
        } else {
            response
        }
    }
}
