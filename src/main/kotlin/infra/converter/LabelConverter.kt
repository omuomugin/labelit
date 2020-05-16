package infra.converter

import domain.model.Label as DomainLabel
import infra.model.Label as InfraLabel

object LabelConverter {
    fun convert(infraLabels: List<InfraLabel>): List<DomainLabel> {
        return infraLabels.asSequence().map {
            DomainLabel(
                name = it.name,
                color = it.color,
                description = it.description
            )
        }.toList()
    }
}