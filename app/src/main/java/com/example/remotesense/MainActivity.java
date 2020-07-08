package com.example.remotesense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Runs first when app starts
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //layouut of the main landing page


//        Button myButton = findViewById(R.id.button1);
//        myButton.setOnClickListener {sendMessage()};

    }

    public void sendMessage(android.view.View view){
        Toast.makeText(this, "Button pressed ", Toast.LENGTH_SHORT).show();
        TextView txtHello= findViewById(R.id.txt_01);
        EditText edittext= findViewById(R.id.editTxt);
        txtHello.setText("Bonjour " + edittext.getText().toString() + "  Welcome to RemoteSense");

    }

    public void onBtnClick(android.view.View view){ // button object and method when clicked
        TextView txtHello= findViewById(R.id.txt_01);
        EditText edittext= findViewById(R.id.editTxt);
        txtHello.setText("Bonjour " + edittext.getText().toString() + "  Welcome to RemoteSense");
    }
}