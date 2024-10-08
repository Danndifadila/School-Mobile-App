package com.example.raiso

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Referensikan ImageView berdasarkan ID-nya dan setel onClickListeners
        val buttonGoToNext: ImageView = findViewById(R.id.buttoncal)

        // Tombol Klik untuk Kalkulator
        buttonGoToNext.setOnClickListener {
            // Navigasi ke Aktivitas Kalkulator
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}