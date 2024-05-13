package com.example.moisesapi.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.moisesapi.R
import com.example.moisesapi.databinding.ActivityLoginBinding
import com.example.moisesapi.databinding.ActivityTelaCadastroBinding

class Tela_Cadastro : AppCompatActivity() {

    private lateinit var binding: ActivityTelaCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnletras.setOnClickListener {
            val navegarTelaLetras = Intent(this, MainActivity::class.java)
            startActivity(navegarTelaLetras)
        }
    }
}