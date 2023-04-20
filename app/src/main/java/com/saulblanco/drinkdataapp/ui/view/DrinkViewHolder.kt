package com.saulblanco.drinkdataapp.ui.view

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.saulblanco.drinkdataapp.data.model.Drink
import com.saulblanco.drinkdataapp.databinding.ItemDrinkBinding

import com.squareup.picasso.Picasso

class DrinkViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemDrinkBinding.bind(view)

    fun bind(drinkInfo: Drink, onItemSelected: (String) -> Unit) {
        binding.tvDrinkName.text = drinkInfo.name
        Picasso.get().load(drinkInfo.image).into(binding.ivDrink)
        Log.i("SAULID",drinkInfo.id)
        binding.ivDrink.setOnClickListener { onItemSelected(drinkInfo.id) }
    }
}