package com.example.hirec

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.hirec.databinding.ActivityRentuserinfoBinding
import com.google.firebase.auth.FirebaseAuth
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject


class payment : AppCompatActivity(), PaymentResultListener {
    private var backpressdtime: Long = 0
    var c= 0
    val amt1 = 10

    private var firebaseAuth: FirebaseAuth? = null
    private var textView: EditText? = null
    var binding: ActivityRentuserinfoBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rentuserinfo)
        firebaseAuth = FirebaseAuth.getInstance()
        textView = findViewById(R.id.useremail)
        val user = firebaseAuth!!.currentUser
        if (user != null) {
            //textView.setText(user.getEmail());
        }
        binding = ActivityRentuserinfoBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val mail = binding!!.useremail.text.toString()
        binding!!.saveData.setOnClickListener {
            val name = binding!!.stdName.text.toString()
            val cno = binding!!.stdContactno.text.toString()
            val mail = binding!!.useremail.text.toString()
            val amt= 10
            saveData(name, cno, amt)
            //finish()

                   // makepayment1()


        }
        binding!!.saveData25.setOnClickListener {
            val name = binding!!.stdName.text.toString()
            val cno = binding!!.stdContactno.text.toString()
            //val mail = binding!!.useremail.text.toString()
            val amt = 25
            saveData(name, cno, amt)

               // makepayment25()

        }
        binding!!.saveData50.setOnClickListener {
            val name = binding!!.stdName.text.toString()
            val cno = binding!!.stdContactno.text.toString()
            //val mail = binding!!.useremail.text.toString()
            val amt = 50
            saveData(name, cno, amt)

              //  makepayment50()

        }
    }
    private fun addDelay() {
        val handler = Handler()
        handler.postDelayed({
            // Your code to run after the delay
        }, 1000) // Delay for 1000 milliseconds (1 second)
    }

    private fun makepayment1() {
        //val activity:payment = this
        val co = Checkout()
        try {
            val options = JSONObject()
            options.put("name", "HIREC")
            options.put("description", "Rent E-cycle")
            //You can omit the image option to fetch the image from the dashboard
            //options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            //options.put("order_id", "HIREC_rent10");
            options.put("amount", "1000")//pass amount in currency subunits


            val prefill = JSONObject()
            prefill.put("email", "hirec@rajalakshmi.edu.in")
            prefill.put("contact","")

            options.put("prefill", prefill)
            co.open(this, options)
        } catch (e: Exception) {
            Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }

    }

    private fun makepayment25() {
        //val activity:payment = this
        val co = Checkout()
        try {
            val options = JSONObject()
            options.put("name", "HIREC")
            options.put("description", "Rent E-cycle")
            //You can omit the image option to fetch the image from the dashboard
            //options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            //options.put("order_id", "HIREC_rent25");
            options.put("amount", "2500")//pass amount in currency subunits


            val prefill = JSONObject()
            prefill.put("email", "hirec@rajalakshmi.edu.in")
            prefill.put("contact", "")

            options.put("prefill", prefill)
            co.open(this, options)
        } catch (e: Exception) {
            Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    private fun makepayment50() {
        //val activity:payment = this
        val co = Checkout()
        try {
            val options = JSONObject()
            options.put("name", "HIREC")
            options.put("description", "Rent E-cycle")
            //You can omit the image option to fetch the image from the dashboard
            //options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            //options.put("order_id", "HIREC_rent50");
            options.put("amount", "5000")//pass amount in currency subunits


            val prefill = JSONObject()
            prefill.put("email", "hirec@rajalakshmi.edu.in")
            prefill.put("contact", "")

            options.put("prefill", prefill)
            co.open(this, options)
        } catch (e: Exception) {
            Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()


        }

    }

    //class Checkout {

    //}

    private fun saveData(name: String?, cno: String?, amt: Int): Boolean {
        if (name != null && cno != null) {
            if (Patterns.PHONE.matcher(cno).matches() && cno.length == 10|| cno.length ==13) {

                //String url = "https://script.google.com/macros/s/AKfycbyW-shFmtJn264qudb29IhrVyzx_Is_Ww6JJgkztDhfUKjq6xD-0qGl7cL8-LIB19Q/exec";
                //var url = "https://script.google.com/macros/s/AKfycbxxBXswezbp-fdeCFELbMbX50RWjA45PkPhDRF84wpZPPkAT6SV6N2Wz4eyIMocIz8/exec?"
                var url ="https://script.google.com/macros/s/AKfycbzcSDd_0pxBJPicS9bvWQMM_siAsHPDMilfHoJZh7l7_gXIhfRlzPuz4T4RZ_sw90g/exec?"
                //https://script.google.com/macros/s/AKfycbxxBXswezbp-fdeCFELbMbX50RWjA45PkPhDRF84wpZPPkAT6SV6N2Wz4eyIMocIz8/exec
                //https://script.google.com/macros/s/AKfycbxxBXswezbp-fdeCFELbMbX50RWjA45PkPhDRF84wpZPPkAT6SV6N2Wz4eyIMocIz8/exec?action=create&name=dfghjkl&cno=12345678
                url = url + "action=create&name=" + name +  "&amt=" + amt + "&cno=" + cno
                Toast.makeText(this@payment,"Wait for the user to be added", Toast.LENGTH_SHORT).show()
                //StringReader StringRequest= new StringReader(Request)
                val StringRequest = StringRequest(
                    Request.Method.GET, url,
                    { response ->
                        //Toast.makeText(rentuserinfo.this,response,Toast.LENGTH_SHORT).show();
                        Toast.makeText(this@payment, response, Toast.LENGTH_SHORT).show()

                        Toast.makeText(this@payment,"Choose your raiding plan", Toast.LENGTH_LONG,). show()


                        binding!!.saveData.setOnClickListener {

                                 makepayment1()

                            //var url ="https://script.google.com/macros/s/AKfycbxp6Qo0VcrmxS1DVx-D3UQP0cazG1OtBmeIKrUZs-17Lngh8Ls7pjPf_s3Aa5YoLnk/exec"
                              //url = url + "action=create&amt=" + 10
                            }
                        binding!!.saveData25.setOnClickListener {
                            makepayment25()
                            //finish()

                        }
                        binding!!.saveData50.setOnClickListener {
                            makepayment50()


                        }
                        //finish()

                        //Intent rentuserinfoIntent;
                        //rentuserinfoIntent = new Intent(rentuserinfo.this, paymentactivity.class);
                        //startActivity(rentuserinfoIntent);
                    }
                ) { error ->
                    Toast.makeText(this@payment, error.message, Toast.LENGTH_SHORT).show()
                }
                val Queue = Volley.newRequestQueue(this)
                Queue.add(StringRequest)
            } else {
                Toast.makeText(this@payment, "enter a valid phone no", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(this@payment, "please fill the details", Toast.LENGTH_SHORT).show()
        }

        return true

    }

    override fun onBackPressed() {
        if (backpressdtime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed()
            return
        } else {
            val rentuserinfoIntent: Intent
            rentuserinfoIntent = Intent(this@payment, homefragment::class.java)
            startActivity(rentuserinfoIntent)
            Toast.makeText(
                this@payment,
                "press once again to exit the app",
                Toast.LENGTH_SHORT
            ).show()
        }
        backpressdtime = System.currentTimeMillis()
    }


    override fun onPaymentSuccess(p0: String?) {


        Toast.makeText(
            this@payment,
            "payment success $p0",
            Toast.LENGTH_SHORT
        ).show()

        addDelay()

        startActivity(Intent(this, paymentactivity::class.java))
        finish()
    }

   /* private fun updateData(status: String?) {
        var url ="https://script.google.com/macros/s/AKfycbzcSDd_0pxBJPicS9bvWQMM_siAsHPDMilfHoJZh7l7_gXIhfRlzPuz4T4RZ_sw90g/exec?"
        url = url + "action=create&status=" + status
    }*/

    override fun onPaymentError(p0: Int, p1: String?) {
        val rentuserinfoIntent: Intent
        rentuserinfoIntent = Intent(this@payment, payment::class.java)
        startActivity(rentuserinfoIntent)
        Toast.makeText(
            this@payment,
            "payment failure $p1",
            Toast.LENGTH_SHORT
        ).show()

    }


}