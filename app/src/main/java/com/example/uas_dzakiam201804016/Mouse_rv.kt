package com.example.uas_dzakiam201804016

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Mouse_rv : AppCompatActivity() {
    private lateinit var rv_tampilanku: RecyclerView
    lateinit var mouseDBHelper: DBHelper
    private  var list: ArrayList<DataModelMouse> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mouse_rv)
        rv_tampilanku = findViewById(R.id.rv_tampilkanm)
        rv_tampilanku.setHasFixedSize(true)
        mouseDBHelper = DBHelper(this)
        list.addAll(mouseDBHelper.fullData())
        rv_tampilanku.layoutManager = LinearLayoutManager(this)
        var cardData = Mouse_rvadapter(list)
        rv_tampilanku.adapter = cardData
    }
}