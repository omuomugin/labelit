package infra.query

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import domain.model.Label
import infra.converter.LabelConverter
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

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
            val jsonData = Json(JsonConfiguration(ignoreUnknownKeys = true)).parse(
                infra.model.Label.serializer().list,
                data
            )
            LabelConverter.convert(jsonData)
        }, { error -> throw Exception("${error.message}") })
    }
}