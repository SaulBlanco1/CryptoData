package com.saulblanco.drinkdataapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.saulblanco.drinkdataapp.R
import com.saulblanco.drinkdataapp.databinding.ActivityDrinkFavoritesBinding
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

        drinkFavsviewModel.listFavDrink.observe(this, Observer { drinkList ->
            adapterFav = DrinkAdapterFav(drinkList,drinkFavsviewModel) { drinkId -> navigateToDetail(drinkId) }
            binding.rvDrinkFavs.setHasFixedSize(true)
            binding.rvDrinkFavs.layoutManager = LinearLayoutManager(binding.cvFavs.context)
            binding.rvDrinkFavs.adapter = adapterFav

        })



    }

    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DrinkDetail::class.java)
        intent.putExtra("extra_id", id)
        startActivity(intent)
    }
}