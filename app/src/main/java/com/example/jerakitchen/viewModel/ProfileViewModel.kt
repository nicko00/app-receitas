package com.example.jerakitchen.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.jerakitchen.model.ProfileRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot

class ProfileViewModel(application: Application) : AndroidViewModel(application){
    private val profileRepository = ProfileRepository(application)

    fun getUserName(): Task<DataSnapshot> {
        return profileRepository.getUserName()
    }
}