package com.example

import io.camunda.zeebe.spring.client.EnableZeebeClient
import io.camunda.zeebe.spring.client.annotation.Deployment
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@SpringBootApplication
@EnableZeebeClient
@EnableDiscoveryClient
@Deployment(resources = ["test-process.bpmn"])
class KotlinCamunda8DemoApplication

fun main(args: Array<String>) {
    runApplication<KotlinCamunda8DemoApplication>(*args)
}
