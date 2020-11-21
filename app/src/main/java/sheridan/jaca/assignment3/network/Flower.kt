package sheridan.jaca.assignment3.network

import com.squareup.moshi.Json

data class Flower (
    val id: String,
    @Json(name="pictures.small") val smallImgSrcUrl: String,
    @Json(name="pictures.large") val largeImgSrcUrl: String,
    val name: String,
    val label: String,
    val text: String,
    val price: Double
)