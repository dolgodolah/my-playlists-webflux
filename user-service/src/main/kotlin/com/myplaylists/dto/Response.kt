package com.myplaylists.dto

enum class ResponseCode(
    val statusCode: Int,
    val message: String? = null
) {
    SUCCESS(200),
    NOT_FOUND(404, "요청하신 데이터를 찾을 수 없습니다.")
}

open class SuccessResponse(
    val statusCode: Int = ResponseCode.SUCCESS.statusCode
) {
}

data class ErrorResponse(
    val statusCode: Int,
    var message: String? = null,
) {
    constructor(responseCode: ResponseCode, message: String?): this(responseCode.statusCode, message)
    constructor(responseCode: ResponseCode): this(responseCode.statusCode, responseCode.message)

}