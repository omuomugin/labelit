package presentation.runner

import domain.model.Label
import domain.usecase.ShowLabelUseCase

class ShowCommandRunner : CommandRunner {
    override fun run(): String {
        val labels = ShowLabelUseCase().getLabels()

        var resultStr = ""
        labels.forEach {
            resultStr += "${labelToString(it)}\n"
        }
        return resultStr
    }

    private fun labelToString(label: Label): String {
        return """
            ${label.name} : #${label.color} (description: ${label.description})
        """.trimIndent()
    }
}