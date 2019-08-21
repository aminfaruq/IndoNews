package com.zeeb.newsindo.screens.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.zeeb.newsindo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navController = findNavController(R.id.nav_host_fragment)
        nav_view.setupWithNavController(navController)

        setupActionBarWithNavController(navController, AppBarConfiguration(nav_view.menu))
//        NavigationUI.setupWithNavController(nav_view, navController)

    }

//    override fun onSupportNavigateUp() = findNavController(R.id.nav_host_fragment).navigateUp()

}
