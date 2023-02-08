package com.example.hirec;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import android.widget.EditText;
import android.widget.Toast;


import com.android.volley.toolbox.Volley;
import com.example.hirec.databinding.ActivityRentuserinfoBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class rentuserinfo extends AppCompatActivity {
    private long backpressdtime;
    private FirebaseAuth firebaseAuth;
    private EditText textView;
    ActivityRentuserinfoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rentuserinfo);
        firebaseAuth = FirebaseAuth.getInstance();
        textView = findViewById(R.id.useremail);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            //textView.setText(user.getEmail());
       }
        binding = ActivityRentuserinfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= binding.stdName.getText().toString();
                String cno= binding.stdContactno.getText().toString();
                String mail= binding.useremail.getText().toString();
                saveData(name,cno,mail);
                makepayment1();
            }
        });

        binding.saveData25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= binding.stdName.getText().toString();
                String cno= binding.stdContactno.getText().toString();
                String mail= binding.useremail.getText().toString();
                saveData(name,cno,mail);
            }
        });

        binding.saveData50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= binding.stdName.getText().toString();
                String cno= binding.stdContactno.getText().toString();
                String mail= binding.useremail.getText().toString();
                saveData(name,cno,mail);
            }
        });

    }

    private void makepayment1() {



    }


    private void saveData(String name, String cno, String mail) {
        if(name!=null && cno!=null){
            if(Patterns.PHONE.matcher(cno).matches()&& cno.length()==10) {

                //String url = "https://script.google.com/macros/s/AKfycbyW-shFmtJn264qudb29IhrVyzx_Is_Ww6JJgkztDhfUKjq6xD-0qGl7cL8-LIB19Q/exec";
                String url=        "https://script.google.com/macros/s/AKfycbxxBXswezbp-fdeCFELbMbX50RWjA45PkPhDRF84wpZPPkAT6SV6N2Wz4eyIMocIz8/exec?";
                //https://script.google.com/macros/s/AKfycbxxBXswezbp-fdeCFELbMbX50RWjA45PkPhDRF84wpZPPkAT6SV6N2Wz4eyIMocIz8/exec
                //https://script.google.com/macros/s/AKfycbxxBXswezbp-fdeCFELbMbX50RWjA45PkPhDRF84wpZPPkAT6SV6N2Wz4eyIMocIz8/exec?action=create&name=dfghjkl&cno=12345678
                url = url + "action=create&name=" + name + "&cno=" + cno + "&mail=" + mail ;
                //StringReader StringRequest= new StringReader(Request)
                StringRequest StringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Toast.makeText(rentuserinfo.this,response,Toast.LENGTH_SHORT).show();
                        Toast.makeText(rentuserinfo.this, response, Toast.LENGTH_SHORT).show();
                        //Intent rentuserinfoIntent;
                        //rentuserinfoIntent = new Intent(rentuserinfo.this, paymentactivity.class);
                        //startActivity(rentuserinfoIntent);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(rentuserinfo.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

                RequestQueue Queue = Volley.newRequestQueue(this);
                Queue.add(StringRequest);
            }else{
                Toast.makeText(rentuserinfo.this,"enter a valid phone no",Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(rentuserinfo.this,"please fill the details",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {

        if(backpressdtime+2000 > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Intent rentuserinfoIntent;
            rentuserinfoIntent = new Intent(rentuserinfo.this, homefragment.class);
            startActivity(rentuserinfoIntent);
            Toast.makeText(rentuserinfo.this,"press once again to exit the app",Toast.LENGTH_SHORT).show();
        }
        backpressdtime = System.currentTimeMillis();
    }
}