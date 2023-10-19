package com.example.progmob2023.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.progmob2023.R
import com.example.progmob2023.adapter.ResponseMhsAdapter
import com.example.progmob2023.model.MhsDataItem
import com.example.progmob2023.model.ResponseAddMhs
import com.example.progmob2023.model.ResponseMhs
import com.example.progmob2023.network.NetworkConfigMhs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddMhsActivity : AppCompatActivity() {
    lateinit var edNama: EditText
    lateinit var edNim: EditText
    lateinit var edAlamat: EditText
    lateinit var edEmail: EditText
    lateinit var edFoto: EditText
    lateinit var btnSimpanMhs: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_mhs)

        edNama = findViewById(R.id.edNama)
        edNim = findViewById(R.id.edNim)
        edAlamat = findViewById(R.id.edAlamat)
        edEmail = findViewById(R.id.edEmail)
        edFoto = findViewById(R.id.edFoto)
        btnSimpanMhs = findViewById(R.id.btnSimpanMhs)

        btnSimpanMhs.setOnClickListener(View.OnClickListener { view ->
            var mhs = MhsDataItem()
            mhs.nama = edNama.text.toString()
            mhs.nim = edNim.text.toString()
            mhs.alamat = edAlamat.text.toString()
            mhs.email = edEmail.text.toString()
            mhs.foto = edFoto.text.toString()
            mhs.id = null

            NetworkConfigMhs().getServiceMhs()
                .addMhs(mhs)
                .enqueue(object : Callback<ResponseAddMhs?> {
                    override fun onFailure(call: Call<ResponseAddMhs?>, t: Throwable) {
                        Toast.makeText(this@AddMhsActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<ResponseAddMhs?>,
                        response: Response<ResponseAddMhs?>
                    ) {
                        Toast.makeText(this@AddMhsActivity, "Berhasil tambah mahasiswa", Toast.LENGTH_SHORT).show()
                    }
                })
        })
    }
}
