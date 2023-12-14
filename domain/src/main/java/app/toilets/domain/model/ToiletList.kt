package app.toilets.domain.model

data class ToiletList(
    val count: Int,
    val toilets: List<Toilet>
)