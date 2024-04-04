package com.example.prandriod

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prandriod.Gun
import com.example.prandriod.GunAdapter
import com.example.prandriod.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GunAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Отримуємо список зразків зброї
        val sampleGuns = getSampleGuns()

        // Ініціалізуємо адаптер і встановлюємо його для RecyclerView
        adapter = GunAdapter(sampleGuns)
        recyclerView.adapter = adapter

        // Зберігаємо список зброї в SharedPreferences
        saveGunsToSharedPreferences(sampleGuns)
    }

    private fun getSampleGuns(): List<Gun> {
        return listOf(
            Gun(1, "AK-47", "Powerful assault rifle", 599.99),
            Gun(2, "M16", "Versatile rifle", 699.99),
            Gun(3, "Desert Eagle", "Handgun with high stopping power", 899.99),
            Gun(4, "Glock 17", "Reliable pistol", 499.99),
            Gun(5, "MP5", "Compact submachine gun", 799.99),
            Gun(6, "Barrett M82", "Heavy sniper rifle", 1499.99),
            Gun(7, "FN SCAR", "Assault rifle with modular design", 899.99),
            Gun(8, "Beretta 92", "Classic handgun", 599.99),
            Gun(9, "UMP45", "Close-quarters submachine gun", 649.99),
            Gun(10, "Remington 870", "Pump-action shotgun", 799.99)
            // Додайте інші товари за потреби
        )
    }

    private fun saveGunsToSharedPreferences(guns: List<Gun>) {
        val sharedPreferences = getSharedPreferences("shop_guns_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(guns)
        editor.putString("guns", json)
        editor.apply()
    }
}
