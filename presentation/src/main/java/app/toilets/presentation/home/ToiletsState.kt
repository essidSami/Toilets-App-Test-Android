package app.toilets.presentation.home

import app.toilets.domain.model.Toilet

data class ToiletsState(
    val toiletList: List<Toilet> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val endReached: Boolean = false,
)