package sheridan.jaca.assignment3.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import sheridan.jaca.assignment3.database.FlowerDatabase
import sheridan.jaca.assignment3.databinding.FragmentFlowerBinding

class OverviewFragment : Fragment() {

    //private val viewModel: FlowerViewModel by lazy {
      //  ViewModelProvider(this).get(FlowerViewModel::class.java)
    //}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFlowerBinding.inflate(inflater)
        binding.lifecycleOwner = this

        val viewModel = FlowerViewModel(requireContext())
        binding.viewModel = viewModel

        //set click listener
        val adapter = FlowerAdapter(FlowerAdapter.OnClickListener {
            viewModel.displayFlowerDetails(it)
        })

        binding.flowerList.adapter = adapter

        viewModel.flowers.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }

        viewModel.navigateToDetails.observe(viewLifecycleOwner, {
            if (null != it){
                val action = OverviewFragmentDirections.actionOverviewToDetail(it)
                this.findNavController().navigate(action)
                viewModel.displayFlowerDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }
}