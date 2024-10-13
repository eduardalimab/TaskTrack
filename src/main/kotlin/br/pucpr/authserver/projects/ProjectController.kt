package br.pucpr.authserver.projects

import br.pucpr.authserver.projects.requests.ProjectRequest
import br.pucpr.authserver.projects.responses.ProjectResponse
import jakarta.transaction.Transactional
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


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
        service.save(Project(name = req.name, description = req.description, start_date = req.start_date, end_date = req.end_date, status = req.status))
            .toResponse()
            .let { ResponseEntity.status(CREATED).body(it) }


    @GetMapping("/{id}")
    fun getProjects(@PathVariable("id") id: Long) =
        service.getById(id).orElse(null)
            ?.let { ResponseEntity.ok(it.toResponse())
        } ?: ResponseEntity.notFound().build()



    private fun Project.toResponse() = ProjectResponse(id!!, name, description, start_date, end_date, status)
}




