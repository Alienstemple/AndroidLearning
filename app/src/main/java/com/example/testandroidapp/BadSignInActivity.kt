package com.example.testandroidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testandroidapp.constance.Constance
import com.example.testandroidapp.databinding.ActivityBadSignInBinding
import com.example.testandroidapp.databinding.ActivitySignInBinding

class BadSignInActivity : AppCompatActivity() {
    lateinit var badSignInBinding: ActivityBadSignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        badSignInBinding = ActivityBadSignInBinding.inflate(layoutInflater)
        setContentView(badSignInBinding.root)
        val login = intent.getStringExtra(Constance.LOGIN).toString()
        badSignInBinding.alertBadSignIn.text = "Пользователь с логином $login не найден!"
        badSignInBinding.buttonBackBadSignIn.setOnClickListener {
            val backMainIntent = Intent(this, MainActivity::class.java)
            startActivity(backMainIntent)
        }
    }
}