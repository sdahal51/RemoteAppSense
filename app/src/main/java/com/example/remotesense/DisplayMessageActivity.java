package com.example.remotesense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Calendar;
import java.util.Date;

public class DisplayMessageActivity extends AppCompatActivity {
    Button previous_button;

    RecyclerView recyclerView;
    DatabaseReference ref;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        TextView welcometext= findViewById(R.id.welcome_txt);
        Bundle extras= getIntent().getExtras();
        String myNametext;





        if (extras!= null){
            myNametext= extras.getString("name");
            welcometext.setText("Hello "+ myNametext.toString() + "  Here is the temperature data recorded in your room today " );
        }

        recyclerView= findViewById(R.id.dp_recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        database= FirebaseDatabase.getInstance();
        ref= database.getReference("RemoteSense");

        Button donebutton= findViewById(R.id.btn_2);
        donebutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Toast.makeText(DisplayMessageActivity.this, "Goodbye! Exiting App", Toast.LENGTH_SHORT).show();
                finishActivity(0);
                System.exit(0);
            }
            });
        previous_button = findViewById(R.id.button_dp);
        previous_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DisplayMessageActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<IOT_Data> options=
                new FirebaseRecyclerOptions.Builder<IOT_Data>()
                .setQuery(ref, IOT_Data.class)
                .setLifecycleOwner(this)
                .build();

        FirebaseRecyclerAdapter<IOT_Data, Holder>firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<IOT_Data, Holder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull IOT_Data iot_data) {
                        holder.setView(getApplicationContext(),
                                iot_data.getHumidity(),
                                iot_data.getTemp_F(),
                                iot_data.getTemp_C(),
                                iot_data.getTime());
                    }

                    @NonNull
                    @Override
                    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        return new Holder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.data, parent,false)) ;
                    }
                };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
