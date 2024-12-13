package com.example.sehati.diagnose_result

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
import com.example.sehati.RekomendasiAiActivity
import com.example.sehati.ResultState
import com.example.sehati.api.body.DiagnoseBody
import com.example.sehati.databinding.ActivityDiagnoseResultBinding
import com.example.sehati.login.LoginViewModel
import com.example.sehati.room.recommend.Recommend

class DiagnoseResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityDiagnoseResultBinding
    private val viewModel: DiagnoseResultModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDiagnoseResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val diagnose = intent.getStringExtra("diagnose")
        val list = intent.getStringArrayListExtra("list")

        binding.textview335.text = diagnose
        val progressDialog = ProgressDialog(this)
        binding.loginBtn.setOnClickListener {
            finish()
        }

        binding.buatR.setOnClickListener {
            list?.let { it1 -> DiagnoseBody(it1) }?.let { it2 ->
                viewModel.recommend(it2).observe(this) {
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
                            viewModel.insertRecommend(Recommend(0,it.value.data.recommendation))
                            val intent = Intent(this, RekomendasiAiActivity::class.java)
                            intent.putExtra("recommend", it.value.data.recommendation)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}