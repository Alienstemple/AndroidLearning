package com.example.testandroidapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.testandroidapp.constance.Constance
import com.example.testandroidapp.databinding.ActivitySignInBinding
import com.example.testandroidapp.entities.User
import kotlin.random.Random

class SignInActivity : AppCompatActivity() {
    val TAG = "SignInActLog"
    lateinit var signInBinding: ActivitySignInBinding
    private var greetingsList =
        listOf("Здравствуйте", "Добрый день", "Хорошего дня", "Приветcтвую", "Отличного настроения")
    val userMap = mapOf(
        "sam" to User("Sam", "Smith", "sam20@mail.com", "+78453330099"),
        "mark" to User("Mark", "Stark", "mark8@mail.com", "+78457775533"),
        "kate" to User("Kate", "Fleen", "kate1@mail.com", "+784533388877")
    )

    override fun onCreate(savedInstanceState: Bundle?) {  // передали данные в Bundle
        super.onCreate(savedInstanceState)
        signInBinding = ActivitySignInBinding.inflate(layoutInflater)   // show our screen
        setContentView(signInBinding.root)

        // Random Greeting.
        if (savedInstanceState == null)  // Generate only if ActivityLaunches first time
            signInBinding.greetingTextView.text =
                greetingsList[greetingsList.indices.random()]  // Kotlin - style

        // initialize variables
        val login = intent.getStringExtra(Constance.LOGIN)
            .toString()  // getIntent set login from ActivityMain to login TextView

        Log.d(TAG, "Passed from ActivityMain login is " + login)

        if (userMap.containsKey(login)) {
            Log.d(TAG, "Login found in our Map: " + login)
            signInBinding.login.text = login
            signInBinding.phone.text = userMap[login]?.phone
            // TODO other fields
        } else {
            Log.d(TAG, "Login NOT found: " + login)
            setResult(RESULT_CANCELED) // return to MainActivity
            finish()
        }

        signInBinding.callButton.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:" + Constance.SAMPLE_PHONE)
            startActivity(dialIntent)
        }

        signInBinding.buttonBack.setOnClickListener {
            setResult(RESULT_OK)
            finish()
        }

        signInBinding.smsButton.setOnClickListener {
            checkSmsPermission()
            val phone: String = signInBinding.phone.toString()
            val smsIntent = Intent(Intent.ACTION_VIEW)
            smsIntent.data = Uri.parse("sms:$phone")
            startActivity(smsIntent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val greetingText = signInBinding.greetingTextView.text
        outState?.putCharSequence("savedText", greetingText)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val greetingText = savedInstanceState?.getCharSequence("savedText")
        signInBinding.greetingTextView.text = greetingText
    }

    private fun checkSmsPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 101)
        }
    }
}