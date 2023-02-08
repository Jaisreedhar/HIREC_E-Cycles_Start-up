package com.example.hirec

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hirec.databinding.ActivityLogoutaactivityBinding
import com.google.firebase.auth.FirebaseAuth

class logoutaactivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogoutaactivityBinding
    private lateinit var user:FirebaseAuth

    private lateinit var textView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLogoutaactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        user = FirebaseAuth.getInstance()
        textView = findViewById(R.id.email_text_view)

        user.currentUser?.let {
            textView.text = it.email
        }
        user = FirebaseAuth.getInstance()
        //binding.tvuseremail.text= String(email)
        if (user.currentUser!= null){
            user.currentUser?.let {
               // binding.tvuseremail.text=it.email
            }
        }
        binding.btnlogout.setOnClickListener {
            user.signOut()
            startActivity(
                Intent(this,MainActivity::class.java)
            )
            finish()
        }

        setContentView(R.layout.activity_logoutaactivity)
        val btnlogout:Button= findViewById(R.id.btnlogout)
        val btnopenfragment: Button = findViewById(R.id.backtohome)
        user = FirebaseAuth.getInstance()
        if (user.currentUser!= null){
            user.currentUser?.let {
               // binding.tvuseremail.text= it.email
            }
        }
        user.currentUser?.let {
            textView.text = it.email
        }
        btnlogout.setOnClickListener {
            user.signOut()
            startActivity(
                Intent(this,MainActivity::class.java)
            )
            finish()
        }
        btnopenfragment.setOnClickListener {
            val homefragment = homefragment()
            val fragment: Fragment? =
                supportFragmentManager.findFragmentByTag(homefragment::class.java.simpleName)
            if (fragment !is homefragment) {
                supportFragmentManager.beginTransaction()
                    .add(R.id.homefragment2, homefragment, homefragment::class.java.simpleName)
                    .commit()
            }
            btnopenfragment.visibility= View.GONE
        }

    }
}