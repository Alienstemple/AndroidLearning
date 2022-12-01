package com.example.testandroidapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.testandroidapp.constance.Constance
import com.example.testandroidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActLog"
    lateinit var bindingClass: ActivityMainBinding
    private var launcher: ActivityResultLauncher<Intent>? = null  // to launch sign in activity

    private var login: String = "empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        bindingClass = ActivityMainBinding.inflate(layoutInflater)  // view binding was added, here find layout
        setContentView(bindingClass.root)  // set MainActivity

        bindingClass.SignInButton.setOnClickListener {
            login = bindingClass.loginTextInput.text.toString()   // retreive login
            Log.d(TAG, "Login from text input: " + login)

            // Launch SignInActivity, pass login
            val signInIntent = Intent(this, SignInActivity::class.java)
            signInIntent.putExtra(Constance.LOGIN, login)   // pass login
            launcher?.launch(signInIntent)
        }

        // init SignInActivity launcher
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
            if (result.resultCode == RESULT_CANCELED) {  // If Registration failed (user not found)
                Log.d(TAG, "Registration failed (user not found). Launching BadSignInActivity")
                val badSignInIntent = Intent(this, BadSignInActivity::class.java)
                badSignInIntent.putExtra(Constance.LOGIN, login)   // pass login
                launcher?.launch(badSignInIntent)
            }
        }
    }
}