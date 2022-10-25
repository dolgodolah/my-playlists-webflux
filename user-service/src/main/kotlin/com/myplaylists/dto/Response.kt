package com.myplaylists.dto

enum class ResponseCode(
    val statusCode: Int,
    val message: String? = null
) {
    SUCCESS(200),
}

open class SuccessResponse(
    val statusCode: Int = ResponseCode.SUCCESS.statusCode
) {
}


}