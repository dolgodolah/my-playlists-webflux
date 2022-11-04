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

class BadRequestException(
    message: String? = null
): ApiException(ResponseCode.BAD_REQUEST.statusCode, message)

class SignupRequiredException(
    message: String = "회원가입을 먼저 진행해주세요.",
): ApiException(ResponseCode.SIGNUP_REQUIRED.statusCode, message)