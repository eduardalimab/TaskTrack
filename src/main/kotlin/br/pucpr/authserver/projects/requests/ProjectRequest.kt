package br.pucpr.authserver.projects.requests

import jakarta.validation.constraints.NotBlank
import org.springframework.format.annotation.DateTimeFormat

data class ProjectRequest(

    @field:NotBlank
    val name : String ,
    @field:NotBlank
    val description : String,

    @field:NotBlank
    val start_date : String,

    @field:NotBlank
    val end_date : String,

    @field:NotBlank
    val status : String
)
