package sheridan.jaca.assignment3.overview

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import sheridan.jaca.assignment3.domain.Flower
import sheridan.jaca.assignment3.network.FlowerJson
import sheridan.jaca.assignment3.network.FlowerApi
import java.lang.Exception

class FlowerViewModel: ViewModel() {
    //val flowers = MutableLiveData<List<FlowerJson>>()
    private var flowers : LiveData<List<Flower>>? = null
    init {
        getFlowerData()
    }

    fun getFlowerData() : LiveData<List<Flower>>{
       /* viewModelScope.launch {
            try {
                val catalog = FlowerApi.retrofitService.getFlowerCatalog()
                val flowerData = catalog.flowers
                flowers.value = flowerData.mapIndexed { index, flowerJson -> flowerJson.toFlower(index) }
                Log.i("SUCCESS","JSON RETRIEVAL COMPLETED")
            }catch (e: Exception){
                Log.i("ERROR","JSON RETRIEVAL FAILED")
            }
        }*/

        return flowers ?: liveData {
            val catalog = FlowerApi.retrofitService.getFlowerCatalog()
            val flowers = catalog.flowers.mapIndexed { index, flowerJson ->
                flowerJson.toFlower(index)
            }
            emit(flowers)
        }.also {
            flowers = it
        }

        //return flowers.also { flowers = it }*/
    }

    private fun FlowerJson.toFlower(index: Int): Flower {
        val flowerId = index.toLong()
        return Flower(label,price,pictures.small,text, flowerId)
    }
}