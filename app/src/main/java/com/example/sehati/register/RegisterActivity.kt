package com.example.sehati.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sehati.BottomNavActivity
import com.example.sehati.ProgressDialog
import com.example.sehati.R
import com.example.sehati.ResultState
import com.example.sehati.api.body.LoginBody
import com.example.sehati.api.body.RegisterBody
import com.example.sehati.databinding.ActivityRegisterBinding
import com.example.sehati.login.LoginActivity
import com.example.sehati.login.LoginViewModel

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val progressDialog = ProgressDialog(this)

        binding.loginBtn.setOnClickListener {

            val email = binding.emailEdt.text.toString()
            val firstName = binding.firstNameEdt.text.toString()
            val lastName = binding.lastNameEdt.text.toString()
            val password = binding.passwordEdt.text.toString()

            if (email.isBlank() || password.isBlank() || firstName.isBlank() || lastName.isBlank()) {
                Toast.makeText(this, "Data Kosong", Toast.LENGTH_SHORT).show()
            } else {

                val body = RegisterBody(firstName,lastName,email, password)

                viewModel.register(body).observe(this) {
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
                            val intent = Intent(this, BottomNavActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
        }

        binding.loginTxt.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}