package sheridan.jaca.assignment3.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FlowerEntity::class], version = 1)
abstract class FlowerDatabase : RoomDatabase(){
    abstract fun flowerDao(): FlowerDao

    companion object {
        @Volatile private var INSTANCE: FlowerDatabase? = null

        fun getInstance(context: Context): FlowerDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    FlowerDatabase::class.java,
                    "donut_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}