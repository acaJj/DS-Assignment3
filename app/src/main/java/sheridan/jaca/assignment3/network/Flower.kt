package sheridan.jaca.assignment3.network

import com.squareup.moshi.Json

data class Flower (
    val id: String,
    val pictures: FlowerPicture,
    val name: String,
    val label: String,
    val text: String,
    val price: Double
)