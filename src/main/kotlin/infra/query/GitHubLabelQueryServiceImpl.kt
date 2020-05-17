package infra.query

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import domain.model.Label
import infra.converter.LabelConverter
import kotlinx.coroutines.runBlocking

class GitHubLabelQueryServiceImpl : GitHubLabelQueryService {
    override fun fetchLabels(owner: String, repoName: String, token: String): List<Label> {
        val result = runBlocking {
            val (_, _, result) = Fuel.get("https://api.github.com/repos/${owner}/${repoName}/labels")
                .authentication()
                .bearer(token)
                .awaitStringResponseResult()

            result
        }

        return result.fold({ data ->
            val labels = LabelConverter.convertFromJson(data)
            LabelConverter.convertToDomainModel(labels)
        }, { error -> throw Exception("${error.message}") })
    }
}