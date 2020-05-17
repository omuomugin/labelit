package infra.converter

import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
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

    fun convertFromJson(jsonStr: String): List<InfraLabel> {
        return Json(JsonConfiguration(ignoreUnknownKeys = true))
            .parse(infra.model.Label.serializer().list, jsonStr)
    }

    fun convertToJson(label: InfraLabel): String {
        return Json(JsonConfiguration.Stable)
            .stringify(infra.model.Label.serializer(), label)
    }
}