package com.myplaylists.router

import com.myplaylists.handler.UserHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RequestPredicates.accept

@Configuration
class UserRouter {

    @Bean
    fun route(userHandler: UserHandler): RouterFunction<ServerResponse> {
        return RouterFunctions
            .route(GET("/me").and(accept(MediaType.APPLICATION_JSON)), userHandler::getMyInfo)
    }
}