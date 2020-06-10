package bep.lingogame

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.Banner

@SpringBootApplication
class LingogameApplication

fun main(args: Array<String>) {
	runApplication<LingogameApplication>(*args){
		setBannerMode(Banner.Mode.OFF)
	}
}
