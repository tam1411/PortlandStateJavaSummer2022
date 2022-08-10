package edu.pdx.cs410J.tn27;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class Launch_Add_New_call extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_add_new_call);
    }

    public void SearchPhoneCall(View view){
        EditText Customer = findViewById(R.id.customerSearch);
        if (Customer.length()== 0)
        {
            Customer.setError("This field is required");
        }
        else{
            String customer = Customer.getText().toString();
            File file = getTheFile(customer);
            boolean IsExist = file.exists();
            if (!IsExist){
                Toast.makeText(this, "No Found Customer",Toast.LENGTH_LONG).show();
            }
            else {Toast.makeText(this, "Customer :" + customer,Toast.LENGTH_LONG).show();

            }
        }
    }


    private File getTheFile(String Customer){
        String file_name =String.format("%s.txt",Customer);
        return new File(this.getDataDir(),file_name);
    }
}