package com.example.favoritplatser

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ItemsListActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items_list)

        // Inicijalizacija Firebase Auth i Firestore instanci
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()
        val recycleView = findViewById<RecyclerView>(R.id.itemsRecyclerView)
         recycleView.layoutManager = LinearLayoutManager(this)

        // Pokušajte dohvatiti i prikazati stavke
        fetchItems()
        val button = findViewById<Button>(R.id.buttonB)
        button.setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onStart() {
        super.onStart()

        fetchItems()
    }

    override fun onResume() {

        super.onResume()
    fetchItems()
        val recycleView = findViewById<RecyclerView>(R.id.itemsRecyclerView)
        recycleView.adapter?.notifyDataSetChanged()
    }
    private fun fetchItems() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            FirebaseFirestore.getInstance().collection("users").document(user.uid)
                .collection("items")
                .get()
                .addOnSuccessListener { documents ->
                    val itemsList = ArrayList<Item>()
                    for (document in documents) {
                        val item = document.toObject(Item::class.java)
                        itemsList.add(item)
                    }
                    val itemsRecyclerView: RecyclerView = findViewById(R.id.itemsRecyclerView)
                    itemsRecyclerView.adapter = ItemsAdapter(this, itemsList)
                }
                .addOnFailureListener { exception ->
                    Toast.makeText(this, "Error fetching items", Toast.LENGTH_SHORT).show()
                }
        }
    }}
