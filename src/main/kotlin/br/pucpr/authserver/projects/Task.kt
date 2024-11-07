package br.pucpr.authserver.projects

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany

@Entity
open class Task (
    @Id @GeneratedValue
    val id: Long? = null,

    @Column(unique = true, nullable = false)
    val name: String = "",

    @ManyToMany(mappedBy = "tasks")
    val projects: MutableSet<Project> = mutableSetOf()

) {
}