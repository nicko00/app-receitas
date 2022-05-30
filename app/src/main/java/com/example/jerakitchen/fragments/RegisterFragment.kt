package com.example.jerakitchen.fragments

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jerakitchen.R
import com.example.jerakitchen.databinding.FragmentRegisterBinding
import com.example.jerakitchen.viewModel.AuthenticationViewModel

class RegisterFragment : Fragment() {
    private lateinit var binding : FragmentRegisterBinding
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val authVM = ViewModelProvider(this).get(AuthenticationViewModel::class.java)

        binding.invLoginButton.setOnClickListener{
            findNavController().navigate(
                RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            )
        }

        binding.btnRegisterButton.setOnClickListener{
            val email = binding.emailRegisterEdiText.text.toString()
            val password = binding.passwordRegisterEditText.text.toString()
            val user = binding.userRegisterEditText.text.toString()

            if (email.trim() != "" && password.trim() != "" && user.trim() != "" && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                authVM.registerAcc(email, password, user)
                findNavController().navigate(
                    RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                )
            }
            else {
                binding.emailRegisterEdiText.error = "Insira os dados corretamente"
                binding.passwordRegisterEditText.error = "Insira os dados corretamente"
            }
        }
        return binding.root
    }
}


