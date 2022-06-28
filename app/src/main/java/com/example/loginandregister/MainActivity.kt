package com.example.loginandregister

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.*
import com.example.loginandregister.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        handleWindowInset()

        binding.logInButton.setOnClickListener {
            LogInActivity.starts(this)
        }
        binding.registerButton.setOnClickListener {
            RegisterStep1Activity.starts(this)
        }

    }

    private fun handleWindowInset() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            binding.root.updatePadding(bottom = insets.bottom)

            windowInsets
        }
    }
}