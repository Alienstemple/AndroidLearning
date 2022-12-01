package com.example.testandroidapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.testandroidapp.constance.Constance
import com.example.testandroidapp.databinding.ActivitySignInBinding
import kotlin.random.Random

class SignInActivity : AppCompatActivity() {
    val TAG = "SignInActLog"
    lateinit var signInBinding: ActivitySignInBinding
    private var greetingsList = listOf("Привет 1", "Добрый день", "Хорошего дня", "Привет 2", "Привет 3")

    override fun onCreate(savedInstanceState: Bundle?) {  // передали данные в Bundle
        super.onCreate(savedInstanceState)
        signInBinding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(signInBinding.root)

        // Random Greeting.
        signInBinding.greetingTextView.text = greetingsList.get(Random.nextInt(0, greetingsList.size))

        // initialize variables
        val login = intent.getStringExtra(Constance.LOGIN).toString()  // getIntent set login from ActivityMain to login TextView
        val phone = intent.getStringExtra(Constance.PHONE).toString()
//        val msgText  // TODO


        Log.d(TAG, "Passed from ActivityMain login is " + login)
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

        signInBinding.smsButton.setOnClickListener {

            checkSmsPermission()

            val testPhone = "+79160630604"
            val msgText = "Hello! Sample sms text"

            try {
                val smsManager = SmsManager.getDefault()
//                smsManager = this.getSystemService(SmsManager::class.java)  // smsManager null!
//                smsManager = applicationContext.getSystemService(SmsManager::class.java)
//                smsManager = SmsManager.getDefault()

                val smsIntent = Intent(Intent.ACTION_VIEW)
                smsIntent.data = Uri.parse("sms:$testPhone")
                startActivity(smsIntent)

//                smsManager.sendTextMessage(testPhone, null, msgText, null, null)

//                Toast.makeText(applicationContext, "Message Sent", Toast.LENGTH_LONG).show()

            } catch (e: Exception) {
                Toast.makeText(applicationContext, "Please enter all the data.."+e.message.toString(), Toast.LENGTH_LONG)
                    .show()
                Log.d(TAG, "Error while sms sending: ${e.message.toString()}")
            }
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), 101)
        }
    }
}