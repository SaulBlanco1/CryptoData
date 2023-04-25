package com.saulblanco.drinkdataapp.ui.view.Auth

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.saulblanco.drinkdataapp.databinding.ActivityAuthBinding
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.saulblanco.drinkdataapp.R
import com.saulblanco.drinkdataapp.ui.view.MainActivity
import com.saulblanco.drinkdataapp.ui.view.ProviderType

private lateinit var binding: ActivityAuthBinding
private val GOOGLE_SIGN_IN = 100

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Analytics Event
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message", "Integración de Firebase completa")
        analytics.logEvent("InitScreen", bundle)

        setup()
        session()
    }

    override fun onStart() {
        super.onStart()
        binding.authLayout.visibility = View.VISIBLE
    }

    private fun session() {

        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE)
        val email = prefs.getString("email", null)
        val provider = prefs.getString("provider", null)


        if (email != null && provider != null) {
            binding.authLayout.visibility = View.INVISIBLE
            showApp(email, provider)
        }

    }

    private fun setup() {
        title = "Autenticación"
        binding.btnRegister.setOnClickListener {
            if (binding.editTextTextEmailAddress.text.isNotEmpty() && binding.editTextTextPassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    binding.editTextTextEmailAddress.text.toString(),
                    binding.editTextTextPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showApp(
                            binding.editTextTextEmailAddress.text.toString(),
                            ProviderType.BASIC.name
                        )
                    } else {
                        showAlert()
                    }
                }
            }
        }

        binding.btnAccess.setOnClickListener {
            if (binding.editTextTextEmailAddress.text.isNotEmpty() && binding.editTextTextPassword.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    binding.editTextTextEmailAddress.text.toString(),
                    binding.editTextTextPassword.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showApp(
                            binding.editTextTextEmailAddress.text.toString(),
                            ProviderType.BASIC.name
                        )
                    } else {
                        showAlert()
                    }
                }
            }
        }

        binding.btnGoogle.setOnClickListener {

            //Configuración
            val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

            val googleClient = GoogleSignIn.getClient(this, googleConf)
            googleClient.signOut()
            startActivityForResult(googleClient.signInIntent, GOOGLE_SIGN_IN)


        }

    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("ERROR")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


    //Transformo el provider a String para enviarlo
    private fun showApp(email: String, provider: String) {
        Log.i("PROVIDERINFOSHOWAPP", provider)
        val appIntent = Intent(this, MainActivity::class.java)
        appIntent.putExtra("email", email)
        appIntent.putExtra("provider", provider)
        startActivity(appIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)

                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                showApp(account.email ?: "", ProviderType.GOOGLE.name)
                            } else {
                                showAlert()
                            }
                        }

                }
            } catch (e: ApiException) {
                showAlert()
            }

        }
    }

}