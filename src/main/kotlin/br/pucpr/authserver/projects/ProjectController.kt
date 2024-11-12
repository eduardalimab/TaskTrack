package br.pucpr.authserver.projects

import br.pucpr.authserver.projects.requests.ProjectRequest
import br.pucpr.authserver.projects.responses.ProjectResponse
import br.pucpr.authserver.task.TaskResponse
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.data.domain.Sort


@RestController
@Transactional
@RequestMapping("/projects")
@Validated
class ProjectController(
    val service: ProjectService,
) {

    @GetMapping
    fun listProjects() = service.findAll().map { it.toResponse() }

    @PostMapping
    fun createProject(@RequestBody @Validated req: ProjectRequest) =
        service.save(req)
            .toResponse()
            .let { ResponseEntity.status(CREATED).body(it) }


    @GetMapping("/{id}")
    fun getProjects(@PathVariable("id") id: Long) =
        service.getById(id).orElse(null)
            ?.let {
                ResponseEntity.ok(it.toResponse())
            } ?: ResponseEntity.notFound().build()


    @GetMapping("/{id}/tasks")
    fun getTasksByProjectId(@PathVariable("id") id: Long): ResponseEntity<List<TaskResponse>> {
        val tasks = service.getTasksByProjectId(id).map { TaskResponse(it) }
        return ResponseEntity.ok(tasks)
    }

    @GetMapping("/filter")
    fun filterProjects(
        @RequestParam(required = false) startDate: String?,
        @RequestParam(defaultValue = "start_date,asc") sort: String
    ): List<ProjectResponse> {
        val sortParams = sort.split(",")
        val sortOrder = if (sortParams.size > 1 && sortParams[1].equals("desc", true)) Sort.Direction.DESC else Sort.Direction.ASC
        val sortBy = Sort.by(sortOrder, sortParams[0])
        return service.findByStartDate(startDate).map { it.toResponse() }
    }


    private fun Project.toResponse() = ProjectResponse(id!!, name, description, start_date, end_date, status)
}




