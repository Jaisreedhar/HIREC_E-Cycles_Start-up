package com.example.hirec

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hirec.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding
    private lateinit var user: FirebaseAuth

    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        user = FirebaseAuth.getInstance()
        checkifuserislogged()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()


        googleSignInClient= GoogleSignIn.getClient(this, gso)

       findViewById<Button>(R.id.forgetpass).setOnClickListener {
           //signingoogle()
           startActivity(Intent(this, resetpassword::class.java))
           finish()
        }

        binding.btnlogin.setOnClickListener()
        {
            registeruser()
        }

    }
    /*private fun signingoogle(){
        val signInIntent= googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }
    //private val launcher= registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            handleresults(task)

        }
    }

    //private fun handleresults(task: Task<GoogleSignInAccount>) {
        if(task.isSuccessful){
            val account: GoogleSignInAccount? = task.result
            if(account != null ){
                updateUI(account)
            }
        }else{
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount)
    {
        val credential = GoogleAuthProvider.getCredential(account.idToken , null)
        user.signInWithCredential(credential).addOnCompleteListener {
            if(it.isSuccessful)
            {
                val intent: Intent= Intent(this, wpage::class.java)
                intent.putExtra("name", account.displayName)
                startActivity(Intent(this, wpage::class.java))
                finish()


            }else{
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }*/




    private fun checkifuserislogged() {
        if (user.currentUser != null) {

            startActivity(Intent(this, wpage::class.java))
            finish()
        }
    }
        private fun registeruser() {
            val email = binding.etEmail.text.toString()
            val password = binding.etpassword.text.toString()
            //val name = binding.etname.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                user.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(MainActivity()) { task ->

                        if (task.isSuccessful) {
                            user.currentUser?.sendEmailVerification()
                                ?.addOnCompleteListener {
                                    Toast.makeText(this, "please verify profile using email", Toast.LENGTH_LONG).show()


                                }
                                ?.addOnFailureListener {
                                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                                }
                            val verification = user.currentUser?.isEmailVerified
                            if (verification == true) {
                                Toast.makeText(this, "user added successfully", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, wpage::class.java))
                                finish()
                            }else{
                                Toast.makeText(this, "verify your identity ", Toast.LENGTH_SHORT).show()
                                Toast.makeText(this, "please check your Email", Toast.LENGTH_LONG).show()
                            }






                        } else {
                            user.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener { mtask ->
                                    if (mtask.isSuccessful) {
                                        val verification = user.currentUser?.isEmailVerified
                                        if (verification == true) {
                                            startActivity(Intent(this, wpage::class.java))
                                            finish()
                                        }else{
                                            Toast.makeText(this, "verify your identity", Toast.LENGTH_LONG).show()
                                        }

                                    } else {
                                        Toast.makeText(
                                            this,
                                            task.exception!!.message,
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                }

                        }

                    }

            } else {
                Toast.makeText(this, "this fields cannot be empty", Toast.LENGTH_LONG).show()
            }
        }


    }





