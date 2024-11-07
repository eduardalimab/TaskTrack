package br.pucpr.authserver.task

data class TaskResponse(
    val name: String,
) {
    constructor(task: Task): this(
        name=task.name,
    )
}
