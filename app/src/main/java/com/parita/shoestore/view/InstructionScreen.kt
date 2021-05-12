package com.parita.shoestore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.parita.shoestore.R
import com.parita.shoestore.databinding.FragmentInstructionScreenBinding

class InstructionScreen : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentInstructionScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_instruction_screen,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.let { Navigation.findNavController(it) }
        binding.next.setOnClickListener {
            navController.navigate(R.id.action_instructionScreen_to_shoeListScreen)
        }
    }
}