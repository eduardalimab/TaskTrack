package br.pucpr.authserver.projects

import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository: JpaRepository<Task, Long> {
    fun findByName(name: String): Task?
}