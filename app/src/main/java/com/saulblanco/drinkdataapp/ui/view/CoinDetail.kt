package com.saulblanco.drinkdataapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saulblanco.drinkdataapp.R

class CoinDetail : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "extra_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        val id:String = intent.getStringExtra(EXTRA_ID).orEmpty()
    }
}