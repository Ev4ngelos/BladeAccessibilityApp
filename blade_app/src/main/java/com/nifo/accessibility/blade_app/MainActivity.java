package com.nifo.accessibility.blade_app;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.vuzix.connectivity.sdk.Connectivity;
import com.vuzix.hud.actionmenu.ActionMenuActivity;

public class MainActivity extends ActionMenuActivity {
    private static final String TAG = "MainActivity";
    public static final String TEST_BLADE_ACTION = "test_blades_communication";
    public static final String BLADE_KEY_EVENT = "com.nifo.accessibility.key_event";
    public Connectivity connectivity;
    private MenuItem menuItem1;
    private MenuItem menuItem2;
    private MenuItem menuItem3;
    private TextView mainText;
    private KeyEventReceiver keyEventReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }//endOnCreate()

    @Override
    protected boolean onCreateActionMenu(Menu menu) {
        super.onCreateActionMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        menuItem1 = menu.findItem(R.id.item1);
        menuItem2 = menu.findItem(R.id.item2);
        menuItem3 = menu.findItem(R.id.item3);
        mainText = (TextView) findViewById(R.id.mainTextView);
        updateMenuItems();
        return true;
    }

    @Override
    protected boolean alwaysShowActionMenu() {
        return true;
    }

    private void updateMenuItems() {
        if (menuItem1 == null) {
            return;
        }
        //VuzixMenuItem.setEnabled(false);
        //BladeMenuItem.setEnabled(false);
    }

    //Action Menu Click events
    //These events where register via the XML for the menu definitions.
    public void showOption(MenuItem item) {
        mainText.setText(item.getTitle().toString());
        Log.d(TAG,item.getTitle().toString());
        showToast(item.getTitle().toString());
    }

    private void showToast(final String text) {
        final Activity activity = this;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void registerReceivers(){
        IntentFilter keyActionFilter = new IntentFilter();
        keyActionFilter.addAction(BLADE_KEY_EVENT);
        registerReceiver(keyEventReceiver,keyActionFilter);
    }

    @Override
    protected void onResume(){
        super.onResume();
        connectivity = Connectivity.get(getApplicationContext());
        keyEventReceiver = new KeyEventReceiver();
        Log.d(TAG, "Blades connected to  " + connectivity.getDevice() + " :" + connectivity.isConnected()); //checks if blades are connected to mobile
        if(connectivity.isConnected()==true){
            registerReceivers();
        } else {
            Toast.makeText(getApplicationContext(),"Connect to Blade device.",Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(keyEventReceiver);
    }
}