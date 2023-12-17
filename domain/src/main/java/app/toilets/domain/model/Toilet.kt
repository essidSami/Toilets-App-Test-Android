package app.toilets.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Toilet(
    val type: String,
    val status: String,
    val address: String,
    val arrondissement: String,
    val horaire: String,
    val accesPmr: Boolean = false,
    val relaisBebe: Boolean = false,
    val urlFicheEquipement: String,
    val geoShape: Pair<Double, Double>?,
    val geometry: Pair<Double, Double>?,
    val gestionnaire: String,
    val source: String,
    val complementAdresse: String,
    val distance: Float
): Parcelable