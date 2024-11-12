package br.pucpr.authserver.task

import br.pucpr.authserver.projects.ProjectRepository
import jakarta.validation.Valid
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TaskController(
    val service: TaskService,
    val projectRepository: ProjectRepository
) {
    @PostMapping
    fun insert(
        @RequestBody @Valid taskRequest: CreateTaskRequest
    ): ResponseEntity<TaskResponse> {
        val project = projectRepository.findById(taskRequest.projectId!!)
            .orElseThrow { IllegalArgumentException("Project not found") }
        val task = service.insert(taskRequest.toTask(project))
        return ResponseEntity.status(CREATED).body(TaskResponse(task))
    }

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
