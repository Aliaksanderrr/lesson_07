package com.minchinovich.lesson_07

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.RadioButton
import com.minchinovich.lesson_07.databinding.ActivityRegistrationBinding

private const val MIN_LOGIN_LENGTH = 4
private const val MIN_PASSWORD_LENGTH = 8

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.loginEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {return}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {return}

            override fun afterTextChanged(s: Editable?) {
                if (!checkLogin(s.toString().trim())){
                    binding.loginEditText.error =
                        getString(R.string.registration_activity_error_login_short)
                }
            }

        })

        binding.passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {return}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {return}

            override fun afterTextChanged(s: Editable?) {
                if (!checkPassword(s.toString())){
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
            if (checkAllFields()){
                val intent = Intent(this, InformationActivity::class.java)
                intent.putExtra(InformationActivity.KEY_LOGIN, binding.loginEditText.text.toString())
                intent.putExtra(InformationActivity.KEY_PASSWORD, binding.passwordEditText.text.toString())
                intent.putExtra(InformationActivity.KEY_FIRST_NAME, binding.firstNameEditText.text.toString())
                intent.putExtra(InformationActivity.KEY_LAST_NAME, binding.lastNameEditText.text.toString())
                val view = findViewById<RadioButton>(binding.genderRadioGroup.checkedRadioButtonId)
                intent.putExtra(InformationActivity.KEY_GENDER, view.text.toString())
                intent.putExtra(InformationActivity.KEY_ADDITIONAL, binding.additionalInformationEditText.text.toString())
                startActivity(intent)
            }
        }

        binding.backButton.setOnClickListener {
            finish()
        }

    }

    private fun checkAllFields(): Boolean{
        when{
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
            binding.passwordRepeatEditText.text.toString() != binding.passwordEditText.text.toString() ->{
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

    private fun checkLogin(text: String): Boolean{
        return text.length >= MIN_LOGIN_LENGTH
    }

    private fun checkPassword(text: String): Boolean{
        return text.length >= MIN_PASSWORD_LENGTH
    }


}