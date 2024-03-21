package tech.dsvdev

import tech.dsvdev.config.YamlPropertySourceFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource

@PropertySource(value = ["classpath:application.yml"], factory = YamlPropertySourceFactory::class)
@SpringBootApplication
class AfinaApplication

fun main(args: Array<String>) {
    runApplication<AfinaApplication>(*args)
}
