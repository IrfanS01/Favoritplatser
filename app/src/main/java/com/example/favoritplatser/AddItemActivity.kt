package com.example.favoritplatser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.PropertyName
import com.google.firebase.firestore.firestore

class AddItemActivity : AppCompatActivity() {


    lateinit var db: FirebaseFirestore
    lateinit var nameView: EditText
    lateinit var auth: FirebaseAuth

    // Definicija varijabli za UI elemente
    lateinit var descriptionView: EditText
    lateinit var latitudeView: EditText
    lateinit var longitudeView: EditText
    lateinit var categorySpinner: Spinner
    lateinit var saveButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item2)



        auth = Firebase.auth
        db = Firebase.firestore

        // Inicijalizacija UI elemenata
        nameView = findViewById(R.id.itemNameView)
        descriptionView = findViewById(R.id.itemDescriptionView)
        latitudeView = findViewById(R.id.itemLatitudeView) // Ispravno koristi ID iz layouta
        longitudeView = findViewById(R.id.tvItemLongitudeView) // Ispravno koristi ID iz layouta
        categorySpinner = findViewById(R.id.itemCategorySpinner)
        saveButton = findViewById(R.id.button2)

        // Postavljanje CustomSpinnerAdapter-a na categorySpinner
        val myAdapter = CustomSpinnerAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listOf("Kategorija 1", "Kategorija 2", "Kategorija 3") // Dodajte stvarne kategorije
        )
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = myAdapter


        val button = findViewById<Button>(R.id.button2)
        button.setOnClickListener {
            saveItem()
        }
    }

    fun saveItem() {
        //  val item = Item(name=nameView.text.toString())   // ovo prvo name smo stavili zato sto dokumentid dole ispod
        //nameView.setText("")

        val name = nameView.text.toString()
        val description = descriptionView.text.toString()
        val latitude = latitudeView.text.toString().toDoubleOrNull()
        val longitude = longitudeView.text.toString().toDoubleOrNull()
        val category = categorySpinner.selectedItem.toString() // Primjer, prilagodite prema vašem Spinner adapteru


        if (name.isBlank() || description.isBlank() || latitude == null || longitude == null) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            return

        }


        val user = auth.currentUser
        if (user != null) {
            val item = Item(
                name = name,
                description = description,
                latitude = latitude,
                longitude = longitude,
                category = category,
                done = false // ili drugu početnu vrijednost koju želite
            )
            // Spremite 'item' u Firestore pod kolekcijom 'items' trenutnog korisnika
            db.collection("users").document(user.uid).collection("items").add(item)
                .addOnSuccessListener { documentReference ->
                    // Ovdje ažuriramo dodani 'item' s njegovim generisanim documentId
                    val itemId = documentReference.id // Dohvatimo ID novokreiranog dokumenta
                    db.collection("users").document(user.uid).collection("items").document(itemId)
                        .update("documentId", itemId) // Ažuriramo 'item' s njegovim ID
                        .addOnSuccessListener {
                            finish()

                            Toast.makeText(this, "Item added successfully and documentId updated.", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            // Obrada grešaka prilikom ažuriranja documentId
                            Toast.makeText(this, "Error updating item with documentId: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error adding item: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } else {
            Toast.makeText(this, "User not signed in", Toast.LENGTH_SHORT).show()
        }

    }

}


data class Item(
    @PropertyName("documentId") var documentId: String? = null,
    @PropertyName("name") var name: String? = null,
    @PropertyName("description") var description: String? = null,
    @PropertyName("latitude") var latitude: Double? = null,
    @PropertyName("longitude") var longitude: Double? = null,
    @PropertyName("category") var category: String? = null,
    @PropertyName("done") var done: Boolean = false
)

