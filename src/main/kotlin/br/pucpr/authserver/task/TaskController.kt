package br.pucpr.authserver.task

import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TaskController(
    val service: TaskService
) {
    @PostMapping
    fun insert(
        @RequestBody @Valid task : CreateTaskRequest
    ) = service.insert(task.toTask())
        .let { TaskResponse(it) }
        .let { ResponseEntity.status(CREATED).body(it) }

    @GetMapping
    fun list() =
        service.findAll()
            .map { TaskResponse(it) }

    @GetMapping("/{name}")
    fun findByName(@PathVariable("name") name: String) =
        service.findbyName(name)
            ?.let { TaskResponse(it) }
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.notFound().build()
}
