package com.minchinovich.lesson_07

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.minchinovich.lesson_07.databinding.ActivityMainBinding

private const val ABOUT_APP = "https://developer.android.com"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.registrationButton.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        binding.aboutAppButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(ABOUT_APP)))
        }
    }
}