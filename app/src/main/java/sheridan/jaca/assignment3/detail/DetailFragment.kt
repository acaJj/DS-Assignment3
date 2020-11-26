package sheridan.jaca.assignment3.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import sheridan.jaca.assignment3.R
import sheridan.jaca.assignment3.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this

        //get flower data from args
        val flowerData = DetailFragmentArgs.fromBundle(requireArguments()).flowerData
        val viewModel = DetailViewModel(flowerData)
        binding.viewModel = viewModel

        //set back button functionality
        binding.btnDetailBack.setOnClickListener {
            val action = DetailFragmentDirections.actionDetailToOverview()
            findNavController().navigate(action)
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}