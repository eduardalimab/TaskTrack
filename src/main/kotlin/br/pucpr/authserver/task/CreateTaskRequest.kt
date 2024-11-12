package br.pucpr.authserver.task

import br.pucpr.authserver.projects.Project
import jakarta.validation.constraints.NotNull


data class CreateTaskRequest(
    val name: String?,

    @field:NotNull
    val projectId: Long?
) {
    fun toTask(project: Project) = Task(
        name = name ?: "",
        project = project
    )
}