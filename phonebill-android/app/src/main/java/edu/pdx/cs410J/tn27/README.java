package edu.pdx.cs410J.tn27;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class README extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readme);
        txt = findViewById(R.id.Readme);
        txt.setText(readme().toString());
        //Toast.makeText(this, )
    }


    public StringBuilder readme(){
        StringBuilder txt = new StringBuilder();
        try {
            InputStream readme = getResources().openRawResource(R.raw.readme);
            BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
            String line = null;

            while ((line = reader.readLine()) != null) {
                txt.append(line);
                txt.append("\n");
            }

        } catch (IOException e) {
            ReadmeException(e);
        }
        return txt;
    }

    private void ReadmeException(IOException e) {

    }

}