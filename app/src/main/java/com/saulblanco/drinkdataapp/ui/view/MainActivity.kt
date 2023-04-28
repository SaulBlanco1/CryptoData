package com.saulblanco.drinkdataapp.ui.view

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.saulblanco.drinkdataapp.R
import com.saulblanco.drinkdataapp.databinding.ActivityMainBinding
import com.saulblanco.drinkdataapp.ui.view.Auth.AuthActivity
import com.saulblanco.drinkdataapp.ui.view.rvadapters.DrinkAdapter
import com.saulblanco.drinkdataapp.ui.viewmodel.DrinkViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

enum class ProviderType{
    BASIC,
    GOOGLE
}

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


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

        //Setup // Recepciono el provider ya como un String
        val bundle=intent.extras
        val email=bundle?.getString("email")
        val provider=bundle?.getString("provider")


       //Guardado de datos
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        if(!email.isNullOrBlank()){
            prefs?.putString("email",email)
            prefs?.putString("provider",provider)
            prefs?.apply()

        }


        initUI()
        initListeners()

        drinkViewModel.onCreate()

        //Inicializo el primer id de la bebida Random
        drinkViewModel.setRandomDrink()

        drinkViewModel.listDrink.observe(this, Observer { drinkList ->
            adapter =
                DrinkAdapter(drinkList, drinkViewModel) { drinkId -> navigateToDetail(drinkId) }
            binding.rvDrinkData.setHasFixedSize(true)
            binding.rvDrinkData.layoutManager = LinearLayoutManager(binding.searchView.context)
            binding.rvDrinkData.adapter = adapter

        })

        drinkViewModel.isLoading.observe(this, Observer {
            binding.loading.isVisible = it
        })

        drinkViewModel.showDialogError.observe(this, Observer {
            if(it){
                val dialog = Dialog(this)
                dialog.setContentView(R.layout.dialog_searchdialog)
                dialog.show()
                val buttonOkey: AppCompatButton = dialog.findViewById(R.id.btnOkey)
                buttonOkey.setOnClickListener {
                    dialog.dismiss()
                }
            }
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
                (parent!!.getChildAt(0) as TextView).setTextColor(Color.BLACK)
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
                (parent!!.getChildAt(0) as TextView).setTextColor(Color.BLACK)
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

                (parent!!.getChildAt(0) as TextView).setTextColor(Color.BLACK)
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
            finish()

        }

        binding.btnRandom.setOnClickListener {
            drinkViewModel.setRandomDrink()
            val id = drinkViewModel.getRandomDrinkId()
            if (!id.equals("")) {
                navigateToDetail(id)
            }
        }

        binding.btnFavorites.setOnClickListener {
            val intent = Intent(this, DrinkFavorites::class.java)
            startActivity(intent)
            finish()
        }

        //BUTTON FAB SIGN OUT
        binding.fabSignOut.setOnClickListener {
            showDialog()
        }


    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_signout)

        val buttonSignOut: AppCompatButton = dialog.findViewById(R.id.btnSignOut)
        buttonSignOut.setOnClickListener {
            // Borrado de datos
            val prefs=getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            //Desloguea de Firebase
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }
        dialog.show()


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

    override fun onBackPressed() {
        // Borrado de datos
        val prefs=getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.clear()
        prefs.apply()
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }

}
