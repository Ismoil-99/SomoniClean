package com.example.somoni.presenetation.mainactivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.somoni.R
import com.example.somoni.databinding.ActivityMainBinding
import com.example.somoni.extensions.SomoniApp
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        setupNavigation()
    }
    private fun setupNavigation() {
        val sharedPreferences = SomoniApp.sharedPreferences
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        val inflater = navController.navInflater
        val graph = inflater.inflate(R.navigation.base_nav_graph)

        navController.graph = graph
        NavigationUI.setupWithNavController(findViewById(R.id.toolbar),navController,null)
        destinationChange()
    }
    private fun destinationChange(){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.mainFragment -> { binding.toolbar.navigationIcon = null }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_layout,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_favorite -> {
                navController.navigate(R.id.action_mainFragment_to_nav_change) }
        }
        return super.onOptionsItemSelected(item)
    }

}