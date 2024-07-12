package com.example.contactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contactapp.databinding.ActivityAddNewContactBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddNewContact : AppCompatActivity() {
    private lateinit var binding : ActivityAddNewContactBinding
    private  lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddNewContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userPath = intent.getStringExtra("name").toString()

        binding.addNewUser.setOnClickListener{

            val name = binding.newContact.text.toString()
            val phoneNumber = binding.newContactNumber.text.toString()

            val newContactId = userContactInfo(name , phoneNumber)
            databaseReference = FirebaseDatabase.getInstance().getReference("Users/$userPath/Contacts")
            writeData( name , newContactId , userPath)
        }
    }

    private fun writeData( name : String , newContactId: userContactInfo , userPath : String) {

        databaseReference.child(name).setValue(newContactId).addOnSuccessListener {

            Toast.makeText(this,"New Contact Added" , Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
        }
    }
}