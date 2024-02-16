package com.example.favoritplatser

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Dohvaćanje UI komponenata
        val tvItemName: TextView = findViewById(R.id.ItemName)
        val tvItemDescription: TextView = findViewById(R.id.ItemDescription)
        val tvItemLatitude: TextView = findViewById(R.id.ItemLatitude)
        val tvItemLongitude: TextView = findViewById(R.id.ItemLongitude)
        val tvItemCategory: TextView = findViewById(R.id.ItemCategory)

        // Dohvaćanje podataka iz Intent-a
        val itemName = intent.getStringExtra("ITEM_NAME") ?: "N/A"
        val itemDescription = intent.getStringExtra("ITEM_DESCRIPTION") ?: "N/A"
        val itemLatitude = intent.getStringExtra("ITEM_LATITUDE") ?: "N/A"
        val itemLongitude = intent.getStringExtra("ITEM_LONGITUDE") ?: "N/A"
        val itemCategory = intent.getStringExtra("ITEM_CATEGORY") ?: "N/A"

        // Postavljanje podataka na UI komponente
        tvItemName.text = itemName
        tvItemDescription.text = itemDescription
        tvItemLatitude.text = itemLatitude
        tvItemLongitude.text = itemLongitude
        tvItemCategory.text = itemCategory
    }
}
