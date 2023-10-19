package com.example.progmob2023.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.progmob2023.HelpActivity
import com.example.progmob2023.R
import com.example.progmob2023.adapter.ResponseMhsAdapter
import com.example.progmob2023.adapter.UsersAdapter
import com.example.progmob2023.model.DataItem
import com.example.progmob2023.model.MhsDataItem
import com.example.progmob2023.model.ResponseMhs
import com.example.progmob2023.model.ResponseUsersItem
import com.example.progmob2023.network.NetworkConfig
import com.example.progmob2023.network.NetworkConfigMhs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetMhsActivity : AppCompatActivity() {
    lateinit var rvMhs: RecyclerView
    lateinit var fabAddMhs: FloatingActionButton

    override fun onRestart() {
        super.onRestart()

        rvMhs = findViewById(R.id.rvMhs)
        fabAddMhs = findViewById(R.id.fabAddMhs)

        NetworkConfigMhs().getServiceMhs()
            .getMhs()
            .enqueue(object : Callback<ResponseMhs?> {
                override fun onFailure(call: Call<ResponseMhs?>, t: Throwable) {
                    Toast.makeText(this@GetMhsActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<ResponseMhs?>,
                    response: Response<ResponseMhs?>
                ) {
                    rvMhs.apply {
                        layoutManager = LinearLayoutManager(this@GetMhsActivity)
                        adapter = ResponseMhsAdapter(response.body()?.data as List<MhsDataItem>?)
                    }
                }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_mhs)

        rvMhs = findViewById(R.id.rvMhs)
        fabAddMhs = findViewById(R.id.fabAddMhs)

        NetworkConfigMhs().getServiceMhs()
            .getMhs()
            .enqueue(object : Callback<ResponseMhs?> {
                override fun onFailure(call: Call<ResponseMhs?>, t: Throwable) {
                    Toast.makeText(this@GetMhsActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<ResponseMhs?>,
                    response: Response<ResponseMhs?>
                ) {
                    rvMhs.apply {
                        layoutManager = LinearLayoutManager(this@GetMhsActivity)
                        adapter = ResponseMhsAdapter(response.body()?.data as List<MhsDataItem>?)
                    }
                }
            })

        fabAddMhs.setOnClickListener(View.OnClickListener { view ->
            val intent = Intent(this@GetMhsActivity, AddMhsActivity::class.java)
            startActivity(intent)
        })
    }
}
