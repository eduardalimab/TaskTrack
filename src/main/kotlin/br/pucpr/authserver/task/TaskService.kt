package br.pucpr.authserver.task

import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class TaskService(
    val repository: TaskRepository
) {
    fun insert(task : Task) =
        repository.save(task)

    fun findAll(): List<Task> =
        repository.findAll(Sort.by("name"))

    fun findbyName(name: String): Task? =
        repository.findByName(name)

    fun deleteById(id: Long): Boolean {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            true
        } else {
            false
        }
    }
}
