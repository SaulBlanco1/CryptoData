package com.saulblanco.drinkdataapp.ui.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saulblanco.drinkdataapp.R
import com.saulblanco.drinkdataapp.domain.model.DrinkGeneralDomain
import com.saulblanco.drinkdataapp.domain.usecases.InsertFavDrinkIntoFavs
import com.saulblanco.drinkdataapp.ui.viewmodel.DrinkFavoritesViewModel
import com.saulblanco.drinkdataapp.ui.viewmodel.DrinkViewModel


class DrinkAdapterFav(
    var drinkList: List<DrinkGeneralDomain> = emptyList(),
    private val drinkFavViewModel:DrinkFavoritesViewModel,
    private val onItemSelected: (String) -> Unit


) :
    RecyclerView.Adapter<DrinkViewHolderFav>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolderFav {

        return DrinkViewHolderFav(
            LayoutInflater.from(parent.context).inflate(R.layout.item_drink, parent, false)
        )
    }



    override fun onBindViewHolder(viewholder: DrinkViewHolderFav, position: Int) {

        viewholder.bind(drinkFavViewModel,drinkList[position], onItemSelected)
    }


    override fun getItemCount() = drinkList.size


}