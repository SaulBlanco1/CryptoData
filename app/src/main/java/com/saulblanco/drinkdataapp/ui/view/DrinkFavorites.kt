package com.saulblanco.drinkdataapp.ui.view

import android.app.Dialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.saulblanco.drinkdataapp.R
import com.saulblanco.drinkdataapp.databinding.ActivityDrinkFavoritesBinding
import com.saulblanco.drinkdataapp.ui.view.Auth.AuthActivity
import com.saulblanco.drinkdataapp.ui.view.rvadapters.DrinkAdapterFav
import com.saulblanco.drinkdataapp.ui.viewmodel.DrinkFavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DrinkFavorites : AppCompatActivity() {

    private val drinkFavsviewModel: DrinkFavoritesViewModel by viewModels()
    private lateinit var binding: ActivityDrinkFavoritesBinding

    private lateinit var adapterFav: DrinkAdapterFav


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrinkFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        drinkFavsviewModel.onCreate()

        initListeners()

        drinkFavsviewModel.listFavDrink.observe(this, Observer { drinkList ->
            adapterFav = DrinkAdapterFav(drinkList,drinkFavsviewModel) { drinkId -> navigateToDetail(drinkId) }
            binding.rvDrinkFavs.setHasFixedSize(true)
            binding.rvDrinkFavs.layoutManager = LinearLayoutManager(binding.cvFavs.context)
            binding.rvDrinkFavs.adapter = adapterFav

        })



    }

    private fun initListeners() {
        binding.btnDeleteAllFavs.setOnClickListener {
            drinkFavsviewModel.deleteAllFavs()
        }

        //BUTTONS MENU
        binding.btnCocktails.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        binding.btnRandom.setOnClickListener {
            drinkFavsviewModel.setRandomDrink()
            val id = drinkFavsviewModel.getRandomDrinkId()
            if (!id.equals("")) {
                navigateToDetail(id)
            }
        }

        binding.btnFavorites.setOnClickListener {
            val intent = Intent(this, DrinkFavorites::class.java)
            startActivity(intent)
        }

        //BUTTON FAB SIGN OUT
        binding.fabSignOut.setOnClickListener {
            showDialog()
        }

    }

    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DrinkDetail::class.java)
        intent.putExtra("extra_id", id)
        startActivity(intent)
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_signout)

        val buttonSignOut: AppCompatButton = dialog.findViewById(R.id.btnSignOut)
        buttonSignOut.setOnClickListener {
            // Borrado de datos
            val prefs=getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }
        dialog.show()


    }
}