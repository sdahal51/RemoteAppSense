package com.example.remotesense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//Firebase imports
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

//fragments


//java utils import


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    // private static final int RC_SIGN_IN = 123;
    Button next_Activity_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Runs first when app starts
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //layout of the main landing page

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                    channelName, NotificationManager.IMPORTANCE_LOW));
        }

        // Handle possible data accompanying notification message.
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }

        final EditText nametext= findViewById(R.id.editText);


        next_Activity_button = (Button) findViewById(R.id.main_btn);
        next_Activity_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
                String str= nametext.getText().toString();
                intent.putExtra("name", str);
                startActivity(intent);

                Log.d(TAG, "Subscribing to alert topic");
                // [START subscribe_topics]
                FirebaseMessaging.getInstance().subscribeToTopic("RemoteSenseAlert")
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                String msg = getString(R.string.msg_subscribed);
                                if (!task.isSuccessful()) {
                                    msg = getString(R.string.msg_subscribe_failed);
                                }
                                Log.d(TAG, msg);
                                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });

                FirebaseInstanceId.getInstance().getInstanceId()
                        .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                            @Override
                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                if (!task.isSuccessful()) {
                                    Log.w(TAG, "getInstanceId failed", task.getException());
                                    return;
                                }

                                // Get new Instance ID token
                                String token = task.getResult().getToken();

                                // Log and toast
                                String msg = getString(R.string.msg_token_fmt, token);
                                Log.d(TAG, msg);

                                //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

//
//    public void sendMessage(android.view.View view){
//        switch (view.getId()){
//            case R.id.main_btn:
//                Toast.makeText(this, "Button pressed ", Toast.LENGTH_SHORT).show();
//                TextView txtHello= findViewById(R.id.txt_01);
//                EditText edittext= findViewById(R.id.editTxt);
//                txtHello.setText("Hello " + edittext.getText().toString() + "  Log in to View Sensor data");
//                break;
//        }
//    }
}