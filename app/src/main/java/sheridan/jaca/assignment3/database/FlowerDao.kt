package sheridan.jaca.assignment3.database

import androidx.lifecycle.LiveData
import androidx.room.*
import sheridan.jaca.assignment3.domain.Flower

@Dao
interface FlowerDao{
    @Query("SELECT * FROM flowers")
    fun getAll(): LiveData<List<FlowerEntity>>

    @Query("SELECT * FROM flowers WHERE id=:id")
    suspend fun get(id:Long): FlowerEntity

    @Insert
    suspend fun insert(flower: FlowerEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll( flowers: List<FlowerEntity>)


    @Delete
    suspend fun delete(flower: FlowerEntity)

    @Query("DELETE FROM flowers WHERE id=:id")
    suspend fun delete(id: Long)

    @Update
    suspend fun update(flower: FlowerEntity)
}