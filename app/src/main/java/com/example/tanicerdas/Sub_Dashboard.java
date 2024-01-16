package com.example.tanicerdas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Sub_Dashboard extends AppCompatActivity implements View.OnClickListener{
    private CardView Card1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_dashboard);

        Card1 = (CardView) findViewById(R.id.Card1);
                Card1.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public  void onClick(View v){
        Intent i;
        switch (v.getId()){
            case R.id.Card1 : i = new Intent(this,dashboard_main.class); startActivity(i); break;
        }


    }

}