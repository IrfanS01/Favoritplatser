package com.example.favoritplatser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {


    lateinit var auth: FirebaseAuth
    lateinit var emailView: EditText
    lateinit var passwordView: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        auth = Firebase.auth
        emailView = findViewById(R.id.emailEditText)
        passwordView = findViewById(R.id.passwordEditText)


        val signUpButton = findViewById<Button>(R.id.signUpButton)
        signUpButton.setOnClickListener {
            signUp()
        }



        val signInButton = findViewById<Button>(R.id.signInButton)
        signInButton.setOnClickListener {
            signIn()
        }

        if (auth.currentUser != null) {
            gotToAddActivity()                     // ako smo vec inlogani da idemo direktno na add activiti


        }


    }


    fun gotToAddActivity() {
        val intent = Intent(this, AddItemActivity::class.java)
        startActivity(intent)
    }


    fun signIn() {


        val email = emailView.text.toString()
        val password = passwordView.text.toString()
        if (email.isEmpty() || password.isEmpty()) {

            return
        }

        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("!!!", "create succes")
                    gotToAddActivity()

                } else {
                    Log.d("!!!", "user not created $ {task.exception}")
                }
            }

    }

    fun signUp() {

        val email = emailView.text.toString()
        val password = passwordView.text.toString()
        if (email.isEmpty() || password.isEmpty()) {

            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("!!!", "signed In")
                    gotToAddActivity()

                } else {
                    Log.d("!!!", "user not signed In $ {task.exception}")
                }
            }
    }
}
















