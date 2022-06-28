package com.example.loginandregister

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import com.example.loginandregister.databinding.ActivityRegisterStep1Binding
import com.example.loginandregister.databinding.ActivityRegisterStep2Binding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterStep2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterStep2Binding
    private lateinit var email: String
    private lateinit var password: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterStep2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        email = intent.getStringExtra(EMAIL)!!
        password = intent.getStringExtra(PASSWORD)!!


        binding.backButtonImage.setOnClickListener {
            onBackPressed()
        }
        binding.registerStep2Button.setOnClickListener {
            submitForm()
        }
    }

    private fun submitForm() = with(binding) {
        val userName = usernameInputEdittext.text?.toString()

        if (!isValidUserName(userName)) {
            usernameInputEdittext.error = ("Incorrect UserName")
        } else {
            registerUser()
        }

    }

    private fun isValidUserName(userName: String?): Boolean {
        return userName != null && userName.isNotBlank()
    }

    private fun registerUser() {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext, "Registration Is Successful",
                        Toast.LENGTH_LONG
                    ).show()
                    signIn(email, password)
                } else {
                    Toast.makeText(
                        baseContext, task.exception?.localizedMessage ?: "Registration Failed",
                        Toast.LENGTH_LONG
                    ).show()
                }
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
        private const val EMAIL = "email"
        private const val PASSWORD = "password"

        fun starts(context: Context, email: String, password: String) {
            val intent = Intent(context, RegisterStep2Activity::class.java)
            intent.putExtra(EMAIL, email)
            intent.putExtra(PASSWORD, password)
            context.startActivity(intent)
        }
    }

}