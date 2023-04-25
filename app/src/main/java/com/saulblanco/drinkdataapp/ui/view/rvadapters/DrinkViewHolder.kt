package com.saulblanco.drinkdataapp.ui.view.rvadapters

import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.saulblanco.drinkdataapp.databinding.ItemDrinkBinding
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import com.saulblanco.drinkdataapp.domain.usecases.InsertFavDrinkIntoFavs
import com.saulblanco.drinkdataapp.ui.viewmodel.DrinkViewModel

import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DrinkViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemDrinkBinding.bind(view)


    fun bind(
        drinkViewModel: DrinkViewModel,
        drinkInfo: DrinkGeneralDomain,
        onItemSelected: (String) -> Unit
    ) {
        val idsFromFav = drinkViewModel.getIdsFromFav()
        binding.chipFav.isChecked = drinkInfo.id in idsFromFav

        binding.tvDrinkName.text = drinkInfo.name
        Picasso.get().load(drinkInfo.image).into(binding.ivDrink)
        binding.ivDrink.setOnClickListener {
            onItemSelected(drinkInfo.id)
        }

        binding.chipFav.setOnClickListener {
            val drinktoFav = DrinkGeneralDomain(
                drinkInfo.id,
                drinkInfo.name,
                drinkInfo.image,
                drinkInfo.alcoholic,
                drinkInfo.tipoVaso,
                drinkInfo.category
            )
            if (!binding.chipFav.isChecked) {
                drinkViewModel.deleteFromFav(drinktoFav)
            } else {
                drinkViewModel.insertToFav(drinktoFav)
            }

        }


    }

}

