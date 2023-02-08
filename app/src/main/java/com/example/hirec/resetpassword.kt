package com.example.hirec

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

private lateinit var etEmail: EditText
private lateinit var btnresetpass: Button
private var backpresstime = 0L
private lateinit var auth: FirebaseAuth

class resetpassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resetpassword)
        etEmail= findViewById(R.id.ch_email)
        btnresetpass = findViewById(R.id.reset)
        auth = FirebaseAuth.getInstance()
        if (etEmail!=null) {
            btnresetpass.setOnClickListener {
                val semail = etEmail.text.toString()
                auth.sendPasswordResetEmail(semail)
                    .addOnSuccessListener {
                        Toast.makeText(this, "please check your Email", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"You Don't Own an account in HIREC", Toast.LENGTH_SHORT).show()
                    }
            }
        }

    }
    override fun onBackPressed() {
        if (backpresstime+ 2000 > System.currentTimeMillis()){
            super.onBackPressed()
        }else{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            Toast.makeText(this,"press back again to exit the app", Toast.LENGTH_SHORT).show()

        }
        backpresstime = System.currentTimeMillis()
    }
}
