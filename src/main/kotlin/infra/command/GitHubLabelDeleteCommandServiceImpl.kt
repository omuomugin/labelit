package infra.command

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult
import domain.model.Label
import infra.converter.LabelConverter
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking

class GitHubLabelDeleteCommandServiceImpl : GitHubLabelDeleteCommandService {
    override fun deleteLabels(owner: String, repoName: String, token: String, labels: List<Label>) {
        runBlocking {
            val deferreds: List<Deferred<Unit>> = LabelConverter.convertToInfraModel(labels)
                .map { label ->
                    async() {
                        val (_, _, result) = Fuel.delete("https://api.github.com/repos/${owner}/${repoName}/labels/${label.name}")
                            .authentication()
                            .bearer(token)
                            .awaitStringResponseResult()

                        result.fold(
                            {},
                            { error -> throw Exception("${error.message}") }
                        )
                    }
                }

            deferreds.awaitAll()
        }
    }

}
