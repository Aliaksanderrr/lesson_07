package com.minchinovich.lesson_07

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.minchinovich.lesson_07.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.verificationButton.setOnClickListener {
            when {
                !checkLogin() -> {}
                !checkPassword() -> {}
                checkLogin() && checkPassword() -> {
                    val intent = Intent(this, InformationActivity::class.java).apply {
                        putExtra(InformationActivity.KEY_LOGIN, binding.loginEditText.text.toString().trim())
                        putExtra(InformationActivity.KEY_PASSWORD, binding.passwordEditText.text.toString())
                    }
                    startActivity(intent)
                }
            }
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun checkLogin(): Boolean {
        if (binding.loginEditText.text.toString().trim().isEmpty()) {
            binding.loginEditText.error = getString(R.string.login_activity_error_login_empty)
            binding.loginEditText.requestFocus()
            return false
        }
        return true
    }

    private fun checkPassword(): Boolean {
        if (binding.passwordEditText.text.toString().isEmpty()) {
            binding.passwordEditText.error = getString(R.string.login_activity_error_password_empty)
            binding.passwordEditText.requestFocus()
            return false
        }
        return true
    }

}