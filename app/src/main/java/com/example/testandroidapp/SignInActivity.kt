package com.example.testandroidapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.testandroidapp.constance.Constance
import com.example.testandroidapp.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    lateinit var signInBinding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {  // передали данные в Bundle
        super.onCreate(savedInstanceState)
        signInBinding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(signInBinding.root)

        val login = intent.getStringExtra(Constance.LOGIN).toString()  // set login from ActivityMain to login TextView
        Log.d("SignInActLog", "Passed from ActivityMain login is " + login)
        signInBinding.login.text = login
        signInBinding.phone.text = Constance.SAMPLE_PHONE

        signInBinding.callButton.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + Constance.SAMPLE_PHONE)
            startActivity(dialIntent)
        }
    }
// TODO add onClickBack (putExtra, setResult, finish)
}