package sheridan.jaca.assignment3.overview

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import sheridan.jaca.assignment3.network.Flower
import sheridan.jaca.assignment3.network.FlowerApi
import java.lang.Exception

class FlowerViewModel: ViewModel() {
    private val _flowers = MutableLiveData<List<Flower>>()
    val flowers: MutableLiveData<List<Flower>>
        get() = _flowers

    init {
        getFlowerData()
    }

    private fun getFlowerData(){
        viewModelScope.launch {
            try {
                _flowers.value = FlowerApi.retrofitService.getFlowers()
                Log.i("WHAT","SUCCESS")
            }catch (e:Exception){
                //on failure, set live data to empty array list
                _flowers.value = ArrayList()
                Log.i("WHAT","ERROR")
            }
        }
    }
}