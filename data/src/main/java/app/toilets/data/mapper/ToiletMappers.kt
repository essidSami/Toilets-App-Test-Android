package app.toilets.data.mapper

import android.location.Location
import app.toilets.data.source.remote.model.RecordDto
import app.toilets.domain.model.Toilet

fun RecordDto.toToilet(currentLocation: Location) = Toilet(
    type = fields?.type ?: "",
    status = "",
    address = fields?.adresse ?: "",
    arrondissement = fields?.arrondissement ?: "",
    horaire = fields?.horaire ?: "",
    accesPmr = fields?.accesPmr == "Oui",
    relaisBebe = fields?.relaisBebe == "Oui",
    urlFicheEquipement = fields?.urlFicheEquipement ?: "",
    geoShape = fields?.geoShape?.coordinates?.get(0)?.let {
        Pair(first = it[0], second = it[1])
    },
    geometry = fields?.geoPoint?.let {
        Pair(first = it[0], it[1])
    },
    gestionnaire = fields?.gestionnaire ?: "",
    source = fields?.source ?: "",
    complementAdresse = fields?.complementAdresse ?: "",
    distance = fields?.geoShape?.coordinates?.get(0)?.let {
        val targetLocation = Location("")
        targetLocation.latitude = it[1]
        targetLocation.longitude = it[0]
        currentLocation.distanceTo(targetLocation)/1000
    }?:0F
)