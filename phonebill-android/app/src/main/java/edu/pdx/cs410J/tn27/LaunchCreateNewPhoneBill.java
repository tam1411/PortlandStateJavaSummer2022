package edu.pdx.cs410J.tn27;

import androidx.appcompat.app.AppCompatActivity;
import static edu.pdx.cs410J.tn27.validateinfo.isValidDate;
import static edu.pdx.cs410J.tn27.validateinfo.isValidTime;
import static edu.pdx.cs410J.tn27.validateinfo.isValidPhoneNumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class LaunchCreateNewPhoneBill extends AppCompatActivity implements Serializable {
    static PhoneBill Bill;
    //EditText Customer,Caller,Callee,Begin_date, Begin_time,End_date,End_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_create_new_phone_bill);
        //TextView view = null;
        //CreatePhoneBill();
    }

    public void CreatePhoneBill(View view){


        EditText Customer = findViewById(R.id.customer);
        EditText Caller = findViewById(R.id.Caller);
        EditText Callee = findViewById(R.id.editTextTextPersonName9);
        EditText Begin_date = findViewById(R.id.editTextTextPersonName10);
        EditText Begin_time = findViewById(R.id.editTextTextPersonName11);
        EditText End_date = findViewById(R.id.editTextTextPersonName12);
        EditText End_time = findViewById(R.id.editTextTextPersonName13);
        //If all the fields are correct --> create a phone bill and return it
         boolean flag = CheckAllFields(Customer,Caller,Callee,Begin_date,Begin_time,End_date,End_time);
            if (flag) {
                this.Bill = new PhoneBill(Customer.getText().toString());
                PhoneCall call = CreateNewPhoneCall(Caller, Callee, Begin_date, Begin_time, End_date, End_time);
                this.Bill.addPhoneCall(call);
                //Toast.makeText(this,, Toast.LENGTH_LONG).show();

            }

        //return null;
    }
   public void ReturnToMenu(View view){
        Intent data = new Intent();
        data.putExtra("new_bill",this.Bill);
        setResult(RESULT_OK,data);
        finish();
    }

    public PhoneCall CreateNewPhoneCall(EditText Caller, EditText Callee, EditText Begin_date,
                                         EditText Begin_time, EditText End_date,EditText End_time) {
        String caller,callee;
        String begin_date,begin_time,end_date,end_time,begin_zone,end_zone;
        caller = Caller.getText().toString();
        callee = Callee.getText().toString();
        begin_date= Begin_date.getText().toString();
        end_date = End_date.getText().toString();
        begin_time = Begin_time.getText().toString();
        end_time = End_time.getText().toString();

        return new PhoneCall(caller,callee,begin_date,begin_time,end_date,end_time);
    }

    public boolean CheckAllFields(EditText Customer, EditText Caller, EditText Callee, EditText Begin_date,
                              EditText Begin_time, EditText End_date,EditText End_time){
        int flag = 0;
        if (Customer.length() == 0) {
            Customer.setError("This field is required");
            flag = 1;
        }
        if (Caller.length() == 0){
            Caller.setError("This field is required");
           // return false;
            flag = 1;
        }
        if (isValidPhoneNumber(Caller.getText().toString())){
            Caller.setError("Phone number format: nnn-nnn-nnnn");
            flag = 1;
        }
        if (Callee.length() == 0){
            Callee.setError("This field is required");
            flag = 1;
        }
        if (isValidPhoneNumber(Callee.getText().toString())){
            Callee.setError("Phone number format: nnn-nnn-nnnn");
            flag = 1;
        }
        if (isValidDate(Begin_date.getText().toString())){
            Begin_date.setError("Date format: mm/dd/yyyy");
            flag = 1;
        }
        if (Begin_date.length() == 0) {
            Begin_date.setError("This field is required");
            flag = 1;
        }

        if (isValidDate(End_date.getText().toString())){
            End_date.setError("Date format: mm/dd/yyyy");
            flag = 1;
        }

        if (Begin_time.length() == 0) {
        Begin_time.setError("This field is required");
            flag = 1;
    }
        if (isValidTime(Begin_time.getText().toString())){
        Begin_time.setError("Time format: nn:nn ");

    }
        if (End_date.length() == 0){
            End_date.setError("This field is required");
            flag = 1;
        }
        if (End_time.length() == 0) {
            End_time.setError("This field is required");
            flag = 1;
        }
        else if (isValidTime(End_time.getText().toString())){
            End_time.setError("Time format: nn:nn ");
            flag = 1;
        }
        if (flag != 0)
            return false;
        return true;
    }


}