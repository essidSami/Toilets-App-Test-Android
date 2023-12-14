package app.toilets.data.source.remote.model

data class ParametersDto(
    val dataset: String,
    val format: String,
    val rows: Int,
    val start: Int,
    val timezone: String
)