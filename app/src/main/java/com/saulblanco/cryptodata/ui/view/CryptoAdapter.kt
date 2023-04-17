package com.saulblanco.cryptodata.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.saulblanco.cryptodata.R
import com.saulblanco.cryptodata.domain.model.CryptoData


class CryptoAdapter (var cryptoList: List<CryptoData> = emptyList()) :
    RecyclerView.Adapter<CryptoViewHolder>() {

    fun updateList(cryptoList: List<CryptoData>){
        this.cryptoList=cryptoList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CryptoViewHolder(layoutInflater.inflate(R.layout.item_crypto, parent, false))
    }


    override fun onBindViewHolder(viewholder: CryptoViewHolder, position: Int) {
        viewholder.bind(cryptoList[position])
    }


    override fun getItemCount() = cryptoList.size

}