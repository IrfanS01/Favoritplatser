package com.example.favoritplatser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.ContentView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class AddItemActivity : AppCompatActivity() {


lateinit var db : FirebaseFirestore
lateinit var nameView : EditText
lateinit var auth : FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item2)

        auth = Firebase.auth

        db = Firebase.firestore
        nameView = findViewById(R.id.itemNameView)

        val button = findViewById<Button>(R.id.button2)
        button.setOnClickListener {
            saveItem()
        }
    }

    fun saveItem() {
        val item = Item(name=nameView.text.toString())   // ovo prvo name smo stavili zato sto dokumentid dole ispod
        nameView.setText("")


        val user = auth.currentUser
        if (user == null) {
            return
        }

        db.collection("users").document(user.uid).collection("items").add(item)
    }

}


data class Item (@DocumentId var documentId: String? = null,
    var name : String? = null,
            var done : Boolean = false)


