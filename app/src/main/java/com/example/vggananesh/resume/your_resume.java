package com.example.vggananesh.resume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.bumptech.glide.Glide;

public class your_resume extends AppCompatActivity {
    String s1,cobj,a,qual,tech,works,softk,d,perd;
    TextView co,aoi,tc,pd,sk,dec,work,year,q,in,cgpa;
    ImageView iv1;

    FirebaseStorage storage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_your_resume);

        Intent i=getIntent();
        co=findViewById(R.id.co);
        aoi=findViewById(R.id.aoi);
        tc=findViewById(R.id.tc);
        work=findViewById(R.id.work);
        sk=findViewById(R.id.sk);
        dec=findViewById(R.id.dec);
        pd=findViewById(R.id.pd);
        iv1=findViewById(R.id.iv1);
        year=findViewById(R.id.year);
        q=findViewById(R.id.q);
        in=findViewById(R.id.in);
        cgpa=findViewById(R.id.cgpa);



         s1=i.getStringExtra("key");
        Toast.makeText(this,s1, Toast.LENGTH_SHORT).show();


        DatabaseReference database=FirebaseDatabase.getInstance().getReference().child("users");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                cobj=dataSnapshot.child(s1).child("co").getValue().toString();
                co.setText(cobj);
                a=dataSnapshot.child(s1).child("aoi").getValue().toString();
                aoi.setText(a);
                tech=dataSnapshot.child(s1).child("tech").getValue().toString();
                tc.setText(tech);
                works=dataSnapshot.child(s1).child("work").getValue().toString();
                work.setText(works);
                softk=dataSnapshot.child(s1).child("sk").getValue().toString();
                sk.setText(softk);
                d=dataSnapshot.child(s1).child("dec").getValue().toString();
                dec.setText(d);
                perd=dataSnapshot.child(s1).child("pd").getValue().toString();
                pd.setText(perd);
                year.setText(dataSnapshot.child(s1).child("yop").getValue().toString());
                cgpa.setText(dataSnapshot.child(s1).child("cgpa").getValue().toString());
                in.setText(dataSnapshot.child(s1).child("in").getValue().toString());
                q.setText(dataSnapshot.child(s1).child("c").getValue().toString());





            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(your_resume.this, "database error", Toast.LENGTH_SHORT).show();

            }
        });
        //String url="gs://resume-56674.appspot.com/gani/image.jpg";//Retrieved url as mentioned above

        //Glide.with(getApplicationContext()).load(url).into(iv1);

        StorageReference storageRef = storage.getInstance().getReferenceFromUrl("gs://resume-56674.appspot.com");
       // storageRef.child("gani/image.jpg");
        storageRef.child(s1+"/image.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //Toast.makeText(your_resume.this,uri.toString(), Toast.LENGTH_SHORT).show();

               String  imageURL = uri.toString();

                Glide.with(getApplicationContext()).load(imageURL).into(iv1);




                //Bitmap b= BitmapFactory.decodeFile("gs://resume-56674.appspot.com/gani/image.jpg");
                //iv1.setImageBitmap(b);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(your_resume.this, "failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
