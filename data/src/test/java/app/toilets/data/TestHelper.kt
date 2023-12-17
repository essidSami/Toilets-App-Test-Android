package app.toilets.data

import app.toilets.data.source.remote.model.FieldsDto
import app.toilets.data.source.remote.model.GeoShapeDto
import app.toilets.data.source.remote.model.GeometryDto
import app.toilets.data.source.remote.model.ParametersDto
import app.toilets.data.source.remote.model.RecordDto
import app.toilets.data.source.remote.model.ToiletsDto
import app.toilets.domain.model.Toilet

val wsResponse = ToiletsDto(
    count = 617,
    parameters = ParametersDto(
        dataset = "sanisettesparis2011",
        format = "json",
        rows = 20,
        start = 0,
        timezone = "UTC"
    ),
    records = listOf(
        RecordDto(
            datasetId = "sanisettesparis2011",
            recordId = "6abfc17e3d6e72b8fda24759466d031f7f9192f6",
            fields = FieldsDto(
                complementAdresse = "numero_de_voie nom_de_voie",
                geoShape = GeoShapeDto(
                    coordinates = listOf(listOf(2.2682280857896977, 48.8623619666065)),
                    type = "MultiPoint"
                ),
                accesPmr = "Oui",
                relaisBebe = null,
                urlFicheEquipement = null,
                adresse = "BOULEVARD SUCHET",
                arrondissement = "75016",
                geoPoint = listOf(48.8623619666065, 2.2682280857896977),
                gestionnaire = "Toilette publique de la Ville de Paris",
                horaire = "6 h - 22 h",
                source = "http://opendata.paris.fr",
                type = "SANISETTE"
            ),
            geometry = GeometryDto(
                coordinates = listOf(2.2682280857896977, 48.8623619666065),
                type = "Point"
            ),
            recordTimestamp = "2023-12-04T05:12:00.43Z"
        )
    )
)

val toilets = listOf(
    Toilet(
        type = "SANISETTE",
        status = "",
        address = "BOULEVARD SUCHET",
        arrondissement = "75016",
        horaire = "6 h - 22 h",
        accesPmr = true,
        relaisBebe = false,
        urlFicheEquipement = "",
        geoShape = Pair(2.2682280857896977, 48.8623619666065),
        geometry = Pair(48.8623619666065, 2.2682280857896977),
        gestionnaire = "Toilette publique de la Ville de Paris",
        source = "http://opendata.paris.fr",
        complementAdresse = "numero_de_voie nom_de_voie",
        distance = 0.0F
    )
)