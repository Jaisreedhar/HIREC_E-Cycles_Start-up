package com.example.hirec

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.*

val s1 = "hirec"
private const val CAMERA_REQUEST_CODE=101
private var backpresstime = 0L
//private val tvtextview = (R.id.tv_textview)

class rentactivity : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rentactivity)
        setuppermission()
        val scannerView = findViewById<CodeScannerView>(R.id.scanner_view)

        codeScanner = CodeScanner(this, scannerView)

        // Parameters (default values)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG)
                if (it.text.equals(s1,true)){
                    Toast.makeText(this, "Redirecting", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, payment::class.java))
                    finish()
                }
                //Toast.makeText(this, "Scan result: ${it.text}", Toast.LENGTH_LONG).show()

            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
    private fun setuppermission(){
        val permission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.CAMERA)
        if( permission != PackageManager.PERMISSION_GRANTED){
            makerequest()
        }
    }
    private fun makerequest(){
        ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_REQUEST_CODE -> {
                if(grantResults.isEmpty()||grantResults[0] !=PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this,"access camera permission",Toast.LENGTH_SHORT).show()
                }else{
                    //sucess
                }
            }
        }
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

