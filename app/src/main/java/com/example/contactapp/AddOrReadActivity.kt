package com.example.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.contactapp.databinding.ActivityAddOrReadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddOrReadActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAddOrReadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddOrReadBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.addNewUser.setOnClickListener{

            val name = binding.newContact.text.toString()
            val phoneNumber = binding.newContactNumber.text.toString()

            val newContactId = userContactInfo(name , phoneNumber)
            val userPath = intent.getStringExtra("name").toString()
            
            writeData( name , newContactId , userPath)
        }
    }

    private fun writeData( name : String , newContactId: userContactInfo , userPath : String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users/$userPath/Contacts")

        databaseReference.child(name).setValue(newContactId).addOnSuccessListener {
            
            Toast.makeText(this,"New Contact Added" , Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
        }
    }
}