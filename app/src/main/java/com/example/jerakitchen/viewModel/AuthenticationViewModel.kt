package com.example.jerakitchen.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.jerakitchen.model.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {
    private val authRepository = AuthRepository(application)
    val getUser : MutableLiveData<FirebaseUser> = authRepository.getUserData()

    fun registerAcc(email : String, password : String, user : String) {
        authRepository.registerAcc(email, password, user)
    }
    fun loginAcc(email : String, password : String) {
        authRepository.loginAcc(email, password)
    }
    fun logOut() {
        authRepository.logOut()
    }

    fun isLogged() : Boolean{
        val user : FirebaseUser? = FirebaseAuth.getInstance().currentUser
        var logged = false

        if (user != null){
            logged=true
        }
        return logged
    }

    fun getUserData(){
        authRepository.getUserData()
    }
}