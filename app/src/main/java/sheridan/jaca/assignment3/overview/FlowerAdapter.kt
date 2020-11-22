package sheridan.jaca.assignment3.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import sheridan.jaca.assignment3.databinding.RecyclerViewItemBinding
import sheridan.jaca.assignment3.domain.Flower
import sheridan.jaca.assignment3.network.FlowerJson

class FlowerAdapter : ListAdapter<Flower,FlowerAdapter.FlowerViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
       return FlowerViewHolder(RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        val flower = getItem(position)
        return holder.bind(flower)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Flower>(){
        override fun areItemsTheSame(oldItem: Flower, newItem: Flower): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Flower, newItem: Flower): Boolean {
            return oldItem.id == newItem.id
        }

    }

    class FlowerViewHolder (private var binding: RecyclerViewItemBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun bind(flower: Flower){
            binding.flower = flower
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): FlowerViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecyclerViewItemBinding.inflate(layoutInflater, parent, false)
                return FlowerViewHolder(binding)
            }
        }
    }
}