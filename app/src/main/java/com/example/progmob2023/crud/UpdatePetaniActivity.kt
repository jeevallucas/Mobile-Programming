package com.example.progmob2023.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.progmob2023.R
import com.example.progmob2023.model.DataItem
import com.example.progmob2023.model.ResponseAddPetani
import com.example.progmob2023.network.NetworkConfig
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdatePetaniActivity : AppCompatActivity() {
    lateinit var edId : EditText
    lateinit var edNama : EditText
    lateinit var edAlamat : EditText
    lateinit var edProvinsi : EditText
    lateinit var edKabupaten : EditText
    lateinit var edKecamatan : EditText
    lateinit var edKelurahan : EditText
    lateinit var edNamaIstri : EditText
    lateinit var edJumlahLahan : EditText
    lateinit var edFoto : EditText
    lateinit var btnSimpanPetani : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_petani)

        edId = findViewById(R.id.edId)
        if(intent.extras != null){
            var bundle : Bundle ?= intent.extras
            var strTes : String = bundle?.getString("idPetani").toString()
            //Snackbar.make(getWindow().getDecorView().getRootView(),strTes, Snackbar.LENGTH_LONG).show()
            edId.setText(strTes)
        }
        edNama = findViewById(R.id.edNama)
        edAlamat = findViewById(R.id.edAlamat)
        edProvinsi = findViewById(R.id.edProvinsi)
        edKabupaten = findViewById(R.id.edKabupaten)
        edKecamatan = findViewById(R.id.edKecamatan)
        edKelurahan = findViewById(R.id.edKelurahan)
        edNamaIstri =findViewById(R.id.edNamaIstri)
        edJumlahLahan = findViewById(R.id.edJumlahLahan)
        edFoto = findViewById(R.id.edFoto)
        btnSimpanPetani = findViewById(R.id.btnSimpanPetani)

        btnSimpanPetani.setOnClickListener(View.OnClickListener { view ->
            var ptn = DataItem()
            ptn.id = edId.text.toString()
            ptn.nama = edNama.text.toString()
            ptn.alamat = edAlamat.text.toString()
            ptn.provinsi = edProvinsi.text.toString()
            ptn.kabupaten = edKabupaten.text.toString()
            ptn.kecamatan = edKecamatan.text.toString()
            ptn.kelurahan = edKelurahan.text.toString()
            ptn.namaIstri = edNamaIstri.text.toString()
            ptn.jumlahLahan = edJumlahLahan.text.toString()
            ptn.foto = edFoto.text.toString()

            NetworkConfig().getService()
                .updatePetani(edId.text.toString().toInt(), ptn)
                .enqueue(object : Callback<ResponseAddPetani?> {
                    override fun onFailure(call: Call<ResponseAddPetani?>, t: Throwable) {
                        Toast.makeText(this@UpdatePetaniActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                    }
                    override fun onResponse(
                        call: Call<ResponseAddPetani?>,
                        response: Response<ResponseAddPetani?>
                    ) {
                        Toast.makeText(this@UpdatePetaniActivity, "Berhasil Ubah Data", Toast.LENGTH_SHORT).show()
                    }
                })
        })
    }
}