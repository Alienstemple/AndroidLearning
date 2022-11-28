package com.example.testandroidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondScreen : AppCompatActivity() {
    val prop = "some text"
    override fun onCreate(savedInstanceState: Bundle?) {  // передали данные в Bundle
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)
        getIntent().getStringExtra(Intent.EXTRA_EMAIL)
    }
}