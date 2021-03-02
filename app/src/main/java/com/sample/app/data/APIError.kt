package com.sample.app.data

import com.google.gson.annotations.SerializedName

/**
 * Created by Vdovicenco Alexandr on 03/02/2021.
 */

data class ApiError(
    @SerializedName("status")
    val statusCode: Int? = null,
    @SerializedName("message")
    val clientErrorMessage: String? = null,
    @SerializedName("error")
    val error: String? = null
)