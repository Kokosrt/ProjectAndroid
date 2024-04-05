package com.example.prandriod

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val sharedPreferences = getSharedPreferences(Const.MY_SHARE_PREFERENCE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        findViewById<Button>(R.id.btn_logout).setOnClickListener {

        }
        findViewById<Button>(R.id.btn_logout).setOnClickListener{
            val intent = Intent(this, AuthActivity::class.java)
            editor.putString(Const.IS_AUTH, false.toString())
            editor.apply()
            startActivity(intent)
            finish()
        }

        val btCaliber = findViewById<Button>(R.id.btn_caliber)
        val btmenu = findViewById<Button>(R.id.btn_menu)
        btmenu.setOnClickListener {
            startActivity(Intent(this, GunActivity::class.java))
        }
        btCaliber.setOnClickListener {
            startActivity(Intent(this, CaliberActivity::class.java))
        }

    }
}