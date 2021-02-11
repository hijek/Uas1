package com.example.uas_dzakiam201804016

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MouseUpdate : AppCompatActivity() {
    lateinit var mouseDBHelper: DBHelper
    lateinit var inputmkode: EditText
    lateinit var inputmbrand: EditText
    lateinit var inputmtype: EditText
    lateinit var inputmharga: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mouse_update)
        inputmkode = findViewById(R.id.input_mkodeu)
        inputmbrand = findViewById(R.id.input_mbrandu)
        inputmtype = findViewById(R.id.input_mtypeu)
        inputmkode = findViewById(R.id.input_mhargau)
        mouseDBHelper = DBHelper(this)
        val bundle = intent.extras
        if (bundle!=null){
            inputmkode.setText(bundle.getString("emailk"))
            inputmbrand.setText(bundle.getString("usernamek"))
            inputmtype.setText(bundle.getString("passk"))
            inputmharga.setText(bundle.getString("fullnamek"))

        }
    }
    fun updateData(v: View){
        var mkodein = inputmkode.text.toString()
        var mbrandin = inputmbrand.text.toString()
        var mtypein = inputmtype.text.toString()
        var mhargain = inputmharga.text.toString()
        mouseDBHelper.updateData(mkodein, mbrandin, mtypein, mhargain)
        var pindah = Intent(this, Mouse_rv::class.java)
        startActivity(pindah)
    }
    fun cancelData(v: View){
        var pindah = Intent(this, Mouse_rv::class.java)
        startActivity(pindah)
    }
}