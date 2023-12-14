package app.toilets.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class ToiletsDto(
    @SerializedName("nhits")
    val count: Int?,
    val parameters: ParametersDto?,
    val records: List<RecordDto>?
)