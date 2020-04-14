package com.example.vggananesh.resume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class new_user extends AppCompatActivity {
    Button bt0,bt;
    EditText e,pass,co,aoi,c,yop,cgpa,in,tech,work,sk,pd,dec;
    private FirebaseAuth au=FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        bt0=findViewById(R.id.bt0);
        bt=findViewById(R.id.bt);
        e=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        co=findViewById(R.id.co);
        aoi=findViewById(R.id.aoi);
        yop=findViewById(R.id.year);
        in=findViewById(R.id.in);
        cgpa=findViewById(R.id.year);
        tech=findViewById(R.id.tech);
        work=findViewById(R.id.work);
        sk=findViewById(R.id.sk);
        pd=findViewById(R.id.pd);
        dec=findViewById(R.id.dec);
        c=findViewById(R.id.c);

        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(new_user.this, certificate.class);
                i.putExtra("key1",e.getText().toString().trim());
                startActivity(i);
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerNewUser();
            }
        });


    }

    private void registerNewUser()
    {


        final String email, password;
        email = e.getText().toString().trim()+"@gmail.com";
        password = pass.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        au.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                            FirebaseDatabase db = FirebaseDatabase.getInstance();
                            DatabaseReference dbref=db.getReference();
                            dbref.child("users").child(e.getText().toString().trim()).child("co").setValue(co.getText().toString().trim());
                            dbref.child("users").child(e.getText().toString().trim()).child("aoi").setValue(aoi.getText().toString().trim());
                            dbref.child("users").child(e.getText().toString().trim()).child("c").setValue(c.getText().toString().trim());
                            dbref.child("users").child(e.getText().toString().trim()).child("yop").setValue(yop.getText().toString().trim());
                            dbref.child("users").child(e.getText().toString().trim()).child("cgpa").setValue(cgpa.getText().toString().trim());
                            dbref.child("users").child(e.getText().toString().trim()).child("in").setValue(in.getText().toString().trim());
                            dbref.child("users").child(e.getText().toString().trim()).child("tech").setValue(tech.getText().toString().trim());
                            dbref.child("users").child(e.getText().toString().trim()).child("work").setValue(work.getText().toString().trim());
                            dbref.child("users").child(e.getText().toString().trim()).child("sk").setValue(sk.getText().toString().trim());
                            dbref.child("users").child(e.getText().toString().trim()).child("pd").setValue(pd.getText().toString().trim());
                            dbref.child("users").child(e.getText().toString().trim()).child("dec").setValue(dec.getText().toString().trim());

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}
