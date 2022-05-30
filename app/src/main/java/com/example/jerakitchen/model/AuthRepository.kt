package com.example.jerakitchen.model

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.jerakitchen.R
import com.example.jerakitchen.fragments.LoginFragmentDirections
import com.example.jerakitchen.fragments.RegisterFragmentDirections
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.properties.Delegates


class AuthRepository (private var application: Application){
    private var mAuth : FirebaseAuth = FirebaseAuth.getInstance()
    private var uid = mAuth.currentUser?.uid.toString()
    private var userData : MutableLiveData<FirebaseUser> = MutableLiveData()
    private var firebaseDB : DatabaseReference = Firebase.database.getReference("users")

    fun registerAcc(email : String, password : String, user : String){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                task->
            if (task.isSuccessful){
                firebaseDB.child(uid).child("userName").setValue(user)
                Toast.makeText(application, "Conta criada com sucesso", Toast.LENGTH_SHORT).show()
            }
                else{
                Toast.makeText(application, "Algo deu errado, tente novamente", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun loginAcc(email : String, password : String){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
                task->
            if (task.isSuccessful){
                Toast.makeText(application, "Bem vindo", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(application, "Algo deu errado, tente novamente", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun logOut(){
        mAuth.signOut()
    }

    fun getUserData() : MutableLiveData<FirebaseUser>{
        return userData
    }
}