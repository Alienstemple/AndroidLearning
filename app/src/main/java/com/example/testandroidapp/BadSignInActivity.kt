package com.example.testandroidapp

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
        badSignInBinding.alertBadSignIn.text.toString()  // TODO concat with login
    }
}