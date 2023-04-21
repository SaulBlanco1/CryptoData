package com.saulblanco.drinkdataapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.saulblanco.drinkdataapp.R
import com.saulblanco.drinkdataapp.databinding.ActivityMainBinding
import com.saulblanco.drinkdataapp.ui.viewmodel.DrinkViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    //private lateinit var getFirstData: GetFirstData
    private lateinit var binding: ActivityMainBinding
    private val drinkViewModel: DrinkViewModel by viewModels()

    private lateinit var adapter: DrinkAdapter

    private var categorySelected: String = ""
    private var alcoholic: String = ""
    private var glassType: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        initListeners()
        drinkViewModel.onCreate()

        //Inicializo el primer id de la bebida Random
        drinkViewModel.setRandomDrink()

        drinkViewModel.listDrink.observe(this, Observer { drinkList ->
            adapter = DrinkAdapter(drinkList) { drinkId -> navigateToDetail(drinkId) }
            binding.rvDrinkData.setHasFixedSize(true)
            binding.rvDrinkData.layoutManager = LinearLayoutManager(binding.searchView.context)
            binding.rvDrinkData.adapter = adapter

        })

    }

    //Inicializa los listeners de los Spinners
    private fun initListeners() {
        //----Listeners Spinners----
        //Recoge el dato del Spinner de Category
        binding.spCategories.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                categorySelected = parent?.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        //Recoge el dato del Spinner de Alcoholic?
        binding.spAlcoholic.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                alcoholic = parent?.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        //Recoge el dato del Spinner de GlassType
        binding.spGlassType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                glassType = parent?.getItemAtPosition(position) as String
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        //----Listeners BUTTONS----
        //BUTTONS SEE ALL
        binding.btnCategoryAll.setOnClickListener {
            if (!categorySelected.equals("Category:") && !categorySelected.equals(""))
                drinkViewModel.searchDrinkListByCategory(categorySelected)
        }

        binding.btnAlcoholicAll.setOnClickListener {
            if (!alcoholic.equals("Is Alcoholic?:") && !alcoholic.equals(""))
                drinkViewModel.searchDrinkListByAlcoholic(alcoholic)
        }

        binding.btnGlassTypeAll.setOnClickListener {
            if (!glassType.equals("Glass Type:") && !glassType.equals(""))
                drinkViewModel.searchDrinkListByGlassType(glassType)

        }

        //BUTTONS MENU
        binding.btnCocktails.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }

        binding.btnRandom.setOnClickListener {
            drinkViewModel.setRandomDrink()
            val id = drinkViewModel.getRandomDrinkId()
            if (!id.equals("")) {
                navigateToDetail(id)
            }
        }

        binding.btnFavorites.setOnClickListener {

        }

    }

    //Inicializa los elementos de la UI
    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                CoroutineScope(Dispatchers.IO).launch {
                    drinkViewModel.searchByName(query, categorySelected, alcoholic, glassType)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?) = false
        })

        //Inicializa el recyclerView
//        adapter = DrinkAdapter { drinkId -> navigateToDetail(drinkId) }
//        binding.rvDrinkData.setHasFixedSize(true)
//        binding.rvDrinkData.layoutManager = LinearLayoutManager(binding.searchView.context)
//        binding.rvDrinkData.adapter = adapter

        initSpinners()


    }

    //Inicializa los spinners
    private fun initSpinners() {
        val categories = resources.getStringArray(R.array.Categories)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        binding.spCategories.adapter = adapter


        val alcoholic = resources.getStringArray(R.array.Alcoholic)
        val adapteralc = ArrayAdapter(this, android.R.layout.simple_spinner_item, alcoholic)
        binding.spAlcoholic.adapter = adapteralc

        val glassType = resources.getStringArray(R.array.GlassType)
        val adapterGlassType = ArrayAdapter(this, android.R.layout.simple_spinner_item, glassType)
        binding.spGlassType.adapter = adapterGlassType


    }

    //Cambia a la activity de Detalle con el ID correspondiente al Cocktail seleccionado
    private fun navigateToDetail(id: String) {
        val intent = Intent(this, DrinkDetail::class.java)
        intent.putExtra("extra_id", id)
        startActivity(intent)
    }

}
