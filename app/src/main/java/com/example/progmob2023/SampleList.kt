package com.example.progmob2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SampleList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_list)

        findViewById<Button>(R.id.btnShowList).setOnClickListener {
            startActivity(Intent(this, SampleListView::class.java))
        }

        findViewById<Button>(R.id.btnSampleRV).setOnClickListener {
            startActivity(Intent(this, SampleRecyclerView::class.java))
        }

        findViewById<Button>(R.id.btnCardView).setOnClickListener {
            startActivity(Intent(this, SampleCardView::class.java))
        }
    }
}
