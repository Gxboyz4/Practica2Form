package com.actividades.practica2form233410

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AlertDialog
import com.actividades.practica2form233410.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        emailFocusListener()
        passwordFocusListener()
        phoneFocusListener()
        nameFocusListener()

        binding.submitButton.setOnClickListener { submitForm() }
    }

    private fun submitForm() {
        binding.emailContainer.helperText = validEmail()
        binding.passwordContainer.helperText = validPassword()
        binding.phoneContainer.helperText = validPhone()
        binding.nameContainer.helperText = validName()
        val validEmail = binding.emailContainer.helperText == null
        val validPassword = binding.passwordContainer.helperText == null
        val validPhone = binding.phoneContainer.helperText == null
        val validName = binding.nameContainer.helperText==null

        if (validEmail && validPassword && validPhone && validName)
            resetForm()
        else
            invalidForm()
    }

    private fun invalidForm() {
        var message = ""
        if (binding.emailContainer.helperText != null)
            message += "\n\nEmail: " + binding.emailContainer.helperText
        if (binding.passwordContainer.helperText != null)
            message += "\n\nPassword: " + binding.passwordContainer.helperText
        if (binding.phoneContainer.helperText != null)
            message += "\n\nPhone: " + binding.phoneContainer.helperText
        if (binding.nameContainer.helperText!=null)
            message += "\n\nName: " + binding.nameContainer.helperText

        AlertDialog.Builder(this)
            .setTitle("Invalid Form")
            .setMessage(message)
            .setPositiveButton("Okay") { _, _ ->
                // do nothing
            }
            .show()
    }

    private fun resetForm() {
        var message = "Email: " + binding.emailEditText.text
        message += "\nPassword: " + binding.passwordEditText.text
        message += "\nPhone: " + binding.phoneEditText.text
        //Código añadido
        message += "\nName: " + binding.nameEditText.text
        message += "\nGender: " + binding.genderSpinner.selectedItem.toString()
        AlertDialog.Builder(this)
            .setTitle("Form submitted")
            .setMessage(message)
            .setPositiveButton("Okay") { _, _ ->
                binding.emailEditText.text = null
                binding.passwordEditText.text = null
                binding.phoneEditText.text = null
                binding.nameEditText.text=null

                binding.emailContainer.helperText = getString(R.string.required)
                binding.passwordContainer.helperText = getString(R.string.required)
                binding.phoneContainer.helperText = getString(R.string.required)
                binding.nameContainer.helperText = getString(R.string.required)
            }
            .show()
    }

    private fun emailFocusListener() {
        binding.emailEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.emailContainer.helperText = validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val emailText = binding.emailEditText.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            return "Invalid Email Address"
        }
        return null
    }

    private fun passwordFocusListener() {
        binding.passwordEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.passwordContainer.helperText = validPassword()
            }
        }
    }

    private fun validPassword(): String? {
        val passwordText = binding.passwordEditText.text.toString()
        if (passwordText.length < 8) {
            return "Minimum 8 Character Password"
        }
        if (!passwordText.matches(".*[A-Z].*".toRegex())) {
            return "Must Contain 1 Upper-case Character"
        }
        if (!passwordText.matches(".*[a-z].*".toRegex())) {
            return "Must Contain 1 Lower-case Character"
        }
        if (!passwordText.matches(".*[@#\$%^&+=].*".toRegex())) {
            return "Must Contain 1 Special Character (@#\$%^&+=)"
        }

        return null
    }

    private fun phoneFocusListener() {
        binding.phoneEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.phoneContainer.helperText = validPhone()
            }
        }
    }

    private fun validPhone(): String? {
        val phoneText = binding.phoneEditText.text.toString()
        if (!phoneText.matches(".*[0-9].*".toRegex())) {
            return "Must be all Digits"
        }
        if (phoneText.length != 10) {
            return "Must be 10 Digits"
        }
        return null
    }

    private fun nameFocusListener() {
        binding.nameEditText.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.nameContainer.helperText = validName()
            }
        }
    }

    private fun validName(): String? {
        val nameText = binding.nameEditText.text.toString()
        if(!nameText.matches("[a-zA-Z]+".toRegex())){
            return "Must be contain letters"
        }
        if (nameText.length < 5){
            return "Must be 5 characters at least"
        }
        /*if (nameText.contains("[^a-zA-Z]".toRegex())) {
            return "Must not contain special characters"
        }

        if (nameText.matches(".*\\d.*".toRegex())) {
            return "Must not contain numbers"
        }
        */
        return null
    }
}