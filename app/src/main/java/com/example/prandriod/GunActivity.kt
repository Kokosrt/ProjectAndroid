package com.example.prandriod

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class GunActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GunAdapter
    private lateinit var addButton: Button
    private lateinit var sampleGuns: MutableList<Gun>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gun)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Отримуємо список зразків зброї
        sampleGuns = getSampleGuns()

        // Ініціалізуємо адаптер і встановлюємо його для RecyclerView
        adapter = GunAdapter(sampleGuns){position->
            showUpdateDialog(position = position, gun = sampleGuns[position] )
        }
        recyclerView.adapter = adapter

        // Зберігаємо список зброї в SharedPreferences
        saveGunsToSharedPreferences(sampleGuns)

        addButton = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            // Створіть діалогове вікно для додавання нового елемента
            val dialog = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.add_gun_dialog, null)

            // Отримайте посилання на елементи керування в діалоговому вікні
            val nameEditText = dialogView.findViewById<EditText>(R.id.nameEditText)
            val descriptionEditText = dialogView.findViewById<EditText>(R.id.descriptionEditText)
            val priceEditText = dialogView.findViewById<EditText>(R.id.priceEditText)

            // Додайте кнопку "Додати" для збереження нового елемента
            dialog.setPositiveButton("Додати") { _, _ ->
                // Отримайте значення з полів вводу
                val name = nameEditText.text.toString()
                val description = descriptionEditText.text.toString()
                val price = priceEditText.text.toString().toDoubleOrNull()

                // Перевірте, чи всі дані були введені коректно
                if (name.isNotEmpty() && description.isNotEmpty() && price != null) {
                    // Створіть новий об'єкт зброї
                    val newGun = Gun(sampleGuns.size + 1, name, description, price)
                    // Додайте нову зброю до списку
                    sampleGuns.add(newGun)
                    // Оновіть відображення списку
                    adapter.notifyItemInserted(sampleGuns.size - 1)
                    // Збережіть оновлений список в SharedPreferences або іншому місці для зберігання даних
                    saveGunsToSharedPreferences(sampleGuns)
                } else {
                    // Повідомте користувача, що необхідно ввести всі дані
                    Toast.makeText(this, "Будь ласка, введіть всі дані", Toast.LENGTH_SHORT).show()
                }
            }

            // Додайте кнопку "Відміна" для скасування операції додавання
            dialog.setNegativeButton("Відміна", null)

            // Показати діалогове вікно
            dialog.setView(dialogView)
            dialog.show()
        }

    }

    private fun getSampleGuns(): MutableList<Gun> {
        return mutableListOf(
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

    private fun saveGunsToSharedPreferences(guns: MutableList<Gun>) {
        val sharedPreferences = getSharedPreferences("shop_guns_prefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(guns)
        editor.putString("guns", json)
        editor.apply()
    }
    private fun showUpdateDialog(position: Int, gun: Gun) {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val dialogView = inflater.inflate(R.layout.add_gun_dialog, null)

        val nameEditText = dialogView.findViewById<EditText>(R.id.nameEditText)
        val descriptionEditText = dialogView.findViewById<EditText>(R.id.descriptionEditText)
        val priceEditText = dialogView.findViewById<EditText>(R.id.priceEditText)

        // Встановлюємо існуючі значення в поля для вводу
        nameEditText.setText(gun.name)
        descriptionEditText.setText(gun.description)
        priceEditText.setText(gun.price.toString())

        builder.setView(dialogView)
            .setTitle("Редагувати")
            .setPositiveButton("Змінити") { dialog, which ->
                // Отримуємо оновлені дані з полів вводу
                val name = nameEditText.text.toString()
                val description = descriptionEditText.text.toString()
                val price = priceEditText.text.toString().toDoubleOrNull()

                // Перевіряємо, чи вдалося отримати ціну
                if (price != null) {
                    // Оновлюємо елемент у списку
                    val updatedGun = Gun(gun.id, name, description, price)
                    sampleGuns[position] = updatedGun
                    adapter.notifyItemChanged(position)
                    // Зберігаємо оновлений список в SharedPreferences або іншому місці для зберігання даних
                    saveGunsToSharedPreferences(sampleGuns)
                } else {
                    // Повідомте користувача, що ціна введена неправильно
                    Toast.makeText(this, "Введіть коректну ціну", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Скасувати") { dialog, which ->
                // Відміна оновлення
                dialog.cancel()
            }
            .show()
    }

}
