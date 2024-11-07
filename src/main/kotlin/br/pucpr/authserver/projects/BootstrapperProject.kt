package br.pucpr.authserver.projects

import br.pucpr.authserver.task.Task
import br.pucpr.authserver.task.TaskRepository
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class BootstrapperProject(
    val projectRepository: ProjectRepository,
    private val taskRepository: TaskRepository,
): ApplicationListener<ContextRefreshedEvent> {

    companion object {
        private val log = LoggerFactory.getLogger(BootstrapperProject::class.java)
    }

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        if (projectRepository.count() == 0L) {
            log.info("Bootstrapping projects")
            val project = Project(
                name = "Project 1",
                description = "Description 1",
                start_date = "01-01-2020",
                end_date = "01-01-2021",
                status = "OPEN"
            )
            projectRepository.save(project)
            val task = taskRepository.save(Task(name = "Task 1", project = project))
            log.info("Bootstrapped project with id ${project.id}")
            log.info("Bootstrapped task with id ${task.id}")
        }
    }
}