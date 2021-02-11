package com.example.uas_dzakiam201804016

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnLogout: Button = findViewById(R.id.btnLogout)
        val savedLogin = getSharedPreferences("Login", MODE_PRIVATE)
        val editSavedLogin = savedLogin.edit()
        val txtmouse: TextView = findViewById(R.id.txtmouse)
        btnLogout.setOnClickListener {
            editSavedLogin.putString("Email", null)
            editSavedLogin.putString("Password", null)
            editSavedLogin.putString("Status", "Off")
            editSavedLogin.commit()
            startActivity(Intent(this, LoginActivity::class.java))
        }
        txtmouse.setOnClickListener {
            val intent = Intent(this, MainMouse::class.java)
            startActivity(intent)
        }
    }
}