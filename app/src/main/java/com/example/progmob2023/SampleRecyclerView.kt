package com.example.progmob2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.progmob2023.adapter.PetaniAdapter
import com.example.progmob2023.model.Petani

class SampleRecyclerView : AppCompatActivity() {
    lateinit var rvLatihan: RecyclerView
    lateinit var petaniAdapter: PetaniAdapter
    lateinit var lPetani: List<Petani>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_recycler_view)

        rvLatihan = findViewById(R.id.rvLatihan)

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

        petaniAdapter = PetaniAdapter(lPetani)
        rvLatihan.adapter = petaniAdapter
    }
}
