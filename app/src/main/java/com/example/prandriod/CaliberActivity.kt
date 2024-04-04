package com.example.prandriod

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.prandriod.CaliberActivity
import com.example.prandriod.CaliberAdapter
import com.example.prandriod.R
import com.google.gson.Gson

class CaliberActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CaliberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caliber)

        recyclerView = findViewById(R.id.caliberRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)


        // Отримуємо список зразків калібру
        val sampleCalibers = getSampleCalibers()

        // Ініціалізуємо адаптер і встановлюємо його для RecyclerView
        adapter = CaliberAdapter(sampleCalibers)
        recyclerView.adapter = adapter

        // Зберігаємо список калібру в SharedPreferences
        saveCalibersToSharedPreferences(sampleCalibers)
    }

    private fun getSampleCalibers(): List<Caliber> {
        return listOf(
            Caliber(1, "9mm", 10.99),
            Caliber(2, ".45 ACP", 15.99),
            Caliber(3, "5.56mm NATO", 20.99),
            Caliber(4, "7.62mm NATO", 25.99),
            Caliber(5, "12 gauge", 30.99),
            Caliber(6, "20 gauge", 35.99),
            Caliber(7, ".22 LR", 5.99),
            Caliber(8, ".308 Winchester", 40.99),
            Caliber(9, ".38 Special", 12.99),
            Caliber(10, ".44 Magnum", 18.99)
            // Додайте інші калібри за потреби
        )
    }

    private fun saveCalibersToSharedPreferences(calibers: List<Caliber>) {
        val sharedPreferences = getSharedPreferences("shop_calibers_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(calibers)
        editor.putString("calibers", json)
        editor.apply()
    }
}
