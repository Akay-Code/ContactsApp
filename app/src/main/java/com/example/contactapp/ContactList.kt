package com.example.contactapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ContactList : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_list)

        val userPath = intent.getStringExtra("name").toString()
        val textView = findViewById<TextView>(R.id.userContactsInfo)

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")

        val userContactList = mutableListOf<userContactInfo>()
        databaseReference.child(userPath).child("Contacts").addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){

                    for(index in snapshot.children){
                        val contact = index.getValue(userContactInfo::class.java)
                        if(contact != null){
                            userContactList.add(contact)
                        }
                    }

                    val size = userContactList.size
                    textView.text = "$size users found"

                    val recyclerViewXML = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.ContactsRecyclerView)
                    recyclerViewXML.layoutManager = LinearLayoutManager(this@ContactList)

                    val myAdapter = MyAdapter(this@ContactList, userContactList)
                    recyclerViewXML.adapter = myAdapter

                    myAdapter.mySetItemClickListener(object :MyAdapter.MyClickListener{
                        override fun onItemClicking(position: Int) {
                        }
                    })
                }
                else{
                    textView.text = getString(R.string.noContacts)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                textView.text = "No internet connection "
            }

        })

        val btnAddUser = findViewById<Button>(R.id.addNewContact)
        btnAddUser.setOnClickListener {
            val intent = Intent(this, AddNewContact::class.java)
            intent.putExtra("name", userPath)
            startActivity(intent)
        }
    }
}