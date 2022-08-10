package edu.pdx.cs410J.tn27;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to launch the menu interface
 */
public class LaunchMenu extends AppCompatActivity {
    int CreatePhoneBill = 1;
    int Add_New_Call = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_menu);
    }
    public void LaunchPrettyPrint(View view){
        Intent intent = new Intent(this,pretty_print.class);
        startActivity(intent);
    }
    public void LaunchAddNewCall(View view){
        Intent intent = new Intent(this,Launch_Add_New_call.class);
        startActivity(intent);
    }

    public void LaunchCreatePhoneBill(View view){
        Intent intent = new Intent(this, LaunchCreateNewPhoneBill.class);
        startActivityForResult(intent,CreatePhoneBill);
        //startActivity(intent);
    }

    @Override
    protected void onActivityResult (int request_code, int result_code, @Nullable Intent data) {
        super.onActivityResult(request_code, result_code, data);
        if (request_code == CreatePhoneBill && result_code == RESULT_OK && data != null) {

            PhoneBill bill = (PhoneBill) data.getSerializableExtra("new_bill");

            if (bill != null) {
                File file_name = getTheFile(bill.getCustomer());
                try {
                    TextDumper dumper = new TextDumper(new FileWriter(file_name));
                    dumper.dump(bill);

                } catch (IOException e) {
                    ToastException(e);
                }
            }
        }
    }

    private void ToastException(IOException e) {
        Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
    }


    private File getTheFile(String Customer){
        String file_name =String.format("%s.txt",Customer);
            return new File(this.getFilesDir(),file_name);
        }



}