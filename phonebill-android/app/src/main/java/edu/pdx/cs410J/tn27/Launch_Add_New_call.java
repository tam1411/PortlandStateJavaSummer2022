package edu.pdx.cs410J.tn27;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import edu.pdx.cs410J.ParserException;

public class Launch_Add_New_call extends AppCompatActivity {

    private static final int CALL = 10 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_add_new_call);
    }

    public boolean SearchPhoneCall(View view){
        EditText Customer = findViewById(R.id.customerSearch);
        if (Customer.length()== 0)
        {
            Customer.setError("This field is required");
        }
        else{
            String customer = Customer.getText().toString();
            File file = getFile(customer);
            boolean IsExist = file.exists();
            if (!IsExist){
                Toast.makeText(this, "No Found Customer",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "Customer :" + customer,Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return false;
    }
    public void LaunchNewCall(View view) {
        if (SearchPhoneCall(view)){
        Intent intent = new Intent(this,Add_call.class);
        startActivityForResult(intent,CALL);
        }
    }
    @Override
    protected void onActivityResult (int request_code, int result_code, @Nullable Intent data) {
        super.onActivityResult(request_code, result_code, data);
        if (request_code == CALL && result_code == RESULT_OK && data != null) {

            PhoneCall call = (PhoneCall) data.getSerializableExtra("new_call");
            if (call != null) {
                EditText Customer = findViewById(R.id.customerSearch);
                String customer = Customer.getText().toString();
                File file_name = getFile(customer);
                //PhoneBill bill = null;
                try {
                    TextParser parser = new TextParser(new FileReader(file_name));
                     PhoneBill bill = parser.parse();
                     bill.addPhoneCall(call);
                    TextDumper dumper = new TextDumper(new FileWriter(file_name));
                     dumper.dump(bill);
                } catch (IOException | ParserException e) {
                    e.getMessage();
                }
            }
        }

    }
    private PhoneBill ReadFile(File file_name) throws FileNotFoundException, ParserException {
        TextParser parser = new TextParser(new FileReader(file_name));
        return parser.parse();
    }
    private void ToastException(IOException e) {
        Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
    }
    private File getFile(String Customer){
        String file_name =String.format("%s.txt",Customer);
        return new File(this.getFilesDir(),file_name);
    }
    private FileInputStream getFileToRead(String file_name) {
        String name = String.format("%s.txt", file_name);
        FileInputStream in = null;
        try {
            in = openFileInput(name);
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        return in;
    }
}