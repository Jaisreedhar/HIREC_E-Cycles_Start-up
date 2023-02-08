package com.example.hirec

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley

class paymentactivity : AppCompatActivity() {
    private var backpresstime = 0L
    //private var textView: TextView? = null
    //var binding: ActivityPaymentBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        val status = "payment successful"
        saveData(status)

    }
    private fun saveData(status: String?): Boolean {
        var url ="https://script.google.com/macros/s/AKfycbzcSDd_0pxBJPicS9bvWQMM_siAsHPDMilfHoJZh7l7_gXIhfRlzPuz4T4RZ_sw90g/exec?"

         url = url+"action=create&status=" + status
        //StringReader StringRequest= new StringReader(Request)
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { Toast.makeText(this@paymentactivity, "response", Toast.LENGTH_SHORT).show() }
        ) { Toast.makeText(this@paymentactivity, "error.getMessage", Toast.LENGTH_SHORT).show() }

        val queue = Volley.newRequestQueue(this)
        queue.add(stringRequest)
        return true

    }
    override fun onBackPressed() {
        if (backpresstime+ 2000 > System.currentTimeMillis()){
            super.onBackPressed()
        }else{
            startActivity(Intent(this, homefragment::class.java))
            finish()
            Toast.makeText(this,"press back again to exit the app", Toast.LENGTH_SHORT).show()
        }
        backpresstime = System.currentTimeMillis()

    }
}