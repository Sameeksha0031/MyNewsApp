package com.example.newslistapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.viewContainerForFragment) as NavHostFragment
        navController = navHostFragment.navController


        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.viewContainerForFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    @SuppressLint("RestrictedApi")
    override fun onBackPressed() {
        Log.d("#sam","navController = ${navController.currentBackStack.value.size}")
        navController.popBackStack()
//        supportFragmentManager.backStackEntryCount
//        Log.d("#sam","backStackEntryCount = ${supportFragmentManager.backStackEntryCount}")
        Log.d("#sam","navController = ${navController.currentBackStack.value.size}")
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("#sam","OnDestroy")
    }

    override fun onStop() {
        super.onStop()
        Log.d("#sam","OnStop")
    }

    override fun onPause() {
        super.onPause()
        Log.d("#sam","OnPause")
    }
}

