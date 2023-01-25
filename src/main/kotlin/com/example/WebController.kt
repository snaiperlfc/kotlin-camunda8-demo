package com.example

import io.camunda.zeebe.spring.client.lifecycle.ZeebeClientLifecycle
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequiredArgsConstructor
class WebController {

    @Autowired
    private val client: ZeebeClientLifecycle? = null

    @GetMapping("/start")
    fun startWorkflowInstance(): String? {
        // and kick of a new flow instance
        val workflowInstanceResult = client!!
            .newCreateInstanceCommand()
            .bpmnProcessId("test-process")
            .latestVersion()
            .variables("{\"name\": \"Josh Wulf\"}")
            .withResult()
            .send()
            .join()
        return workflowInstanceResult
            .variablesAsMap
            .getOrDefault("say", "Error: No greeting returned") as String?
    }
}
