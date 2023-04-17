package com.saulblanco.cryptodata.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.saulblanco.cryptodata.R
import com.saulblanco.cryptodata.data.model.CryptoRepository
import com.saulblanco.cryptodata.databinding.ActivityMainBinding
import com.saulblanco.cryptodata.domain.GetFirstData
import com.saulblanco.cryptodata.domain.model.CryptoData
import com.saulblanco.cryptodata.ui.viewmodel.CryptoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private lateinit var getFirstData: GetFirstData

    private lateinit var binding: ActivityMainBinding
    private val cryptoViewModel: CryptoViewModel by viewModels()

    private lateinit var adapter: CryptoAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        cryptoViewModel.onCreate()




    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                CoroutineScope(Dispatchers.IO).launch{
                   val result: List<CryptoData>? = getFirstData()

                    //TODO
                    //No lee el recyclerview, no pinta nada, repasar
                    runOnUiThread{
                        adapter.updateList(result.orEmpty())

                    }

                }
                return false
            }

            override fun onQueryTextChange(newText: String?) = false

        })

        adapter = CryptoAdapter()
        binding.rvCryptoData.setHasFixedSize(true)
        binding.rvCryptoData.layoutManager = LinearLayoutManager(this)
        binding.rvCryptoData.adapter = adapter

    }



}