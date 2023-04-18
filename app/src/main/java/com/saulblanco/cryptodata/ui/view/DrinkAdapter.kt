package com.saulblanco.cryptodata.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saulblanco.cryptodata.R
import com.saulblanco.cryptodata.data.model.Drink


class DrinkAdapter(
    var cryptoList: List<Drink> = emptyList(),
    private val onItemSelected: (String) ->Unit
) :
    RecyclerView.Adapter<DrinkViewHolder>() {

    fun updateList(cryptoList: List<Drink>) {
        this.cryptoList = cryptoList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DrinkViewHolder(layoutInflater.inflate(R.layout.item_crypto, parent, false))
    }


    override fun onBindViewHolder(viewholder: DrinkViewHolder, position: Int) {
        viewholder.bind(cryptoList[position],onItemSelected)
    }


    override fun getItemCount() = cryptoList.size

}