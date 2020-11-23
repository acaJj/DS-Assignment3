package sheridan.jaca.assignment3

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import sheridan.jaca.assignment3.domain.Flower
import sheridan.jaca.assignment3.network.FlowerJson
import sheridan.jaca.assignment3.overview.FlowerAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val fullImgUrl = "http://tetervak.dev.fast.sheridanc.on.ca/Examples/jQuery/Flowers3/images/flowers/$imgUrl"
        val imgUri = fullImgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindData(recyclerView: RecyclerView, data:List<Flower>?){
    val adapter = recyclerView.adapter as FlowerAdapter
    adapter.submitList(data)
}