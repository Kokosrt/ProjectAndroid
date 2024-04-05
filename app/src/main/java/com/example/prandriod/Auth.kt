package com.example.prandriod

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class AuthActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        val sharedPreferences = getSharedPreferences(Const.MY_SHARE_PREFERENCE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        findViewById<Button>(R.id.btn_authorisation).setOnClickListener {
            val login = findViewById<EditText>(R.id.btn_login).text.toString()
            val password = findViewById<EditText>(R.id.btn_password).text.toString()
            val savedLogin = sharedPreferences.getString(Const.LOGIN, "")
            val savedPassword = sharedPreferences.getString(Const.PASSWORD, "")

            if (login == savedLogin && password == savedPassword) {
                val intent = Intent(this, MenuActivity::class.java)
                editor.putString(Const.IS_AUTH, true.toString())
                editor.apply()
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, getString(R.string.log_pass_error), Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<Button>(R.id.btn_registration).setOnClickListener {
            startActivity(Intent(this,RegistrationActivity::class.java))
        }
    }
}