package infra.query

import domain.model.Label

class GitHubLabelQueryServiceImpl : GitHubLabelQueryService {
    override fun fetchLabels(): List<Label> {
        // TODO: fetch from api
        return listOf(
            Label("hoge", "000000", ""),
            Label("hoge", "000000", ""),
            Label("hoge", "000000", ""),
            Label("hoge", "000000", "")
        )
    }

}