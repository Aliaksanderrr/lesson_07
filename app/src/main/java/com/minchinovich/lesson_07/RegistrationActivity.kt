package com.minchinovich.lesson_07

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import com.minchinovich.lesson_07.databinding.ActivityRegistrationBinding
import com.minchinovich.lesson_07.util.SimpleTextWatcher

private const val MIN_LOGIN_LENGTH = 4
private const val MIN_PASSWORD_LENGTH = 8

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginEditText.addTextChangedListener(object : SimpleTextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!checkLogin(s.toString().trim())) {
                    binding.loginEditText.error =
                        getString(R.string.registration_activity_error_login_short)
                }
            }
        })

        binding.passwordEditText.addTextChangedListener(object : SimpleTextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!checkPassword(s.toString())) {
                    binding.passwordEditText.error =
                        getString(R.string.registration_activity_error_password_simple)
                }
            }
        })

        binding.verificationButton.isActivated = false
        binding.agreement.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.verificationButton.isEnabled = true
            } else {
                binding.verificationButton.isActivated = false
                binding.verificationButton.isEnabled = false
            }
        }

        binding.verificationButton.setOnClickListener {
            if (checkAllFields()) {
                val view = findViewById<RadioButton>(binding.genderRadioGroup.checkedRadioButtonId)
                startActivity(Intent(this, InformationActivity::class.java)
                    .putExtra(InformationActivity.KEY_LOGIN, binding.loginEditText.text.toString())
                    .putExtra(InformationActivity.KEY_PASSWORD, binding.passwordEditText.text.toString())
                    .putExtra(InformationActivity.KEY_FIRST_NAME, binding.firstNameEditText.text.toString())
                    .putExtra(InformationActivity.KEY_LAST_NAME, binding.lastNameEditText.text.toString())
                    .putExtra(InformationActivity.KEY_GENDER, view.text.toString())
                    .putExtra(InformationActivity.KEY_ADDITIONAL, binding.additionalInformationEditText.text.toString())
                )
            }
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun checkAllFields(): Boolean {
        when {
            !checkLogin(binding.loginEditText.text.toString().trim()) -> {
                binding.loginEditText.error =
                    getString(R.string.registration_activity_error_login_short)
                binding.loginEditText.requestFocus()
                return false
            }
            !checkPassword(binding.passwordEditText.text.toString()) -> {
                binding.passwordEditText.error =
                    getString(R.string.registration_activity_error_password_simple)
                binding.passwordEditText.requestFocus()
                return false
            }
            binding.passwordRepeatEditText.text.toString() != binding.passwordEditText.text.toString() -> {
                binding.passwordRepeatEditText.error =
                    getString(R.string.registration_activity_error_repeat_password_simple)
                binding.passwordRepeatEditText.requestFocus()
                return false
            }
            binding.firstNameEditText.text.isEmpty() -> {
                binding.firstNameEditText.error =
                    getString(R.string.registration_activity_error_empty_field)
                binding.firstNameEditText.requestFocus()
                return false
            }
            binding.lastNameEditText.text.isEmpty() -> {
                binding.lastNameEditText.error =
                    getString(R.string.registration_activity_error_empty_field)
                binding.lastNameEditText.requestFocus()
                return false
            }
        }
        return true
    }

    private fun checkLogin(text: String): Boolean {
        return text.length >= MIN_LOGIN_LENGTH
    }

    private fun checkPassword(text: String): Boolean {
        return text.length >= MIN_PASSWORD_LENGTH
    }


}