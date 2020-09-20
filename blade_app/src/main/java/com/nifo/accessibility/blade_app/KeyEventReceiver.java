package com.nifo.accessibility.blade_app;

import android.app.Instrumentation;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;

public class KeyEventReceiver extends BroadcastReceiver {
    private static final String TAG = "KeyEventReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        final String key_event = intent.getStringExtra("key_event");
        Log.d(TAG, key_event);
        if (key_event != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Instrumentation inst = new Instrumentation();

                        switch (key_event) {
                            case "Left":
                                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_LEFT); //<--this works
                                break;
                            case "Right":
                                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_RIGHT); //<--this works
                                break;
                            case "Enter":
                                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_ENTER);//<--this works
                                break;
                            case "Back":
                                inst.sendKeyDownUpSync(KeyEvent.KEYCODE_BACK);//<--this works
                                break;
                        }
                        //inst.sendKeyDownUpSync(KeyEvent.KEYCODE_FORWARD);//<--this doesn't work
                        //inst.sendKeyDownUpSync(KeyEvent.KEYCODE_DEL);//<--this doesn't execute in Blades
                        //inst.sendKeyDownUpSync(KeyEvent.KEYCODE_HOME); //<--this doesn't execute in Blades
                        //inst.sendKeyDownUpSync(KeyEvent.KEYCODE_MENU); //<--This is not properly tested
                        //inst.sendKeyDownUpSync(KeyEvent.KEYCODE_MOVE_HOME); //<--this doesn't execute in Blades
                        //inst.sendKeyDownUpSync(KeyEvent.KEYCODE_ESCAPE); //<--this doesn't execute in Blades
                        //inst.sendKeyDownUpSync(KeyEvent.KEYCODE_NUMPAD_ENTER); //<--this doesn't execute in Blades
                        //inst.sendKeyDownUpSync(KeyEvent.KEYCODE_SYSTEM_NAVIGATION_UP); //<--this doesn't execute in Blades

                    } catch (Exception e) {
                    }
                }
            }).start();
        }
    }
}
