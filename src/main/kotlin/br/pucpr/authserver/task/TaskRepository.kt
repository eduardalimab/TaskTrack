package br.pucpr.authserver.task

import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long> {
    fun findByName(name: String): Task?
}