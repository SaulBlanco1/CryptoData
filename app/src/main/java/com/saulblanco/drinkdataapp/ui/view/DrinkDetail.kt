package com.saulblanco.drinkdataapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.saulblanco.drinkdataapp.R
import com.saulblanco.drinkdataapp.databinding.ActivityDrinkDetailBinding
import com.saulblanco.drinkdataapp.ui.viewmodel.DrinkDetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrinkDetail : AppCompatActivity() {



    private val drinkDetailViewModel: DrinkDetailViewModel by viewModels()
    private lateinit var binding: ActivityDrinkDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDrinkDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val idItem:String = intent.getStringExtra("extra_id").orEmpty()
        drinkDetailViewModel.onCreate(idItem)


        drinkDetailViewModel.drinkItem.observe(this,{ itemDrink->
            Picasso.get().load(itemDrink.imageDetail).into(binding.ivImageDetail)
            binding.tvCateg.text=itemDrink.category
            binding.tvAlcoh.text=itemDrink.alcoholic
            binding.tvingr1.text=itemDrink.ing1
            binding.tvingr2.text=itemDrink.ing2
            binding.tvingr3.text=itemDrink.ing3
            binding.tvInstruc.text=itemDrink.instr
            binding.tvGlassType.text=itemDrink.glassType
        })


    }




}