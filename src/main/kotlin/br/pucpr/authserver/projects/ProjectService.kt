package br.pucpr.authserver.projects

import org.springframework.stereotype.Service

@Service
class ProjectService (val repository: ProjectRepository) {

    fun save(project: Project) = repository.save(project)

    fun getById(id: Long) = repository.findById(id)

    fun findAll() = repository.findAll()


}