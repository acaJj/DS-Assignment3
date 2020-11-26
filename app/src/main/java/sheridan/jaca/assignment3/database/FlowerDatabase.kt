package sheridan.jaca.assignment3.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FlowerEntity::class], version = 1)
abstract class FlowerDatabase : RoomDatabase(){

}