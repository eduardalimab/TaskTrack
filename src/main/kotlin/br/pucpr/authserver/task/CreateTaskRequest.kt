package br.pucpr.authserver.task

import jakarta.validation.constraints.NotBlank


data class CreateTaskRequest(
    @field:NotBlank
    val name: String?
) {
    fun toTask() = Task(
        name=name!!,
    )
}
