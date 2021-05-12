package com.parita.shoestore

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.parita.shoestore.SharedPreference.clearValues
import com.parita.shoestore.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var defaultPrefs: SharedPreferences

    companion object {
        @JvmStatic
        private lateinit var binding: ActivityMainBinding

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = this.findNavController(R.id.nav_host_fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.help -> {
                navController.navigate(R.id.action_shoeListScreen_to_instructionScreen)
                true
            }
            R.id.log_out -> {
                defaultPrefs = SharedPreference.defaultPreference(this)
                defaultPrefs.clearValues
                navController.navigate(R.id.action_shoeListScreen_to_loginScreen)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}