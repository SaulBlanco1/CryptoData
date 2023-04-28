package com.saulblanco.drinkdataapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.saulblanco.drinkdataapp.R
import com.saulblanco.drinkdataapp.databinding.ActivityDrinkDetailBinding
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import com.saulblanco.drinkdataapp.ui.viewmodel.DrinkDetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrinkDetail : AppCompatActivity() {


    private val drinkDetailViewModel: DrinkDetailViewModel by viewModels()
    private lateinit var binding: ActivityDrinkDetailBinding
    private var ids : List<String> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrinkDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val idItem: String = intent.getStringExtra("extra_id").orEmpty()

        drinkDetailViewModel.onCreate(idItem)


        drinkDetailViewModel.idsFromFavoriteList.observe(this, Observer {
            ids=it
        })


        drinkDetailViewModel.drinkItem.observe(this, Observer { itemDrink ->
            binding.chipFav.isChecked = idItem in ids
            binding.chipFav.setOnClickListener {
                val drinktoFav = DrinkGeneralDomain(
                    itemDrink.id,
                    itemDrink.name,
                    itemDrink.imageDetail,
                    itemDrink.alcoholic,
                    itemDrink.glassType,
                    itemDrink.category
                )
                if (!binding.chipFav.isChecked) {
                    drinkDetailViewModel.deleteFromFav(drinktoFav)
                    binding.chipFav.isChecked = false
                } else {
                    drinkDetailViewModel.insertToFav(drinktoFav)
                }
            }
            Picasso.get().load(itemDrink.imageDetail).into(binding.ivImageDetail)
            binding.tvCateg.text = itemDrink.category
            binding.tvAlcoh.text = itemDrink.alcoholic
            binding.tvingr1.text = itemDrink.ing1
            binding.tvingr2.text = itemDrink.ing2
            binding.tvingr3.text = itemDrink.ing3
            binding.tvInstruc.text = itemDrink.instr
            binding.tvGlassType.text = itemDrink.glassType
            binding.tvDrinkNameDetail.text = itemDrink.name
        })

        drinkDetailViewModel.isLoading.observe(this, Observer {
            binding.loadingDetail.isVisible = it
        })

    }
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


}