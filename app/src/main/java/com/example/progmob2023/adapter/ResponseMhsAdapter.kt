package com.example.progmob2023.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.progmob2023.R
import com.example.progmob2023.crud.GetMhsActivity
import com.example.progmob2023.model.MhsDataItem
import com.example.progmob2023.model.ResponseAddMhs
import com.example.progmob2023.network.NetworkConfigMhs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResponseMhsAdapter(var mhs: List<MhsDataItem>?): RecyclerView.Adapter<ResponseMhsAdapter.MhsHolder>() {
    lateinit var mContext: Context
    lateinit var adapter: ResponseMhsAdapter

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResponseMhsAdapter.MhsHolder {
        return MhsHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_mhs, parent, false))
    }

    override fun onBindViewHolder(holder: ResponseMhsAdapter.MhsHolder, position: Int) {
        holder.bindMhs(mhs?.get(position))
        var popupMenu = PopupMenu(holder.itemView.context, holder.itemView)
        popupMenu.menu.add(Menu.NONE,0,0,"Edit")
        popupMenu.menu.add(Menu.NONE,1,1,"Delete")
        popupMenu.setOnMenuItemClickListener { menuItem ->
            val id = menuItem.itemId
            mContext = holder.itemView.context
            if (id == 1) {
                var idTmp = mhs?.get(position)?.id.toString()
                NetworkConfigMhs().getServiceMhs()
                    .deleteMhs(idTmp.toInt())
                    .enqueue(object : Callback<ResponseAddMhs?> {
                        override fun onFailure(call: Call<ResponseAddMhs?>, t: Throwable) {
                            Toast.makeText(holder.itemView.context, t.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                        override fun onResponse(
                            call: Call<ResponseAddMhs?>,
                            response: Response<ResponseAddMhs?>
                        ) {
                            Toast.makeText(holder.itemView.context, "Berhasil Hapus Data", Toast.LENGTH_SHORT).show()
                            (mContext as GetMhsActivity).finish()
                            var intent = Intent(mContext, GetMhsActivity::class.java)
                            mContext.startActivity(intent)
                        }
                    })
            }
            false
        }
        holder.itemView.setOnClickListener(View.OnClickListener { view ->
            popupMenu.show()
        })
    }

    override fun getItemCount(): Int {
        return mhs?.size ?: 0
    }

    class MhsHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var txtNama: TextView
        lateinit var txtNim: TextView
        lateinit var txtAlamat: TextView
        lateinit var txtEmail: TextView
        lateinit var txtFoto: TextView

        fun bindMhs(mhs: MhsDataItem?) {
            itemView.apply {
                txtNama = findViewById(R.id.nama)
                txtNim = findViewById(R.id.nim)
                txtAlamat = findViewById(R.id.alamat)
                txtEmail = findViewById(R.id.email)
                txtFoto = findViewById(R.id.foto)

                txtNama.text = mhs?.nama
                txtNim.text = mhs?.nim
                txtAlamat.text = mhs?.alamat
                txtEmail.text = mhs?.email
                txtFoto.text = mhs?.foto
            }
        }
    }

    fun setData(newMhsList: List<MhsDataItem?>?) {
        var mhsList = newMhsList
        notifyDataSetChanged()
    }

    fun getItem(position: Int): MhsDataItem? {
        return mhs?.get(position)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo

        // Use the getItem method from the adapter to get the selected item
        var selectedMhs = adapter.getItem(info.position)

        // Now, selectedMhs holds the selected item

        return super.onContextItemSelected(item)
    }
}
