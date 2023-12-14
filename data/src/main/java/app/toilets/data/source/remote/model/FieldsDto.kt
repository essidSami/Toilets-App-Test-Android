package app.toilets.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class FieldsDto(
    @SerializedName("complement_adresse")
    val complementAdresse: String?,
    @SerializedName("geo_shape")
    val geoShape: GeoShapeDto?,
    @SerializedName("acces_pmr")
    val accesPmr: String?,
    @SerializedName("relais_bebe")
    val relaisBebe: String?,
    @SerializedName("url_fiche_equipement")
    val urlFicheEquipement: String?,
    val adresse: String?,
    val arrondissement: String?,
    @SerializedName("geo_point_2d")
    val geoPoint: List<Double>?,
    val gestionnaire: String?,
    val horaire: String?,
    val source: String?,
    val type: String?
)