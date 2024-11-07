package br.pucpr.authserver.projects

import br.pucpr.authserver.projects.requests.ProjectRequest
import br.pucpr.authserver.task.TaskRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class ProjectService (val repository: ProjectRepository, val taskRepository: TaskRepository) {

    fun save(req: ProjectRequest): Project {
        val project = Project(
            name = req.name,
            description = req.description,
            start_date = req.start_date,
            end_date = req.end_date,
            status = req.status)
        repository.save(project
        )

//        val projectTask = taskRepository.findByName("Back")
//        project.tasks.add(projectTask!!)
        return repository.save(project)
        }


    fun getById(id: Long) = repository.findById(id)

    fun findAll() = repository.findAll()


}