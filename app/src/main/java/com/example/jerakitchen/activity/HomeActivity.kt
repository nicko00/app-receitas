package com.example.jerakitchen.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.jerakitchen.R
import com.example.jerakitchen.databinding.ActivityHomeBinding
import com.example.jerakitchen.viewModel.AuthenticationViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var navController : NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authVM = ViewModelProvider(this).get(AuthenticationViewModel::class.java)

        supportActionBar?.hide()

        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.listFragment,
            R.id.favoriteFragment
        ))
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setupListeners(authVM)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupListeners(authVM : AuthenticationViewModel) {
        binding.btnFilterFAB.setOnClickListener {
            TODO()
        }
        binding.btnLogoutFAB.setOnClickListener {
            authVM.logOut()
            goToRegisterPage()
        }
    }

    private fun goToRegisterPage(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}