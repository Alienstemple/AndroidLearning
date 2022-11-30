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

class MainActivity : AppCompatActivity() {
    val TAG = "MainActLog"

    lateinit var bindingClass: ActivityMainBinding

    private var launcher: ActivityResultLauncher<Intent>? = null  // to launch sign in activity

    private var login: String = "empty"
    private var name: String = "empty"
    private var name2: String = "empty"
    private var email: String = "empty"
    private var phone: String = "empty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate")
        bindingClass = ActivityMainBinding.inflate(layoutInflater)  // view binding was added, here find layout
        setContentView(bindingClass.root)  // set MainActivity

        // init AignInActivity launcher
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val text = result.data?.getStringExtra("key1")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Constance.REQUEST_CODE_SIGN_IN) {
            val loginConst = data?.getStringExtra(Constance.LOGIN)
            if(login == loginConst) {  // This user is present


            }
        } else if (requestCode == Constance.REQUEST_CODE_SIGN_UP) {

        }
    }
    fun onClickSignIn(view: View) {
        login = bindingClass.loginTextInput.text.toString()   // retreive login
        Log.d(TAG, "Login from text input: " + login)
        val intent = Intent(this, SignInActivity::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
        intent.putExtra(Constance.LOGIN, login)

        // TODO search login in map

        startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_IN)  // Activity must return result code?

    }

    fun onClickSignUp(view: View) {

    }
}