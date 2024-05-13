package com.example.moisesapi.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.moisesapi.R
import com.example.moisesapi.databinding.ActivityLoginBinding

class Login : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonGoogle.setOnClickListener {
            val navegarTelaCadastro = Intent(this, Tela_Cadastro::class.java)
            startActivity(navegarTelaCadastro)

        }

        binding.buttonFace.setOnClickListener {
            val navegarTelaCadastro = Intent(this, Tela_Cadastro::class.java)
            startActivity(navegarTelaCadastro)
        }

        binding.buttonEmail.setOnClickListener {
            val navegarTelaCadastro = Intent(this, Tela_Cadastro::class.java)
            startActivity(navegarTelaCadastro)
        }
    }
}