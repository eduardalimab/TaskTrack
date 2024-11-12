package br.pucpr.authserver.projects

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository : JpaRepository<Project, Long>, JpaSpecificationExecutor<Project> {

    @Query("SELECT p FROM Project p WHERE (:startDate IS NULL OR p.start_date = :startDate) ORDER BY p.start_date DESC")
    fun findByStartDate(@Param("startDate") startDate: String?): List<Project>
}