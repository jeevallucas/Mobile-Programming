package com.example.progmob2023.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.progmob2023.model.ResponseUsersItem
import com.example.progmob2023.R

class UsersAdapter {
    class UsersAdapter(val users: List<ResponseUsersItem>?) :
        RecyclerView.Adapter<UsersAdapter.UsersHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.UsersHolder {
            return UsersHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_user, parent, false))
        }

        override fun onBindViewHolder(holder: UsersAdapter.UsersHolder, position: Int) {
            holder.bindUsers(users?.get(position))
        }

        override fun getItemCount(): Int {
            return users?.size ?: 0
        }

        class UsersHolder(view: View) : RecyclerView.ViewHolder(view) {
            lateinit var txtNama: TextView
            lateinit var txtEmail: TextView
            lateinit var txtAlamat: TextView
            lateinit var txtNoTelp: TextView
            fun bindUsers(users: ResponseUsersItem?) {
                itemView.apply {
                    txtNama = findViewById(R.id.nama)
                    txtEmail = findViewById(R.id.email)
                    txtAlamat = findViewById(R.id.alamat)
                    txtNoTelp = findViewById(R.id.notelp)
                    txtNama.text = users?.name
                    txtEmail.text = users?.email
                    val address = "${users?.address?.street}, ${ users?.address?.city }, ${ users?.address?.suite }, ${ users?.address?.zipcode }"
                    txtAlamat.text = address
                    txtNoTelp.text = users?.phone
                }
            }
        }
    }
}