package com.example.progmob2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.progmob2023.adapter.PetaniCVAdapter
import com.example.progmob2023.model.Petani

class SampleCardView : AppCompatActivity() {
    lateinit var rvCardView: RecyclerView
    lateinit var petaniCVAdapter: PetaniCVAdapter
    lateinit var lPetani: List<Petani>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_card_view)

        rvCardView = findViewById(R.id.rvCardView)

        lPetani = listOf(
            Petani(
                user = "P01",
                nama = "Jeevallucas Gautama",
                jumlahLahan = "2",
                identifikasi = "1",
                tambahLahan = "3"
            ),
            Petani(
                user = "P02",
                nama = "Vivian",
                jumlahLahan = "3",
                identifikasi = "2",
                tambahLahan = "4"
            )
        )

        petaniCVAdapter = PetaniCVAdapter(lPetani)

        rvCardView.apply {
            layoutManager = LinearLayoutManager(this@SampleCardView)
            adapter = petaniCVAdapter
        }
    }
}
