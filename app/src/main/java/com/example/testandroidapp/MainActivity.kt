package com.example.testandroidapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.testandroidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding

    private var login: String = "empty"
    private var name: String = "empty"
    private var name2: String = "empty"
    private var email: String = "empty"
    private var phone: String = "empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActLog", "onCreate")
        bindingClass = ActivityMainBinding.inflate(layoutInflater)  // view binding was added, here find layout
        setContentView(bindingClass.root)  // set MainActivity
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
    fun onClickSignIn(view: View) {

    }

    fun onClickSignUp(view: View) {

    }
}