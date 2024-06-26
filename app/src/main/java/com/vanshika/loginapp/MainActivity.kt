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
import androidx.navigation.ui.setupActionBarWithNavController
import com.vanshika.loginapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var navController: NavController? = null
    var appBarConfiguration: AppBarConfiguration? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        navController = findNavController(R.id.host)
        appBarConfiguration = navController?.graph?.let {
            AppBarConfiguration(it)
        }
        setupActionBarWithNavController(navController!!, appBarConfiguration!!)
        navController?.addOnDestinationChangedListener { navController, destination, arguments ->
            when (destination.id) {
                R.id.loginFragment -> supportActionBar?.title =
                    resources.getString(R.string.login_page)

                R.id.otpFragment -> supportActionBar?.title = resources.getString(R.string.otp_page)
                R.id.passwordFragment -> supportActionBar?.title =
                    resources.getString(R.string.password_page)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController!!.popBackStack()
    }
}


