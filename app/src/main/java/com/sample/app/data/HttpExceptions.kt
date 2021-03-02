package com.sample.app.data

import java.io.IOException

/**
 * Created by Vdovicenco Alexandr on 03/02/2021.
 */

sealed class HttpException(apiError: ApiError) : IOException(apiError.error)

class BadRequestException(apiError: ApiError) : HttpException(apiError)
class UnauthorizedAccessException(apiError: ApiError) : HttpException(apiError)