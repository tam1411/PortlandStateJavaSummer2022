package edu.pdx.cs410J.tn27;

import androidx.appcompat.app.AppCompatActivity;
import static edu.pdx.cs410J.tn27.validateinfo.isValidDate;
import static edu.pdx.cs410J.tn27.validateinfo.isValidTime;
import static edu.pdx.cs410J.tn27.validateinfo.isValidPhoneNumber;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LaunchCreateNewPhoneBill extends AppCompatActivity {

    EditText Customer,Caller,Callee,Begin_date, Begin_time,End_date,End_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_create_new_phone_bill);
    }

    public PhoneBill CreatePhoneBill(View view){

        Customer = findViewById(R.id.editTextTextPersonName7);
        Caller = findViewById(R.id.editTextTextPersonName8);
        Callee = findViewById(R.id.editTextTextPersonName9);
        Begin_date = findViewById(R.id.editTextTextPersonName10);
        Begin_time = findViewById(R.id.editTextTextPersonName11);
        End_date = findViewById(R.id.editTextTextPersonName12);
        End_time = findViewById(R.id.editTextTextPersonName13);


        return null;
    }

    public boolean CheckAllFields(EditText Customer, EditText Caller, EditText Callee, EditText Begin_date,
                              EditText Begin_time, EditText End_date,EditText End_time){
        if (Customer.length() == 0) {
            Customer.setError("This field is required");
            return false;
        }
        if (Caller.length() == 0){
            Caller.setError("This field is required");
            return false;
        }
        if (isValidPhoneNumber(Caller.toString())){
            Caller.setError("Phone number format: nnn-nnn-nnnn");
            return false;
        }
        if (Callee.length() == 0){
            Callee.setError("This field is required");
            return false;
        }
        if (isValidPhoneNumber(Callee.toString())){
            Callee.setError("Phone number format: nnn-nnn-nnnn");
            return false;
        }
        if (isValidDate(Begin_date.toString())){
            Begin_date.setError("Date format: mm/dd/yyyy");
            return false;
        }
        if (Begin_date.length() == 0){
            Begin_date.setError("This field is required");
            return false;
        }
        if (isValidDate(End_date.toString())){
            End_date.setError("Date format: mm/dd/yyyy");
            return false;
        }

        if (Begin_time.length() == 0) {
        Begin_time.setError("This field is required");
        return false;
    }
        if (isValidTime(Begin_time.toString())){
        Begin_time.setError("Time format: n:nn am/pm");
        return false;
    }
        if (End_date.length() == 0){
            End_date.setError("This field is required");
            return false;
        }
        if (End_time.length() == 0) {
            End_time.setError("This field is required");
            return false;
        }
        else if (isValidTime(End_time.toString())){
            End_time.setError("Time format: n:nn am/pm");
            return false;
        }

        return true;
    }


}