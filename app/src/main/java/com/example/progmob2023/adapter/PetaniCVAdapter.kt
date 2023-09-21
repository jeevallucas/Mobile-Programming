package com.example.progmob2023.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.progmob2023.R
import com.example.progmob2023.model.Petani

class PetaniCVAdapter(val petani: List<Petani>) : RecyclerView.Adapter<PetaniCVAdapter.PetaniCVHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetaniCVHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item_dutatani, parent, false)
        return PetaniCVHolder(view)
    }

    override fun onBindViewHolder(holder: PetaniCVHolder, position: Int) {
        holder.bindPetani(petani[position])
    }

    override fun getItemCount(): Int {
        return petani.size
    }

    class PetaniCVHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtUser: TextView = itemView.findViewById(R.id.txtUser)
        val txtNama: TextView = itemView.findViewById(R.id.txtNama)
        val txtJumlahLahan: TextView = itemView.findViewById(R.id.txtJumlahLahan)
        val txtIdentifikasi: TextView = itemView.findViewById(R.id.txtIdentifikasi)
        val txtTambahLahan: TextView = itemView.findViewById(R.id.txtTambahLahan)

        fun bindPetani(petani: Petani) {
            txtUser.text = petani.user
            txtNama.text = petani.nama
            txtJumlahLahan.text = petani.jumlahLahan
            txtIdentifikasi.text = petani.identifikasi
            txtTambahLahan.text = petani.tambahLahan
        }
    }
}
