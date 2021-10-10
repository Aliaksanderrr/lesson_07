package com.minchinovich.lesson_07

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.minchinovich.lesson_07.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val loginErrorMessage by lazy { getString(R.string.login_activity_error_login_empty) }
    private val passwordErrorMessage by lazy { getString(R.string.login_activity_error_password_empty) }
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.verificationButton.setOnClickListener {
            binding.loginEditText.error = null
            binding.passwordEditText.error = null
            if (checkLogin() && checkPassword()) {
                startActivity(
                    Intent(this, InformationActivity::class.java)
                        .putExtra(
                            InformationActivity.KEY_LOGIN,
                            binding.loginEditText.text.toString().trim()
                        )
                        .putExtra(
                            InformationActivity.KEY_PASSWORD,
                            binding.passwordEditText.text.toString()
                        )
                )
            }
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun checkLogin(): Boolean {
        if (binding.loginEditText.text.toString().trim().isEmpty()) {
            binding.loginEditText.error = loginErrorMessage
            binding.loginEditText.requestFocus()
            return false
        }
        return true
    }

    private fun checkPassword(): Boolean {
        if (binding.passwordEditText.text.toString().isEmpty()) {
            binding.passwordEditText.error = passwordErrorMessage
            binding.passwordEditText.requestFocus()
            return false
        }
        return true
    }

}