package com.example.earningapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.earningapp.databinding.ActivityLoginBinding
import com.example.earningapp.databinding.ActivityMainBinding
import com.example.earningapp.model.User
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mGoogleApiClient: GoogleApiClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        // Configure Google Sign-In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleApiClient = GoogleApiClient.Builder(this)
            .enableAutoManage(this) { connectionResult ->
                Log.d(TAG, "onConnectionFailed: ${connectionResult.errorMessage}")
                Toast.makeText(this, "Google Play Services error.", Toast.LENGTH_SHORT).show()
            }
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()



        binding.signinBtn.setOnClickListener {
            val email = binding.emailEtxt.text.toString()
            val password = binding.passEtxt.text.toString()
            signInWithEmailAndPassword(email, password)
        }
    }


    private fun signInWithEmailAndPassword(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = mAuth.currentUser
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }


    companion object {
        private const val TAG = "MainActivity"
        private const val RC_GOOGLE_SIGN_IN = 9001
    }
    override fun onStart() {
        super.onStart()
        if(Firebase.auth.currentUser!=null){
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }
}