package br.pucpr.authserver.projects.responses

data class ProjectResponse(
    val id : Long,
    val name : String,
    val description : String,
    val start_date : String,
    val end_date : String,
    val status : String
)
