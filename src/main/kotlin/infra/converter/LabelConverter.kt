package infra.converter

import domain.model.Label as DomainLabel
import infra.model.Label as InfraLabel

object LabelConverter {
    fun convertToDomainModel(infraLabels: List<InfraLabel>): List<DomainLabel> {
        return infraLabels.asSequence().map {
            DomainLabel(
                name = it.name,
                color = it.color,
                description = it.description
            )
        }.toList()
    }

    fun convertToInfraModel(domainLabels: List<DomainLabel>): List<InfraLabel> {
        return domainLabels.asSequence().map {
            InfraLabel(
                name = it.name,
                color = it.color,
                description = it.description
            )
        }.toList()
    }
}