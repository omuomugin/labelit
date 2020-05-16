package domain.usecase

import domain.model.Label
import domain.model.Repository
import domain.model.Token
import infra.query.GitHubLabelQueryService

class FetchLabelUseCase(private val service: GitHubLabelQueryService) {
    fun getLabels(repository: Repository, token: Token): List<Label> {
        return service.fetchLabels(owner = repository.ownerName, repoName = repository.name, token = token.token)
    }
}