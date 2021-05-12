package com.parita.shoestore.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.parita.shoestore.R
import com.parita.shoestore.adapter.ShoesListAdapter
import com.parita.shoestore.databinding.FragmentShoeListScreenBinding
import com.parita.shoestore.model.Shoes
import com.parita.shoestore.viewmodel.ShoeViewModel

class ShoeListScreen : Fragment() {
    private lateinit var binding: FragmentShoeListScreenBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: ShoeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list_screen, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(ShoeViewModel::class.java)
        val toolbar: Toolbar = view.findViewById(R.id.toolbar)
        (activity as AppCompatActivity)?.setSupportActionBar(toolbar)
        activity?.actionBar?.show()
        activity?.actionBar?.setDisplayHomeAsUpEnabled(true)
        activity?.actionBar?.setDisplayShowTitleEnabled(false)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        navController = view.let { Navigation.findNavController(it) }
        binding.addUser.setOnClickListener {
            navController.navigate(R.id.action_shoeListScreen_to_shoeDetailsScreen)
        }
        fetchShoeDetails()
    }

    private fun fetchShoeDetails() {
        viewModel.getShoeDetails(requireContext())
        viewModel.getAllShoes().observe(viewLifecycleOwner, Observer { shoeList ->
            for (i in shoeList) {
                Log.d("TAG", "data: " + i + "\t" + i.shoeId + "\t" + i.shoeName)
            }
            var adapter = ShoesListAdapter(shoeList as ArrayList<Shoes>)
            binding.recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        })
    }
}