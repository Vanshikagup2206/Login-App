package com.vanshika.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.vanshika.loginapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding : ActivityMainBinding ?= null
    var navController : NavController?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        navController = findNavController(R.id.host)
        navController?.addOnDestinationChangedListener { navController, destination, arguments ->
            when (destination.id) {
                R.id.loginFragment -> supportActionBar?.title = resources.getString(R.string.login_page)
                R.id.otpFragment -> supportActionBar?.title = resources.getString(R.string.otp_page)
                R.id.passwordFragment -> supportActionBar?.title = resources.getString(R.string.password_page)
            }
        }
    }
}