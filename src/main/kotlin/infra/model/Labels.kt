package infra.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

@Serializable
data class Label(
    val name: String,
    val color: String,
    val description: String
)

fun fromJson(jsonStr: String): List<Label> {
    return Json(JsonConfiguration(ignoreUnknownKeys = true))
        .parse(Label.serializer().list, jsonStr)
}

fun toJson(label: Label): String {
    return Json(JsonConfiguration.Stable)
        .stringify(Label.serializer(), label)
}
