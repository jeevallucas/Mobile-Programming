package com.example.progmob2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class RecyclerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        var btnSampleCard : Button = findViewById(R.id.btnSampleCard)
        btnSampleCard.setOnClickListener(View.OnClickListener { View ->
            var intent = Intent(this@RecyclerActivity,SampleCardView::class.java)
            startActivity(intent)
        })

        var btnSampleList : Button = findViewById(R.id.btnSampleList)
        btnSampleList.setOnClickListener(View.OnClickListener { view ->
            var intent = Intent(this@RecyclerActivity,SampleListView::class.java)
            startActivity(intent)
        })

        var btnSampleRecycle : Button = findViewById(R.id.btnSampleRecycle)
        btnSampleRecycle.setOnClickListener(View.OnClickListener { view ->
            var intent = Intent(this@RecyclerActivity,SampleRecyclerView::class.java)
            startActivity(intent)
        })
    }
}