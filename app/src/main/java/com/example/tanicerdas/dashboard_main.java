package com.example.tanicerdas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageButton;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class dashboard_main extends AppCompatActivity implements View.OnClickListener{
    private ImageView iconBack;

    private TextView DataSuhu;
    //id suhu lingkungan
    private TextView DataHum;
    //id kelemababan lingkungan
    private TextView DataPH;
    //id ph
    private TextView DataAIR;

    ImageButton RelayOn;
    ImageButton RelayOff;

    //id kelembaban tanah
    private Firebase mRef;
    //firebase agar mendapatkan data suhu dari Firebase
    private  Firebase mTwo;
    //firebase agar mendapatkan data kelembaban dari Firebase
    private Firebase mThree;
    //firebase agar mendapatkan data kelembaban Tanah dari Firebase
    private Firebase mFour;
    //firebase agar mendapatkan data PhTanah dari Firebase
    private Firebase mBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_main);

        //Menghubungkan Mobile APP dengan Firebase
        DataSuhu=(TextView) findViewById(R.id.DataSuhu);
        mRef = new Firebase("https://fir-3f95b-default-rtdb.firebaseio.com/DHT/Temp");
        DataAIR=(TextView) findViewById(R.id.DataAIR);
        mTwo = new Firebase("https://fir-3f95b-default-rtdb.firebaseio.com/DHT/Hum");
        DataHum=(TextView) findViewById(R.id.DataHum);
        mThree = new Firebase("https://fir-3f95b-default-rtdb.firebaseio.com/KTanah");
        DataPH=(TextView) findViewById(R.id.DataPH);
        mFour = new Firebase("https://fir-3f95b-default-rtdb.firebaseio.com/pH");



        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Temp = dataSnapshot.getValue(String.class);

                //menampilkan suhu pada mobile app
                DataSuhu.setText(Temp);


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mTwo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Hum = dataSnapshot.getValue(String.class);

                //Menampilkan kelembaban di mobile app
                DataAIR.setText(Hum);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mThree.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String KTanah = dataSnapshot.getValue(String.class);

                //Menampilkan kelembaban tanah di mobile app
                DataHum.setText(KTanah);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mFour.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String pH = dataSnapshot.getValue(String.class);

                //Menampilkan ph di mobile app
                DataPH.setText(pH);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


            iconBack = (ImageView) findViewById(R.id.iconBack);
            iconBack.setOnClickListener((View.OnClickListener) this);


            RelayOn = (ImageButton) findViewById(R.id.RelayOn);
            RelayOff = (ImageButton) findViewById(R.id.RelayOff);

            RelayOn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference mBtn = database.getReference("https://fir-3f95b-default-rtdb.firebaseio.com/relayA");

                    mBtn.setValue(1);
                }
            });
            RelayOff.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    // Write a message to the database
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference mBtn = database.getReference("https://fir-3f95b-default-rtdb.firebaseio.com/relayA");

                    mBtn.setValue(0);
                }
            });


    }

    @Override
    public  void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.iconBack:
                i = new Intent(this, Sub_Dashboard.class);
                startActivity(i);
                break;
        }
    }
}
