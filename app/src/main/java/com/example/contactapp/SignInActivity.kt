package com.example.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contactapp.databinding.ActivitySignInBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignInBinding
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnScanData.setOnClickListener {
            val inputName = binding.inputName.text.toString()

            if(inputName.isNotEmpty()){
                readData(inputName)
            }
            else{
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(inputName: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(inputName).get().addOnSuccessListener {
            if(it.exists()){
                Toast.makeText(this, "User found", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,AddOrReadActivity::class.java)
                intent.putExtra("name", inputName)

                startActivity(intent)
            }
            else{
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
                Toast.makeText(this, "DataBase Error, check your Internet connection and try again", Toast.LENGTH_SHORT).show()
        }
    }
}