package app.toilets.presentation.home

import app.toilets.domain.model.ToiletList

data class ToiletsState(
    val toiletList: ToiletList? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)