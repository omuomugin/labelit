package presentation.model

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val labels: List<Label>
)

@Serializable
data class Label(
    val name: String,
    val color: String,
    val description: String = ""
)
