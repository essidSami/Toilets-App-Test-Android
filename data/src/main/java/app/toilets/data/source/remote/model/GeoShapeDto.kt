package app.toilets.data.source.remote.model

data class GeoShapeDto(
    val coordinates: List<List<Double>>,
    val type: String
)