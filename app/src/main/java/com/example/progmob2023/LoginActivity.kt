package com.example.progmob2023

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    val prefs_name = "session_login"
    lateinit var sharedPref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var btnLogin: Button = findViewById(R.id.btnLogin)
        var edEmail : EditText = findViewById(R.id.edEmail)
        var edPassword : EditText = findViewById(R.id.edPassword)

        sharedPref = getSharedPreferences(prefs_name, Context.MODE_PRIVATE)

        var tmpEmail = sharedPref.getString("email", null)
        var tmpPassword = sharedPref.getString("password", null)

        if(!tmpEmail.isNullOrEmpty() && !tmpPassword.isNullOrEmpty()) {
            finish()
            var intent = Intent(this@LoginActivity, GetAPIActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener(View.OnClickListener { view ->
            val sharedEditor : SharedPreferences.Editor = sharedPref.edit()
            sharedEditor.putString("email", edEmail.text.toString())
            sharedEditor.putString("password", edPassword.text.toString())
            sharedEditor.apply()

            finish()
            var intent = Intent(this@LoginActivity, GetAPIActivity::class.java)
            startActivity(intent)
        })
    }
}