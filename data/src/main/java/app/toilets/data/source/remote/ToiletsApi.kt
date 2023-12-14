package app.toilets.data.source.remote

import app.toilets.data.source.remote.model.ToiletsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ToiletsApi {
    @GET("search")
    suspend fun getProducts(
        @Query("dataset") dataSet: String,
        @Query("start") start: Int,
        @Query("rows") rows: Int
    ): ToiletsDto
}