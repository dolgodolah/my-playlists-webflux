package com.myplaylists

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyPlaylistsWebfluxApplication

fun main(args: Array<String>) {
	runApplication<MyPlaylistsWebfluxApplication>(*args)
}
