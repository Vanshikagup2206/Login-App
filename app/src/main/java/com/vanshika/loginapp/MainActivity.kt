package com.vanshika.loginapp

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.vanshika.loginapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding ?= null
    private var navController : NavController?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.otpFragment) as NavHostFragment
        navHostFragment.findNavController().run {
            binding?.toolbar?.setupWithNavController(this,AppBarConfiguration(graph))
        }
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

private fun Toolbar?.setupWithNavController(
    navController: NavController,
    appBarConfiguration: AppBarConfiguration
) {
    TODO("Not yet implemented")
}
