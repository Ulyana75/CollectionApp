package com.ulyanaab.collectionsapp2

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.ulyanaab.collectionsapp2.utilities.APP_ACTIVITY
import com.ulyanaab.collectionsapp2.utilities.AppDrawer

class MainActivity : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var drawer: AppDrawer
    lateinit var navController: NavController

    var currentGalleryPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        APP_ACTIVITY = this
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        initViews()
    }

    private fun initViews() {
        toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        drawer = AppDrawer()
        navController = findNavController(R.id.nav_host_fragment)

        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.primary_dark)
    }

}