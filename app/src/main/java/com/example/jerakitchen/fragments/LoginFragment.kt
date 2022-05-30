package com.example.jerakitchen.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jerakitchen.R
import com.example.jerakitchen.activity.HomeActivity
import com.example.jerakitchen.databinding.FragmentLoginBinding
import com.example.jerakitchen.viewModel.AuthenticationViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var mAuth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mAuth = FirebaseAuth.getInstance()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val userData : MutableLiveData<FirebaseUser> = MutableLiveData()
        val authVM = ViewModelProvider(this).get(AuthenticationViewModel::class.java)

        binding.invRegisterButton.setOnClickListener{
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }

        binding.btnEnterButton.setOnClickListener{login(authVM)}

        return binding.root
    }

    private fun login(authVM : AuthenticationViewModel){
        val email = binding.emailLoginEditText.text.toString()
        val password = binding.passwordLoginEditText.text.toString()

        if (email.trim() != "" && password.trim() != "" && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            authVM.loginAcc(email, password)
            val i = Intent(activity, HomeActivity::class.java)
            startActivity(i)
            findNavController().navigate(
                LoginFragmentDirections.actionLoginFragmentToListFragment()
            )
        }
        else {
            binding.emailLoginEditText.error = "Credencial(is) inválida(s)"
            binding.passwordLoginEditText.error = "Credencial(is) inválida(s)"
        }
    }
}


