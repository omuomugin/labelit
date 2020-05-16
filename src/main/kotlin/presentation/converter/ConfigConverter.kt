package presentation.converter

import domain.model.Config as DomainConfig
import domain.model.Label as DomainLabel

import presentation.model.Config as PresentationConfig
import presentation.model.Label as PresentationLabel

class ConfigConverter {
    fun convert(presentationConfig: PresentationConfig): DomainConfig {
        val labels = presentationConfig.labels.asSequence().map {
            convertLabels(it)
        }.toList()

        return DomainConfig(labels)
    }

    private fun convertLabels(presentationLabel: PresentationLabel): DomainLabel {
        return DomainLabel(
            name = presentationLabel.name,
            color = presentationLabel.color,
            description = if (presentationLabel.description.isNotBlank()) {
                presentationLabel.description
            } else ""
        )
    }
}