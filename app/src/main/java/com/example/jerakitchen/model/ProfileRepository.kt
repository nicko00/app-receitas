package com.example.jerakitchen.model

import android.app.Application
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfileRepository (private var application: Application) {
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var firebaseDB: DatabaseReference = Firebase.database.reference
    private val uid = mAuth.currentUser?.uid.toString()

    fun getUserName(): Task<DataSnapshot> {
        return firebaseDB.child("users").child(uid).child("userName").get()
    }
}