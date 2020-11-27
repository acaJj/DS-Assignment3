package sheridan.jaca.assignment3.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import sheridan.jaca.assignment3.database.FlowerDatabase
import sheridan.jaca.assignment3.database.FlowerEntity
import sheridan.jaca.assignment3.domain.Flower
import sheridan.jaca.assignment3.network.FlowerApi
import sheridan.jaca.assignment3.network.FlowerApiService
import sheridan.jaca.assignment3.network.FlowerJson

class FlowerRepository (private val db: FlowerDatabase){
    private val flowerDao = db.flowerDao()

    suspend fun getAll(): LiveData<List<Flower>>{
        return Transformations.map(flowerDao.getAll()){
            list -> list.map { it.toFlower() }
        }
    }

    suspend fun get(id:Long):Flower{
        return flowerDao.get(id).toFlower()
    }

    suspend fun insert(flower: Flower):Long{
        return flowerDao.insert(flower.toFlowerEntity())
    }

    suspend fun insert(flowers: List<Flower>){
        val flowerEntities = flowers.mapIndexed{ _, f ->
            f.toFlowerEntity()
        }
        flowerDao.insertAll(flowerEntities)
    }

    suspend fun delete(flower: Flower){
        flowerDao.delete(flower.id)
    }

    suspend fun update(flower: Flower){
        flowerDao.update(flower.toFlowerEntity())
    }
    suspend fun getFlowerData(): List<Flower> {
        return withContext(Dispatchers.IO) {
            val catalog = FlowerApi.retrofitService.getFlowerCatalog()
            val flowers = catalog.flowers.mapIndexed { index, flowerJson ->
                flowerJson.toFlower(index)
            }

            insert(flowers)

            return@withContext flowers
        }
    }

    fun Flower.toFlowerEntity(): FlowerEntity{
        return FlowerEntity(id,label,price, imgUrl, text)
    }

    fun FlowerJson.toFlower(index: Int): Flower {
        val flowerId = index.toLong()
        return Flower(label,price,pictures.small,text, flowerId)
    }

    fun FlowerEntity.toFlower(): Flower{
        return Flower(label,price, imgUrl, text, id)
    }
}