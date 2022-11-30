package com.example.testandroidapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.testandroidapp.constance.Constance
import com.example.testandroidapp.databinding.ActivitySignInBinding
import kotlin.random.Random

class SignInActivity : AppCompatActivity() {
    lateinit var signInBinding: ActivitySignInBinding
    private var greetingsList = listOf("Привет 1", "Добрый день", "Хорошего дня", "Привет 2", "Привет 3")

    override fun onCreate(savedInstanceState: Bundle?) {  // передали данные в Bundle
        super.onCreate(savedInstanceState)
        signInBinding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(signInBinding.root)

        // Random Greeting. Changes, when Activity killed // TODO onSaveIntentState
        signInBinding.greetingTextView.text = greetingsList.get(Random.nextInt(0, greetingsList.size))

        val login = intent.getStringExtra(Constance.LOGIN).toString()  // getIntent set login from ActivityMain to login TextView
        Log.d("SignInActLog", "Passed from ActivityMain login is " + login)
        signInBinding.login.text = login
        signInBinding.phone.text = Constance.SAMPLE_PHONE

        signInBinding.callButton.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + Constance.SAMPLE_PHONE)
            startActivity(dialIntent)
        }

        signInBinding.buttonBack.setOnClickListener {
            val intent = Intent()
            intent.putExtra("key1", "Returned from SignIn")
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}