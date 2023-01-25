package com.example;

import io.camunda.zeebe.client.api.response.ActivatedJob
import io.camunda.zeebe.client.api.worker.JobClient
import io.camunda.zeebe.spring.client.annotation.JobWorker
import lombok.RequiredArgsConstructor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate


@Component
@Suppress("unused")
@RequiredArgsConstructor
class DemoAdapter {

    val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @JobWorker(type = "get-time")
        fun handleGetTime(client: JobClient, job: ActivatedJob) {
            log.info(job.toString())
            val uri = "https://json-api.joshwulf.com/time"

            val restTemplate = RestTemplate()
            val result = restTemplate.getForObject(uri, String::class.java)!!

            client.newCompleteCommand(job.key)
                .variables("{\"time\":$result}")
                .send().join()
        }

    @JobWorker(type = "make-greeting")
    fun handleMakeGreeting(client: JobClient, job: ActivatedJob) {
        val headers = job.customHeaders
        val greeting = headers.getOrDefault("greeting", "Good day")
        val variablesAsMap = job.variablesAsMap
        val name = variablesAsMap.getOrDefault("name", "there") as String
        val say = "$greeting $name"
        client.newCompleteCommand(job.key)
                .variables("{\"say\": \"$say\"}")
                .send().join()
    }
}
