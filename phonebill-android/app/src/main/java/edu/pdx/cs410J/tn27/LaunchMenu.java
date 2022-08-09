package edu.pdx.cs410J.tn27;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Class to launch the menu interface
 */
public class LaunchMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_menu);
    }

    public void LaunchCreatePhoneBill(View view){
        Intent intent = new Intent(this, LaunchCreateNewPhoneBill.class);
        startActivity(intent);
    }


}