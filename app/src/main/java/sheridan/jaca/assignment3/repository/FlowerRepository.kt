package sheridan.jaca.assignment3.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import sheridan.jaca.assignment3.database.FlowerDatabase
import sheridan.jaca.assignment3.database.FlowerEntity
import sheridan.jaca.assignment3.domain.Flower

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

    suspend fun delete(flower: Flower){
        flowerDao.delete(flower.id)
    }

    suspend fun update(flower: Flower){
        flowerDao.update(flower.toFlowerEntity())
    }

    fun Flower.toFlowerEntity(): FlowerEntity{
        return FlowerEntity(id,label,price, imgUrl, text)
    }

    fun FlowerEntity.toFlower(): Flower{
        return Flower(label,price, imgUrl, text, id)
    }
}