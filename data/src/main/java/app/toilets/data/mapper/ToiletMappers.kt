package app.toilets.data.mapper

import app.toilets.data.source.remote.model.RecordDto
import app.toilets.domain.model.Toilet

fun RecordDto.toToilet() = Toilet(
    type = fields?.type ?: "",
    status = "",
    address = fields?.adresse ?: "",
    arrondissement = fields?.arrondissement ?: "",
    horaire = fields?.horaire ?: "",
    accesPmr = fields?.accesPmr ?: "",
    relaisBebe = fields?.relaisBebe ?: "",
    urlFicheEquipement = fields?.urlFicheEquipement ?: "",
    geoShape = fields?.geoShape?.coordinates?.get(0)?.let {
        Pair(first = it[0], second = it[1])
    },
    geometry = fields?.geoPoint?.let {
        Pair(first = it[0], it[1])
    },
    gestionnaire = fields?.gestionnaire ?: "",
    source = fields?.source ?: "",
    complementAdresse = fields?.complementAdresse ?: ""
)