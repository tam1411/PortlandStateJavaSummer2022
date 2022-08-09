package edu.pdx.cs410J.tn27;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

     public void LaunchREADME ( View view){
        Intent intent = new Intent(this, README.class);
        startActivity(intent);
    }

    public void Launch_Menu(View view){
        Intent intent = new Intent(this, LaunchMenu.class);
        startActivity(intent);
    }
}