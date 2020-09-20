package com.nifo.accessibility.blade_app;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class MyAccessibilityService extends AccessibilityService {
    private static final String TAG = MyAccessibilityService.class.getSimpleName();

    @Override
    public void onCreate() {
        Log.d(TAG,"On Create");
    }//endOnCreate()

    @Override
    public void onDestroy(){
        Log.d(TAG,"On Destroy");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onServiceConnected() {
        Log.d(TAG,"On Service Connected");

    }

    @Override
    public void onInterrupt() {

    }
}

