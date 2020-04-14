package com.example.vggananesh.resume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    EditText et1,et2;
    Button bt;
    private FirebaseAuth au=FirebaseAuth.getInstance();
    String s,s1;
    String email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         tv1=findViewById(R.id.tv1);
         et1=findViewById(R.id.et1);
         et2=findViewById(R.id.et2);
         bt=findViewById(R.id.bt);
        // au=FirebaseAuth.getInstance();
         s=et1.getText().toString().trim();
         s1=et2.getText().toString().trim();
         bt.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 loginUserAccount();

             }
         });

         tv1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, new_user.class);
                startActivity(i);
             }
         });
    }

    private void loginUserAccount() {


       // String email, password;
        email = et1.getText().toString().trim()+"@gmail.com";
        password = et2.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
            return;
        }

        au.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();
                            Intent i= new Intent(MainActivity.this,your_resume.class);
                            i.putExtra("key",et1.getText().toString().trim());
                            startActivity(i);


                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }


}
