package com.parita.shoestore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.parita.shoestore.R
import com.parita.shoestore.databinding.FragmentShoeDetailsScreenBinding
import com.parita.shoestore.model.Shoes
import com.parita.shoestore.viewmodel.ShoeViewModel

class ShoeDetailsScreen : Fragment() {
    private lateinit var binding: FragmentShoeDetailsScreenBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: ShoeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_details_screen,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(ShoeViewModel::class.java)
        navController = view.let { Navigation.findNavController(it) }
        binding.saveBtn.setOnClickListener {
            saveShoeDetails()
        }
        binding.cancelBtn.setOnClickListener {
            navController.navigate(R.id.action_shoeDetailsScreen_to_shoeListScreen)
        }
    }


    private fun saveShoeDetails() {
        var shoeModel = Shoes(
            binding.shoeId.text.toString().trim(),
            binding.shoeName.text.toString().trim(),
            binding.shoeCompany.text.toString().trim(),
            binding.shoeSize.text.toString().trim(),
            binding.shoeDescription.text.toString().trim()
        )
        binding.shoe = shoeModel
        viewModel.addShoeDetails(shoeModel, requireContext()).observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireContext(), "Shoe details added", Toast.LENGTH_SHORT).show()
            }
        })
    }
}