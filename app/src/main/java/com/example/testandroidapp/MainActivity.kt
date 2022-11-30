package com.example.testandroidapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.testandroidapp.constance.Constance
import com.example.testandroidapp.databinding.ActivityMainBinding
import com.example.testandroidapp.entities.User

class MainActivity : AppCompatActivity() {
    val TAG = "MainActLog"

    lateinit var bindingClass: ActivityMainBinding

    private var launcher: ActivityResultLauncher<Intent>? = null  // to launch sign in activity

    private var login: String = "empty"

    val userMap = mapOf(
        "sam" to User("Sam", "Smith", "sam20@mail.com", "+78453330099"),
        "mark" to User("Mark", "Stark", "mark8@mail.com", "+78457775533"),
        "kate" to User("Kate", "Fleen", "kate1@mail.com", "+784533388877"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")

        bindingClass = ActivityMainBinding.inflate(layoutInflater)  // view binding was added, here find layout
        setContentView(bindingClass.root)  // set MainActivity

        // init SignInActivity launcher
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val autorisationResult = result.data?.getStringExtra("key1")
            }
        }
    }

    fun onClickSignIn(view: View) {
        login = bindingClass.loginTextInput.text.toString()   // retreive login
        Log.d(TAG, "Login from text input: " + login)

        if (userMap.containsKey(login)) {
            val intent = Intent(this, SignInActivity::class.java)
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
            intent.putExtra(Constance.LOGIN, login)
            intent.putExtra(Constance.NAME, userMap[login]?.name1)
            intent.putExtra(Constance.NAME2, userMap[login]?.name2)
            intent.putExtra(Constance.EMAIL, userMap[login]?.email)
            intent.putExtra(Constance.PHONE, userMap[login]?.phone)
            launcher?.launch(intent)
        }
    }
}