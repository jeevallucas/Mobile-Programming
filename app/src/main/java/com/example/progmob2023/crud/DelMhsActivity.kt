package com.example.progmob2023.crud

import android.os.Bundle
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.progmob2023.R
import com.example.progmob2023.adapter.ResponseMhsAdapter
import com.example.progmob2023.model.MhsDataItem
import com.example.progmob2023.model.ResponseMhs
import com.example.progmob2023.network.NetworkConfigMhs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DelMhsActivity : AppCompatActivity() {
    private lateinit var rvMhs: RecyclerView
    private lateinit var adapter: ResponseMhsAdapter
    private lateinit var selectedMhs: MhsDataItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_del_mhs)

        rvMhs = findViewById(R.id.rvMhs)
        rvMhs.layoutManager = LinearLayoutManager(this)
        adapter = ResponseMhsAdapter(null)
        rvMhs.adapter = adapter

        // Set a click listener for your RecyclerView items
        adapter.setOnItemClickListener(object : ResponseMhsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val selectedMhs = adapter.getItem(position)
                // Do something with the selectedMhs
                // For example, you can call your deleteMhs method here
                deleteMhs(selectedMhs)
            }
        })

        rvMhs.adapter = adapter
        fetchAndDisplayMhsList()
    }

    private fun fetchAndDisplayMhsList() {
        NetworkConfigMhs().getServiceMhs()
            .getMhs()
            .enqueue(object : Callback<ResponseMhs?> {
                override fun onFailure(call: Call<ResponseMhs?>, t: Throwable) {
                    Toast.makeText(this@DelMhsActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<ResponseMhs?>, response: Response<ResponseMhs?>) {
                    val mhsList = response.body()?.data
                    adapter.setData(mhsList)
                }
            })
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        selectedMhs = adapter.getItemAtPosition(info.position)

        return super.onContextItemSelected(item)
    }

    private fun deleteMhs(student: MhsDataItem) {
        NetworkConfigMhs().getServiceMhs()
            .deleteMhs(selectedMhs.id)
            .enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(this@DelMhsActivity, "Gagal menghapus mahasiswa", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    fetchAndDisplayMhsList()
                    Toast.makeText(this@DelMhsActivity, "Mahasiswa terhapus", Toast.LENGTH_SHORT).show()
                }
            })
    }
}
