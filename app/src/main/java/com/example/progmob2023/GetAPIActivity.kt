package com.example.progmob2023

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.progmob2023.adapter.UsersAdapter
import com.example.progmob2023.model.ResponseUsersItem
import com.example.progmob2023.network.NetworkConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetAPIActivity : AppCompatActivity() {
    val prefs_name = "session_login"
    lateinit var sharedPref : SharedPreferences

    override fun onRestart() {
        super.onRestart()

        var rvUserAPI: RecyclerView = findViewById(R.id.rvUserAPI)
        sharedPref = getSharedPreferences(prefs_name, Context.MODE_PRIVATE)

        NetworkConfig().getService().getUsers().enqueue(object : Callback<List<ResponseUsersItem>> {
            override fun onFailure(
                call: Call<List<ResponseUsersItem>>, t:
                Throwable
            ) {
                Toast.makeText(
                    this@GetAPIActivity, t.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(
                call: Call<List<ResponseUsersItem>>,
                response: Response<List<ResponseUsersItem>>
            ) {
                rvUserAPI.apply {
                    layoutManager = LinearLayoutManager(this@GetAPIActivity)
                    adapter = UsersAdapter.UsersAdapter(response.body())
                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_apiactivity)

        var rvUserAPI: RecyclerView = findViewById(R.id.rvUserAPI)
        sharedPref = getSharedPreferences(prefs_name, Context.MODE_PRIVATE)

        NetworkConfig().getService().getUsers().enqueue(object : Callback<List<ResponseUsersItem>> {
            override fun onFailure(
                call: Call<List<ResponseUsersItem>>, t:
                Throwable
            ) {
                Toast.makeText(
                    this@GetAPIActivity, t.localizedMessage,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(
                call: Call<List<ResponseUsersItem>>,
                response: Response<List<ResponseUsersItem>>
            ) {
                rvUserAPI.apply {
                    layoutManager = LinearLayoutManager(this@GetAPIActivity)
                    adapter = UsersAdapter.UsersAdapter(response.body())
                }
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_logout, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.btnLogout -> {
                val editor : SharedPreferences.Editor = sharedPref.edit()
                editor.clear()
                editor.apply()
                finish()
                var intent = Intent(this@GetAPIActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}