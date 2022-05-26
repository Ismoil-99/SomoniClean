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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        val inflater = navController.navInflater
        val graph = inflater.inflate(R.navigation.base_nav_graph)

        navController.graph = graph
        NavigationUI.setupWithNavController(findViewById(R.id.toolbar),navController,null)
        destinationChange()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_layout,menu)
        return super.onCreateOptionsMenu(menu)
    }
    private fun toChangeOption(){
        navController.navigate(R.id.action_mainFragment_to_nav_change)
        //visible option menu
        binding.toolbar.menu?.findItem(R.id.action_favorite)?.isVisible = false
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_favorite -> {toChangeOption()}
        }
        return super.onOptionsItemSelected(item)
    }
    //visible arrow back
    private fun destinationChange(){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.mainFragment -> { binding.toolbar.navigationIcon = null }
            }
        }
    }
}