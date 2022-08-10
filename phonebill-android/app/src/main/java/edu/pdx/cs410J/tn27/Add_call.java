package edu.pdx.cs410J.tn27;

import static edu.pdx.cs410J.tn27.validateinfo.isValidDate;
import static edu.pdx.cs410J.tn27.validateinfo.isValidPhoneNumber;
import static edu.pdx.cs410J.tn27.validateinfo.isValidTime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class Add_call extends AppCompatActivity implements Serializable {
    PhoneCall call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_call);
    }

    public void CreateCall(View view){
        EditText Caller = findViewById(R.id.AddCaller);
        EditText Callee = findViewById(R.id.AddCallee);
        EditText Begin_date = findViewById(R.id.begin_date);
        EditText Begin_time = findViewById(R.id.begin_time);
        EditText End_date = findViewById(R.id.end_date);
        EditText End_time = findViewById(R.id.end_time);
        boolean flag = CheckAllFields(Caller,Callee,Begin_date,Begin_time,End_date,End_time);
        if (flag){
            this.call = CreateNewPhoneCall(Caller, Callee, Begin_date, Begin_time, End_date, End_time);
            Toast.makeText(this,"Create Successfully", Toast.LENGTH_LONG).show();
        }
    }
    public void ReturnToMenu(View view){
        Intent intent = new Intent();
        intent.putExtra( "new_call",this.call);
        setResult(RESULT_OK,intent);
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
    public boolean CheckAllFields( EditText Caller, EditText Callee, EditText Begin_date,
                                  EditText Begin_time, EditText End_date,EditText End_time){
        int flag = 0;
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