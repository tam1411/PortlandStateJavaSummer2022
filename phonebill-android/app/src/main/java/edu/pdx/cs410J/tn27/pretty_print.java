package edu.pdx.cs410J.tn27;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import edu.pdx.cs410J.ParserException;

public class pretty_print extends AppCompatActivity {

    private static final int PRETTY = 9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pretty_print);
    }

    public void SearchPhoneCallPrettyPrint(View view) {
        EditText Customer = findViewById(R.id.CustomerPretty);
        if (Customer.length() == 0) {
            Customer.setError("This field is required");
        } else {
            String customer = Customer.getText().toString();
            File file = getFile(customer);
            boolean IsExist = file.exists();
            if (!IsExist) {
                Toast.makeText(this, "No Found Customer", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Customer :" + customer, Toast.LENGTH_SHORT).show();
                //return true;
                try {
                    TextParser parser = new TextParser(new FileReader(file));
                    PhoneBill bill = parser.parse();
                    Print(bill);
                    Toast.makeText(this,"Load successfully", Toast.LENGTH_LONG).show();

                }catch(ParserException | FileNotFoundException e){
                    e.getMessage();
                }

            }
        }

    }

   public void Print(PhoneBill bill) {
       if (bill != null) {
            ArrayList<PhoneCall> call_list = (ArrayList<PhoneCall>) bill.getPhoneCalls();

            ListView listview = findViewById(R.id.view);
            ArrayAdapter<String> all_calls = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
            for (int i = 0; i< call_list.size(); ++i){
                all_calls.add(call_list.get(i).toString());
            }
            listview.setAdapter(all_calls);
       }
    }
        private File getFile(String Customer){
            String file_name =String.format("%s.txt",Customer);
            return new File(this.getFilesDir(),file_name);

    }
}