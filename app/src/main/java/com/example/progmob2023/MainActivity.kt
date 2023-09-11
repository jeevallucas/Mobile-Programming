package com.example.progmob2023

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var tvMain : TextView
    lateinit var btnMain : Button
    lateinit var btnHelp : Button
    lateinit var btnLinear : Button
    lateinit var btnConstraint : Button
    lateinit var btnTable : Button
    lateinit var btnProtein : Button
    lateinit var edInputNama : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvMain = findViewById(R.id.textView2)
        tvMain.text = getString(R.string.text_progmob_main)

        btnMain = findViewById(R.id.btnHelloWorld)
        btnMain.text = "SAVE"

        btnHelp = findViewById(R.id.btnHelp)

        btnLinear = findViewById(R.id.btnLinear)

        btnConstraint = findViewById(R.id.btnConstraint)

        btnTable = findViewById(R.id.btnTable)

        btnProtein = findViewById(R.id.btnProtein)

        edInputNama = findViewById(R.id.ed_input_nama)
        btnMain.setOnClickListener(View.OnClickListener {
            tvMain.text = "Hi, " + edInputNama.text.toString() + "."
        })

        btnHelp.setOnClickListener(View.OnClickListener { view ->
            var bundle = Bundle()
            var strTmp = edInputNama.text.toString()
            bundle.putString("tesText",strTmp)

            var intent = Intent(this@MainActivity, HelpActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        })

        btnLinear.setOnClickListener(View.OnClickListener { view ->
            var bundle = Bundle()
            var strTmp = edInputNama.text.toString()
            bundle.putString("tesText",strTmp)

            var intent = Intent(this@MainActivity, LinearActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        })

        btnConstraint.setOnClickListener(View.OnClickListener { view ->
            var bundle = Bundle()
            var strTmp = edInputNama.text.toString()
            bundle.putString("tesText",strTmp)

            var intent = Intent(this@MainActivity, ConstraintActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        })

        btnTable.setOnClickListener(View.OnClickListener { view ->
            var bundle = Bundle()
            var strTmp = edInputNama.text.toString()
            bundle.putString("tesText",strTmp)

            var intent = Intent(this@MainActivity, TableActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        })

        btnProtein.setOnClickListener(View.OnClickListener { view ->
            var bundle = Bundle()
            var strTmp = edInputNama.text.toString()
            bundle.putString("tesText",strTmp)

            var intent = Intent(this@MainActivity, ProteinTrakerActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        })
    }
}