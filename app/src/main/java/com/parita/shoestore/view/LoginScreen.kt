package com.parita.shoestore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.parita.shoestore.R
import com.parita.shoestore.databinding.FragmentLoginScreenBinding
import com.parita.shoestore.model.User
import com.parita.shoestore.viewmodel.LoginViewModel

class LoginScreen : Fragment() {
    private lateinit var binding: FragmentLoginScreenBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_login_screen, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(LoginViewModel::class.java)
        navController = view.let { Navigation.findNavController(it) }
        binding.welcomeScreen.setOnClickListener {
            if (binding.email.text.toString().length == 0 && binding.password.text.toString().length ==0) {
                navController.navigate(R.id.action_loginScreen_to_welcomeScreen)
            } else {
                checkExistingUser()
            }
        }
        binding.loginBtn.setOnClickListener {
            saveUserInfo()
        }
    }

    private fun checkExistingUser() {
        var userModel = User()
        userModel.email = binding.email.text.toString()
        userModel.password = binding.password.text.toString()
        binding.user = userModel
        viewModel.fetchUserDetails(requireContext(), userModel)
            .observe(viewLifecycleOwner, Observer {
                if (it)
                    navController.navigate(R.id.action_loginScreen_to_welcomeScreen)
            })
    }

    private fun saveUserInfo() {
        var userModel = User()
        userModel.email = binding.email.text.toString()
        userModel.password = binding.password.text.toString()
        binding.user = userModel
        viewModel.fetchUserDetails(requireContext(), userModel)
            .observe(viewLifecycleOwner, Observer {
                if (it) {
                    navController.navigate(R.id.action_loginScreen_to_welcomeScreen)
                }
            })
    }
}