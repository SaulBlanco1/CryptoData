package com.saulblanco.drinkdataapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.saulblanco.drinkdataapp.databinding.ActivityMainBinding
import com.saulblanco.drinkdataapp.domain.GetFirstData
import com.saulblanco.drinkdataapp.ui.viewmodel.DrinkViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var getFirstData: GetFirstData
    private lateinit var binding: ActivityMainBinding
    private val drinkViewModel: DrinkViewModel by viewModels()

    private lateinit var adapter: DrinkAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()

        drinkViewModel.onCreate()


        drinkViewModel.listDrink.observe(this, Observer { drinkList ->
            adapter= DrinkAdapter(drinkList) { drinkId -> navigateToDetail(drinkId) }
            binding.rvCryptoData.setHasFixedSize(true)
            binding.rvCryptoData.layoutManager = LinearLayoutManager(binding.searchView.context)
            binding.rvCryptoData.adapter=adapter

        })

        adapter = DrinkAdapter() { drinkId -> navigateToDetail(drinkId) }
        binding.rvCryptoData.setHasFixedSize(true)
        binding.rvCryptoData.layoutManager = LinearLayoutManager(binding.searchView.context)
        binding.rvCryptoData.adapter = adapter


    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                CoroutineScope(Dispatchers.IO).launch {

                   drinkViewModel.searchByName(query)

                }

                return false
            }

            override fun onQueryTextChange(newText: String?) = false

        })

        adapter = DrinkAdapter { drinkId -> navigateToDetail(drinkId) }


    }


    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DrinkDetail::class.java)
        intent.putExtra("extra_id", id)
        startActivity(intent)
    }

}
