package br.pucpr.authserver.projects

import br.pucpr.authserver.task.Task
import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank

@Entity
@Table(name = "projects")
class Project (
    @Id
    @GeneratedValue
    var id: Long? = null,
    @field:NotBlank
    var name: String,
    @field:NotBlank
    var description: String,
    var start_date: String,
    var end_date: String,
    var status: String,

    @OneToMany(mappedBy = "project")
    var tasks: MutableList<Task> = mutableListOf()


)