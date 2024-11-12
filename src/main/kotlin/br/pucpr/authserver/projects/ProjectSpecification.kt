package br.pucpr.authserver.projects

import org.springframework.data.jpa.domain.Specification

class ProjectSpecification {
    companion object {
        fun hasStartDate(startDate: String?): Specification<Project>? {
            return startDate?.let {
                Specification { root, _, criteriaBuilder ->
                    criteriaBuilder.equal(root.get<String>("start_date"), it)
                }
            }
        }
    }
}