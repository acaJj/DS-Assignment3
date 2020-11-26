package sheridan.jaca.assignment3.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sheridan.jaca.assignment3.domain.Flower

class DetailViewModel(flowerData: Flower): ViewModel() {
    private var _flower = MutableLiveData<Flower>()
    val flower : LiveData<Flower>
        get() = _flower

    init {
        _flower.value = flowerData
    }
}