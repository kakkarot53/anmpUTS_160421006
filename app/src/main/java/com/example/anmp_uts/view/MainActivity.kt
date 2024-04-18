package com.example.anmp_uts.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.anmp_uts.R
import com.example.anmp_uts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.NavBar.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.ItemHome -> {
                    val action = UserFragmentDirections.actionUserHobby()
                    Navigation.findNavController(binding.fragmentHost).navigate(action)
                    true
                }
                R.id.itemUser -> {
                    val action = HobbyListFragmentDirections.actionHobbyUser()
                    Navigation.findNavController(binding.fragmentHost).navigate(action)
                    true
                }
                else -> false
            }
        }
    }

}