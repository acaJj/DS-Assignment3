package sheridan.jaca.assignment3.network

data class FlowerJson (
    val pictures: FlowerPictureJson,
    val name: String,
    val label: String,
    val text: String,
    val price: String
)