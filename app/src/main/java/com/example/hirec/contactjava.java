package com.example.hirec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class contactjava extends AppCompatActivity {
    private long backpressdtime;
    EditText email;
    EditText subject;
    EditText message;
    Button sendbtn;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactactivity);
        email=findViewById(R.id.toEmailAddress);
        subject=findViewById(R.id.subject);
        message=findViewById(R.id.message);
        sendbtn=findViewById(R.id.sendbtn);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!email.getText().toString().isEmpty()&&!subject.getText().toString().isEmpty()&&!message.getText().toString().isEmpty()){

                    Intent intent =new  Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email.getText().toString()});
                    intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                    intent.putExtra(Intent.EXTRA_TEXT, message.getText().toString());
                    intent.setType("message/rfc822");
                    //intent.setData(Uri.parse("mailto!"));
                    if(intent.resolveActivity(getPackageManager())!=null){
                        startActivity(intent);
                    }else{
                        Toast.makeText(contactjava.this,"noo app",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(contactjava.this,"fill",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onBackPressed() {

        if(backpressdtime+2000 > System.currentTimeMillis()){
            super.onBackPressed();
            return;
        }else {
            Intent rentuserinfoIntent;
            rentuserinfoIntent = new Intent(contactjava.this, homefragment.class);
            startActivity(rentuserinfoIntent);
            Toast.makeText(contactjava.this,"press once again to exit the app",Toast.LENGTH_SHORT).show();
        }
        backpressdtime = System.currentTimeMillis();
    }

}