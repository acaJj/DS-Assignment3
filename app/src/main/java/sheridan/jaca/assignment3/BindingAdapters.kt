package sheridan.jaca.assignment3

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import sheridan.jaca.assignment3.network.Flower
import sheridan.jaca.assignment3.overview.FlowerAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?){
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
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