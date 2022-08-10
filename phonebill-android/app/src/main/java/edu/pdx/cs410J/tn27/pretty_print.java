package edu.pdx.cs410J.tn27;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import edu.pdx.cs410J.ParserException;

public class pretty_print extends AppCompatActivity {

    private static final int PRETTY = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pretty_print);
    }

    public boolean SearchPhoneCallPrettyPrint(View view) {
        EditText Customer = findViewById(R.id.editTextTextPersonName);
        if (Customer.length() == 0) {
            Customer.setError("This field is required");
        } else {
            String customer = Customer.getText().toString();
            File file = getFile(customer);
            boolean IsExist = file.exists();
            if (!IsExist) {
                Toast.makeText(this, "No Found Customer", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Customer :" + customer, Toast.LENGTH_LONG).show();
                return true;

            }
        }
        return false;

    }

   public void PrettyPrint(View view) {
       if (SearchPhoneCallPrettyPrint(view)){
           Intent intent = new Intent(this,ToPrint.class);
           startActivity(intent);


            /*EditText Customer = findViewById(R.id.editTextTextPersonName);
            String customer = Customer.getText().toString();
            File file_name = getFile(customer);
          try {
              TextParser parser = new TextParser(new FileReader(file_name));
              PhoneBill bill = parser.parse();
          }catch(ParserException | FileNotFoundException e){
              e.getMessage();
          }*/
        }
    }
        private File getFile(String Customer){
            String file_name =String.format("%s.txt",Customer);
            return new File(this.getFilesDir(),file_name);

    }
}