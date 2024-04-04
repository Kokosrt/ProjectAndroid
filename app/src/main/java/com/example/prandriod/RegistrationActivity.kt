package com.example.prandriod

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast


class RegistrationActivity : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 1

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val sharedPreferences = getSharedPreferences(Const.MY_SHARE_PREFERENCE, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        findViewById<Button>(R.id.btn_save).setOnClickListener {
            val name = findViewById<EditText>(R.id.btn_name).text.toString()
            val phone = findViewById<EditText>(R.id.btn_phone).text.toString()
            val dateOfBirth = findViewById<EditText>(R.id.btn_DateOfBirth).text.toString()
            val login = findViewById<EditText>(R.id.btn_login).text.toString()
            val password = findViewById<EditText>(R.id.btn_password).text.toString()
            val confirmPassword = findViewById<EditText>(R.id.et_confirm_password).text.toString()


            if (password == confirmPassword) {

                editor.putString("name", name)
                editor.putString("phone", phone)
                editor.putString("dateOfBirth", dateOfBirth)
                editor.putString(Const.LOGIN, login)
                editor.putString(Const.PASSWORD, password)
                editor.apply()


                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            } else {

                Toast.makeText(this, getString(R.string.pass_error), Toast.LENGTH_SHORT).show()
            }
        }
        val imageView: ImageView = findViewById(R.id.iv_avatar)

        imageView.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            val selectedImageUri = data.data

            findViewById<ImageView>(R.id.iv_avatar).setImageURI(selectedImageUri)
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}