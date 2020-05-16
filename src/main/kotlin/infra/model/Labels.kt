package infra.model

data class Labels(
    val labels: List<Label>
)

data class Label(
    val name: String,
    val color: String,
    val description: String
)
