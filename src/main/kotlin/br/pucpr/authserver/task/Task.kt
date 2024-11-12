package br.pucpr.authserver.task

import br.pucpr.authserver.projects.Project
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
open class Task (
    @Id @GeneratedValue
    val id: Long? = null,

    @Column(unique = true, nullable = false)
    val name: String = "",

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id", nullable = false)
    val project: Project


) {
}