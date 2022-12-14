package com.myplaylists.dto

enum class ResponseCode(
    val statusCode: Int,
) {
    SUCCESS(200),
    BAD_REQUEST(400),
    NOT_FOUND(404),
    SIGNUP_REQUIRED(470)
}

open class SuccessResponse(
    val statusCode: Int = ResponseCode.SUCCESS.statusCode
) {
}

data class ErrorResponse(
    val statusCode: Int,
    var message: String? = null,
) {
}