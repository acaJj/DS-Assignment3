package sheridan.jaca.assignment3.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import sheridan.jaca.assignment3.databinding.FragmentFlowerBinding

class OverviewFragment : Fragment() {

    private val viewModel: FlowerViewModel by lazy {
        ViewModelProvider(this).get(FlowerViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFlowerBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel
        val adapter = FlowerAdapter()
        binding.flowerList.adapter = adapter

        viewModel.getFlowerData().observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        setHasOptionsMenu(true)
        return binding.root
    }
}