package com.example.sehati.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sehati.BottomNavActivity
import com.example.sehati.Preferences
import com.example.sehati.R
import com.example.sehati.register.RegisterActivity
import com.example.sehati.ResultState
import com.example.sehati.api.body.LoginBody
import com.example.sehati.databinding.ActivityLoginBinding
import com.example.sehati.ProgressDialog

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val progressDialog = ProgressDialog(this)
        val preferences = Preferences(this)

        binding.loginBtn.setOnClickListener {

            val email = binding.editTextTextEmailAddress.text.toString()
            val password = binding.editTextTextPassword.text.toString()

            if (email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show()
            } else {

                val body = LoginBody(email, password)

                viewModel.login(body).observe(this) {
                    when (it) {
                        is ResultState.Error -> {
                            progressDialog.hide()
                            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                        }

                        is ResultState.Loading -> {
                            progressDialog.show()
                        }

                        is ResultState.Success -> {
                            progressDialog.hide()
                            preferences.saveSession()
                            val intent = Intent(this@LoginActivity, BottomNavActivity::class.java).apply {
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            }
                            startActivity(intent)
                        }
                    }
                }
            }
        }

        binding.daftarTxt.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}

