package com.example.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // making a splashscreen
        Handler().postDelayed({

            startActivity(Intent(this , HomeScreenActivity::class.java))
            finish()
        } , 3000)
    }
}