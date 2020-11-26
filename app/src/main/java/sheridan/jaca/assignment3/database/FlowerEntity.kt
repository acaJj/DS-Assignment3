package sheridan.jaca.assignment3.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flowers")
data class FlowerEntity(
    @PrimaryKey
    val id : Long,
    @ColumnInfo(name = "label")
    val label:String,
    @ColumnInfo(name = "price")
    val price:String,
    @ColumnInfo(name = "imgUrl")
    val imgUrl:String,
    @ColumnInfo(name = "text")
    val text:String,
)