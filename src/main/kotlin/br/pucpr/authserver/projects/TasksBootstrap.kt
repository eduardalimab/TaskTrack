package br.pucpr.authserver.projects

import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextStartedEvent
import org.springframework.stereotype.Component

@Component
class TasksBootstrap(
    val taskRepositoy: TaskRepository,
    val projectRepository: ProjectRepository
): ApplicationListener<ContextStartedEvent> {

    override fun onApplicationEvent(event: ContextStartedEvent) {
        if (taskRepositoy.count() == 0L) {
            taskRepositoy.save(Task(name = "Task 1"))
            taskRepositoy.save(Task(name = "Task 2"))
        }
        if (projectRepository.count() == 0L) {
            projectRepository.save(Project(
                name = "Project 1",
                description = "Description 1",
                start_date = "Start 1",
                end_date = "End 1",
                status = "Status 1",))
        }
    }
}