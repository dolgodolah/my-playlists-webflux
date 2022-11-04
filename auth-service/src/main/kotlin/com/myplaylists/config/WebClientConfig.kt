package com.myplaylists.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
    @Value("\${user-service.url}")
    private val userServiceUrl: String
) {

    @Bean(name = ["userServiceClient"])
    fun userServiceClient(): WebClient = WebClient.builder()
            .baseUrl(userServiceUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()
}