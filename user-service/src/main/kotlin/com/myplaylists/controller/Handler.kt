package com.myplaylists.controller

import com.myplaylists.dto.ErrorResponse
import com.myplaylists.exception.ApiException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import reactor.core.publisher.Mono

@RestControllerAdvice
class Handler {

    @ExceptionHandler(ApiException::class)
    fun exceptionHandler(e: ApiException): Mono<ErrorResponse> {
        return Mono.just(ErrorResponse(e.statusCode, e.message))
    }
}