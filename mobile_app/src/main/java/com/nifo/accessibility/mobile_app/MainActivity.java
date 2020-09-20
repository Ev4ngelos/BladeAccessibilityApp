package com.nifo.accessibility.mobile_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vuzix.connectivity.sdk.Connectivity;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivityMobile";
    public static final String BLADE_KEY_EVENT = "com.nifo.accessibility.key_event";
    public Connectivity connectivity;
    Button leftBtn, rightBtn, enterBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leftBtn = findViewById(R.id.leftBtn);
        rightBtn = findViewById(R.id.rightBtn);
        enterBtn = findViewById(R.id.enterBtn);
        backBtn = findViewById(R.id.backBtn);
    }//endOnCreate()

    @Override
    protected void onResume() {
        super.onResume();
        connectivity = Connectivity.get(getApplicationContext());

    }

    public void sendKeyEvent(View view) {
        Button btn = findViewById(view.getId());
        String event = btn.getText().toString();
        Log.d(TAG, "Button ID: " + btn.getId() + " " + btn.getText());
        Intent keyEventIntent = new Intent(BLADE_KEY_EVENT);
        //keyEventIntent.setPackage("com.nifo.accessibility");
        if (event != null) {
            keyEventIntent.putExtra("key_event", event);
            if(connectivity.isConnected()==true){
                connectivity.sendBroadcast(keyEventIntent);
            } else {
                Toast.makeText(getApplicationContext(),"Connect to Blade device.",Toast.LENGTH_SHORT).show();
            }

        }
    }


}
