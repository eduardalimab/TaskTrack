package br.pucpr.authserver.task//package br.pucpr.authserver.projects
//
//import org.springframework.context.ApplicationListener
//import org.springframework.context.event.ContextStartedEvent
//import org.springframework.stereotype.Component
//import kotlin.math.log
//
//@Component
//class TasksBootstrap(
//    val taskRepository: TaskRepository,
//    val projectRepository: ProjectRepository
//): ApplicationListener<ContextStartedEvent> {
//
//    override fun onApplicationEvent(event: ContextStartedEvent) {
//        if (taskRepository.count() == 0L) {
//            taskRepository.save(Task(name = "Task 1"))
//            taskRepository.save(Task(name = "Task 2"))
//        }
//        if (projectRepository.count() == 0L) {
//            projectRepository.save(Project(
//                name = "Project 1",
//                description = "Description 1",
//                start_date = "Start 1",
//                end_date = "End 1",
//                status = "Status 1",))
//        }
//     }
//}
