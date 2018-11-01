package com.mrrajnruoj.filemanagement;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    EditText edtInput;
    Button btnRead, btnWrite;
    TextView txtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtInput = (EditText)findViewById(R.id.edtInput);
        btnRead = (Button)findViewById(R.id.btnRead);
        btnWrite = (Button)findViewById(R.id.btnWrite);
        txtView = (TextView)findViewById(R.id.txtView);


        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFile("myFile", edtInput.getText().toString());
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = readFile("myFile");
                txtView.setText(text);
            }
        });
    }
    public void saveFile(String file, String text) {
        try {
            FileOutputStream fos = openFileOutput(file, Context.MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error while saving file!", Toast.LENGTH_SHORT).show();
        }
    }

    public String readFile(String file) {
        String text = "";
        try {
            FileInputStream fis = openFileInput(file);
            int size = fis.available();
            byte[] buffer = new byte[size];
            fis.read(buffer);
            fis.close();
            text = new String(buffer);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error while reading file!", Toast.LENGTH_SHORT).show();
        }

        return text;
    }
}
