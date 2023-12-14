package app.toilets.domain.model

data class Toilet(
    val type: String,
    val status: String,
    val address: String,
    val arrondissement: String,
    val horaire: String,
    val accesPmr: String,
    val relaisBebe: String,
    val urlFicheEquipement: String,
    val geoShape: Pair<Double, Double>,
    val geometry: Pair<Double, Double>,
    val gestionnaire: String,
    val source: String,
    val complementAdresse: String,

)