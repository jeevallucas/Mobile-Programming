package com.example.progmob2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class ServiceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)

        var btnGetAPI : Button = findViewById(R.id.btnGetAPI)
        btnGetAPI.setOnClickListener(View.OnClickListener { view ->
            var intent = Intent(this@ServiceActivity, GetAPIActivity::class.java)
            startActivity(intent)
        })
    }
}