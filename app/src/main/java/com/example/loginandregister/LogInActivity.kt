package com.example.loginandregister

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.loginandregister.databinding.ActivityLogInBinding
import com.example.loginandregister.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogInActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButtonImage.setOnClickListener {
            onBackPressed()
        }
        binding.loginButton.setOnClickListener {
            submitForm()
        }


    }

    private fun submitForm() = with(binding) {
        val email = emailInputEdittext.text?.toString()
        val password = passInputEdittext.text?.toString()
        var isFormValid = true

        if (!isValidEmail(email)) {
            emailInputEdittext.error = ("Incorrect Email")
            isFormValid = false
        }

        if (!(password != null && password.isNotBlank())) {
            passInputEdittext.error = ("Input Password")
            isFormValid = false
        }
        if (isFormValid) {
            signIn(email!!, password!!)
        }

    }

    private fun isValidEmail(email: String?): Boolean {
        return if (email.isNullOrBlank()) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }

    private fun signIn(email: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext, "Log In Successful",
                        Toast.LENGTH_LONG
                    ).show()
                    HomeActivity.starts(this)
                } else {
                    Toast.makeText(
                        baseContext, task.exception?.localizedMessage ?: "log In Failed",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

    }


    companion object {
        fun starts(context: Context) {
            val intent = Intent(context, LogInActivity::class.java)
            context.startActivity(intent)
        }
    }
}