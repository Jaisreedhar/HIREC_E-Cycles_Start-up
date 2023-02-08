package com.example.hirec

import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class wpage : AppCompatActivity() {
    private var backpresstime = 0L
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    lateinit var frameLayout: FrameLayout
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var textView: TextView
    private lateinit var emailview: TextView





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wpage)
        firebaseAuth = FirebaseAuth.getInstance()
        textView = findViewById(R.id.email_text_view)
        emailview = findViewById(R.id.email_view)


        firebaseAuth.currentUser?.let {
            textView.text = it.email
            emailview.text=it.email
        }
        val buttonrent: Button= findViewById(R.id.rentcycle)
        buttonrent.setOnClickListener {
            val rentfrag = rentfragment()
            val fragment :Fragment?=
            supportFragmentManager.findFragmentByTag(rentfragment::class.java.simpleName)
            if(fragment !is rentfragment){
                supportFragmentManager.beginTransaction()
                    .add(R.id.framelayout,rentfrag,rentfragment::class.java.simpleName)
                    .commit()
                     drawerLayout.closeDrawers()
            }
        }



        drawerLayout = findViewById(R.id.drawerLayout)
        val navView:NavigationView=findViewById(R.id.nav_view)
        toggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener {
            it.isChecked=true
            when(it.itemId){
                //R.id.nav_home->Toast.makeText(applicationContext,"clicked Home",Toast.LENGTH_SHORT).show()
                R.id.nav_home->replaceFragment(homefragment(),it.title.toString())
                //R.id.nav_rent->Toast.makeText(applicationContext,"clicked rent",Toast.LENGTH_SHORT).show()
                R.id.nav_rent->replaceFragment(rentfragment(),it.title.toString())
                //R.id.nav_buy->Toast.makeText(applicationContext,"clicked buy",Toast.LENGTH_SHORT).show()
                R.id.nav_buy->replaceFragment(buyfragment(),it.title.toString())
                //R.id.nav_gallery->Toast.makeText(applicationContext,"clicked gallery",Toast.LENGTH_SHORT).show()
                R.id.nav_gallery->replaceFragment(galleryfragment(),it.title.toString())
                //R.id.nav_logout->Toast.makeText(applicationContext,"clicked logout",Toast.LENGTH_SHORT).show()
                R.id.nav_logout->replaceFragment(logoutfragment(),it.title.toString())
                //R.id.nav_contact->Toast.makeText(applicationContext,"clicked contact us",Toast.LENGTH_SHORT).show()
                R.id.nav_contact->replaceFragment(contactfragment(),it.title.toString())
               R.id.nav_share-> Toast.makeText(applicationContext,"share US",Toast.LENGTH_LONG).show()
                //R.id.nav_share->replaceFragment(sharefragment(),it.title.toString())
                R.id.nav_about->replaceFragment(infofragment(),it.title.toString())
                //R.id.nav_contact->

            }
            true
        }

    }
    private fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.framelayout,fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)

    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }



    override fun onBackPressed() {

        if (backpresstime+ 2000 > System.currentTimeMillis()){

            super.onBackPressed()
        }else{

                //startActivity(Intent(this, homeactivity::class.java))

            /*val homefrag = homefragment()
           // val fragment :Fragment?=
                supportFragmentManager.findFragmentByTag(homefragment::class.java.simpleName)
           // if(fragment !is homefragment){
                supportFragmentManager.beginTransaction()
                    .add(R.id.drawerLayout,homefrag,homefragment::class.java.simpleName)
                    .commit()
           // }*/

                Toast.makeText(
                    applicationContext,
                    "press back again to exit the app",
                    Toast.LENGTH_SHORT
                ).show()



           // finish()





        }
        backpresstime = System.currentTimeMillis()
    }



}


