package sheridan.jaca.assignment3.overview

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import sheridan.jaca.assignment3.database.FlowerDatabase
import sheridan.jaca.assignment3.domain.Flower
import sheridan.jaca.assignment3.network.FlowerJson
import sheridan.jaca.assignment3.network.FlowerApi
import sheridan.jaca.assignment3.repository.FlowerRepository
import java.lang.Exception

class FlowerViewModel(context: Context): ViewModel() {

    private var _flowers = MutableLiveData<List<Flower>>()
    val flowers : LiveData<List<Flower>>
        get() = _flowers

    private val repository : FlowerRepository = FlowerRepository(FlowerDatabase.getInstance(context))
   //private var flowers: LiveData<List<Flower>>? //old method

    private val _navigateToDetails = MutableLiveData<Flower>()
    val navigateToDetails: LiveData<Flower>
        get() = _navigateToDetails

    init {
        getFlowerData()
    }

    // use repository method to retrieve flower data instead of doing it directly
    private fun getFlowerData(){
        viewModelScope.launch {
            try {
                _flowers.value = repository.getFlowerData()
                Log.i("SUCCESS","FLOWER RETRIEVAL COMPLETED")
            }catch (e: Exception){
                Log.i("ERROR",e.message.toString())

                //ERROR: Cannot invoke setValue on a background thread
            }
        }
    }

    //method of retrieving JSON directly through API service
/*    fun getFlowerData() : LiveData<List<Flower>>{
        return flowers ?: liveData {
            val catalog = FlowerApi.retrofitService.getFlowerCatalog()
            val flowers = catalog.flowers.mapIndexed { index, flowerJson ->
                flowerJson.toFlower(index)
            }
            emit(flowers)
        }.also {
            flowers = it
        }

        return flowers.also { flowers = it }
    }*/

    fun displayFlowerDetails(flowerData:Flower){
        _navigateToDetails.value = flowerData
    }

    fun displayFlowerDetailsComplete(){
        _navigateToDetails.value = null
    }

    private fun FlowerJson.toFlower(index: Int): Flower {
        val flowerId = index.toLong()
        return Flower(label,price,pictures.small,text, flowerId)
    }
}