package infra.model

import kotlinx.serialization.Serializable

@Serializable
data class Label(
    val name: String,
    val color: String,
    val description: String
)
