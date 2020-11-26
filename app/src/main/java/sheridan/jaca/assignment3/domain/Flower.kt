package sheridan.jaca.assignment3.domain

import java.io.Serializable

data class Flower (
    val label: String,
    val price:String,
    val imgUrl: String,
    val text: String,
    val id: Long = 0L
): Serializable