package com.example.uas_dzakiam201804016

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainMouse : AppCompatActivity() {
    lateinit var userDBHelper: DBHelper
    lateinit var inputmkode: EditText
    lateinit var inputmbrand: EditText
    lateinit var inputmtype: EditText
    lateinit var inputmharga: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mouse_main)
        inputmkode = findViewById(R.id.input_mkode)
        inputmbrand = findViewById(R.id.input_mbrand)
        inputmtype = findViewById(R.id.input_mtype)
        inputmharga = findViewById(R.id.input_mharga)
        userDBHelper = DBHelper(this)
    }
    fun addData(v: View){
        var mkodein = inputmkode.text.toString()
        var mbrandin = inputmbrand.text.toString()
        var mtypein = inputmtype.text.toString()
        var mhargain = inputmharga.text.toString()
        userDBHelper.InsertDataMouse(mkodein, mbrandin, mtypein, mhargain)
        inputmkode.setText("")
        inputmbrand.setText("")
        inputmtype.setText("")
        inputmharga.setText("")

    }
    fun showAll(v: View){
        var pindah = Intent(this, Mouse_rv::class.java)
        startActivity(pindah)
    }
}