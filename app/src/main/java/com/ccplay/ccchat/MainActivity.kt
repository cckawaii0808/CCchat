package com.ccplay.ccchat

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.ccplay.ccchat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val pref = getSharedPreferences("chat", Context.MODE_PRIVATE)
        pref.edit().putBoolean("login_state",false)
            .apply()
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        //    val appBarConfiguration = AppBarConfiguration(setOf(
        //  R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications))
        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.navigation_home -> true
            R.id.navigation_search -> true
            R.id.navigation_profile -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}