package com.myplaylists.router

import com.myplaylists.handler.HelloHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RequestPredicates.accept

@Configuration
class HelloRouter {

    @Bean
    fun route(helloHandler: HelloHandler): RouterFunction<ServerResponse> {
        return RouterFunctions
            .route(GET("/hello").and(accept(MediaType.APPLICATION_JSON)), helloHandler::hello)
    }
}