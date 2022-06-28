package com.example.loginandregister

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.loginandregister.databinding.ActivityLogInBinding
import com.example.loginandregister.databinding.ActivityRegisterStep1Binding
import android.text.TextUtils
import android.util.Patterns


class RegisterStep1Activity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterStep1Binding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityRegisterStep1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButtonImage.setOnClickListener {
            onBackPressed()
        }
        binding.registerStep1Button.setOnClickListener {
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

        if (!(password != null && password.isNotBlank() && password.length > 6)) {
            passInputEdittext.error = ("Incorrect Password")
            isFormValid = false
        }
        if (isFormValid) {
            startRegisterStep2(email!!, password!!)
        }

    }


    private fun isValidEmail(email: String?): Boolean {
        return if (email.isNullOrBlank()) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }
    }

    private fun startRegisterStep2(email: String, password: String) {
        RegisterStep2Activity.starts(this, email, password)
    }

    companion object {
        fun starts(context: Context) {
            val intent = Intent(context, RegisterStep1Activity::class.java)
            context.startActivity(intent)
        }
    }

}
