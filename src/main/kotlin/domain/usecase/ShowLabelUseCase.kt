package domain.usecase

import domain.model.Label
import infra.query.GitHubLabelQueryServiceImpl

class ShowLabelUseCase {
    fun getLabels(): List<Label> {
        return GitHubLabelQueryServiceImpl().fetchLabels()
    }
}