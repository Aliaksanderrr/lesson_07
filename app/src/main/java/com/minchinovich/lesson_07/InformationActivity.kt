package com.minchinovich.lesson_07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.minchinovich.lesson_07.databinding.ActivityInformationBinding
import java.lang.StringBuilder


class InformationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        val login = intent.getStringExtra(KEY_LOGIN) ?: DEFAULT_STRING
        val password = intent.getStringExtra(KEY_PASSWORD) ?: DEFAULT_STRING
        val firstName = intent.getStringExtra(KEY_FIRST_NAME) ?: ""
        val lastName = intent.getStringExtra(KEY_LAST_NAME) ?: ""
        val gender = intent.getStringExtra(KEY_GENDER) ?: ""
        val additional = intent.getStringExtra(KEY_ADDITIONAL) ?: ""

        val sb = StringBuilder().apply {
            append("Login: $login\n")
            append("Password: $password\n")
            if (firstName.isNotEmpty()){
                append("Name: $firstName\n")
            }
            if (lastName.isNotEmpty()){
                append("Last name: $lastName\n")
            }
            if (gender.isNotEmpty()){
                append("Gender: $gender\n")
            }
            if (additional.isNotEmpty()){
                append("Additional information: $additional\n")
            }
        }
        binding.informationText.text = sb.toString().trim()

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    companion object{
        private const val DEFAULT_STRING = "anonymous"
        const val KEY_LOGIN = "login"
        const val KEY_PASSWORD = "password"
        const val KEY_FIRST_NAME = "first name"
        const val KEY_LAST_NAME = "last name"
        const val KEY_GENDER = "gender"
        const val KEY_ADDITIONAL = "additional"
    }
}