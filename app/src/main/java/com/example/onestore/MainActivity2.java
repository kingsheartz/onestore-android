package com.example.onestore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity2 extends AppCompatActivity {

    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btn=(Button)findViewById(R.id.button);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.GONE);
        if (isConnectedToInternet()) {
            Intent intent=new Intent(MainActivity2.this,MainActivity.class);
            startActivity(intent);
        }

        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

    }


    boolean isConnectedToInternet() {

        ConnectivityManager cm = (ConnectivityManager)MainActivity2.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();


        return isConnected;

    }
}