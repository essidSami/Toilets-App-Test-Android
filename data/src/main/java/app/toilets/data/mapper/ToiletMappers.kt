package app.toilets.data.mapper

import app.toilets.data.source.remote.model.RecordDto
import app.toilets.data.source.remote.model.ToiletsDto
import app.toilets.domain.model.Toilet
import app.toilets.domain.model.ToiletList

class ToiletMappers {

    fun ToiletsDto.toToiletList() = ToiletList(
        count = count,
        toilets = records.map { it.toToilet() }
    )

    private fun RecordDto.toToilet() = Toilet(
        type = fields.type,
        status = "",
        address = fields.adresse,
        arrondissement = fields.arrondissement,
        horaire = fields.horaire,
        accesPmr = fields.accesPmr,
        relaisBebe = fields.relaisBebe,
        urlFicheEquipement = fields.urlFicheEquipement,
        geoShape = Pair(
            first = fields.geoShape.coordinates[0][0],
            second = fields.geoShape.coordinates[0][1]
        ),
        geometry = Pair(first = fields.geoPoint[0], fields.geoPoint[1]),
        gestionnaire = fields.gestionnaire,
        source = fields.source,
        complementAdresse = fields.complementAdresse
    )
}