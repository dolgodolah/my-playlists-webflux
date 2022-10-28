package com.myplaylists.exception

import com.myplaylists.dto.ResponseCode
import java.lang.RuntimeException

open class ApiException(
    val statusCode: Int,
    override val message: String?,
): RuntimeException(message)

class NotFoundException(
    message: String? = null
): ApiException(ResponseCode.NOT_FOUND.statusCode, message)