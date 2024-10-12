package br.pucpr.authserver.projects

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank


@Entity
class Project (
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    var name: String,

    @NotBlank
    var description: String,

    @NotBlank
    var start_date: String,

    @NotBlank
    var end_date: String,

    @NotBlank
    var status: String,

)