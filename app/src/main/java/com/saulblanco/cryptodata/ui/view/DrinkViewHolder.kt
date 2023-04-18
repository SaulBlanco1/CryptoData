package com.saulblanco.cryptodata.ui.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.saulblanco.cryptodata.data.model.Drink
import com.saulblanco.cryptodata.databinding.ItemCryptoBinding
import com.squareup.picasso.Picasso

class DrinkViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCryptoBinding.bind(view)

    fun bind(drinkInfo: Drink, onItemSelected: (String) -> Unit) {
        binding.tvCryptoName.text = drinkInfo.name
        Picasso.get().load(drinkInfo.image).into(binding.ivDrink)
        binding.root.setOnClickListener { onItemSelected(drinkInfo.id) }
    }
}