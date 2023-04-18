package com.saulblanco.drinkdataapp.ui.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saulblanco.drinkdataapp.R
import com.saulblanco.drinkdataapp.data.model.Drink


class DrinkAdapter(
    var drinkList: List<Drink> = emptyList(),
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<DrinkViewHolder>() {

    fun updateList(drinkList: List<Drink>) {
        this.drinkList = drinkList
        Log.i("Saul",drinkList.toString())
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {

        return DrinkViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_drink, parent, false)
        )
    }


    override fun onBindViewHolder(viewholder: DrinkViewHolder, position: Int) {
        viewholder.bind(drinkList[position], onItemSelected)
    }


    override fun getItemCount() = drinkList.size

}