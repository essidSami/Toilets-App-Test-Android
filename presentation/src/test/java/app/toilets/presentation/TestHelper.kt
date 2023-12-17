package app.toilets.presentation

import app.toilets.domain.model.Toilet

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