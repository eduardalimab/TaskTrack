package br.pucpr.authserver.task

data class TaskResponse(
    val id : Long,
    val name: String,
) {
    constructor(task: Task): this(
        id = task.id!!,
        name=task.name,
    )
}
