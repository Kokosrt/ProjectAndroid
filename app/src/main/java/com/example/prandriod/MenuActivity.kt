package com.example.prandriod

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MenuActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        findViewById<Button>(R.id.btn_logout).setOnClickListener {

        }


        val btCaliber = findViewById<Button>(R.id.btn_caliber)
        val btmenu = findViewById<Button>(R.id.btn_menu)
        btmenu.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        btCaliber.setOnClickListener {
            startActivity(Intent(this, CaliberActivity::class.java))
        }


        findViewById<Button>(R.id.btn_logout).setOnClickListener {
            startActivity(Intent(this, AuthActivity::class.java))
        }
    }
}