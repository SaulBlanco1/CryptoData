package com.saulblanco.cryptodata.ui.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.saulblanco.cryptodata.data.model.CryptoListModel
import com.saulblanco.cryptodata.databinding.ItemCryptoBinding
import com.saulblanco.cryptodata.domain.model.CryptoData

class CryptoViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding=ItemCryptoBinding.bind(view)

    fun bind(cryptoListModel: CryptoData){
        binding.tvCryptoName.text=cryptoListModel.name

    }
}