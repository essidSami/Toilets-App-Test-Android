package app.toilets.data.source.remote.model
import com.google.gson.annotations.SerializedName

data class RecordDto(
    @SerializedName("datasetid")
    val datasetId: String,
    val fields: FieldsDto,
    val geometry: GeometryDto,
    @SerializedName("record_timestamp")
    val recordTimestamp: String,
    @SerializedName("recordid")
    val recordId: String
)