package com.example.contactapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.contactapp.databinding.ActivitySignInBinding
import com.example.contactapp.databinding.ActivitySignUpBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {

    lateinit var binding : ActivitySignUpBinding
    lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegisterUser.setOnClickListener {
            databaseReference = FirebaseDatabase.getInstance().getReference("Users")
            val name = binding.inputName.text.toString()
            val emailId = binding.inputId.text.toString()
            // made a userData class , couldn't figure out way without class
            val data = userData(name,emailId)

            databaseReference.child(name).setValue(data).addOnSuccessListener {
                binding.inputName.text?.clear()
                binding.inputId.text?.clear()

                Toast.makeText(this, "User Added", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "User Not Added", Toast.LENGTH_SHORT).show()
            }
        }
    }
}