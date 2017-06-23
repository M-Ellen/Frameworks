package com.gotechcn.testfagment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity
{

    private static final String TAG = "ActivityLife";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.layout, new FirstFragment());
        fragmentTransaction.commit();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "A -> onRestart()...");
    }

    @Override
    protected void onStart() {
        // The activity is about to become visible.
        super.onStart();
        Log.e(TAG, "A -> onStart()...");

    }

    @Override
    protected void onResume() {
        // The activity has become visible (it is now "resumed").
        super.onResume();
        Log.e(TAG, "A -> onResume()...");
    }

    @Override
    protected void onPause() {
        // Another activity is taking focus (this activity is about to be "paused").
        super.onPause();
        Log.e(TAG, "A -> onPause()...");
    }

    @Override
    protected void onStop() {
        // The activity is no longer visible (it is now "stopped")
        super.onStop();
        Log.e(TAG, "A -> onStop()...");
    }

    @Override
    protected void onDestroy() {
        // The activity is about to be destroyed.
        super.onDestroy();
        Log.e(TAG, "A -> onDestroy()...");
    }

}
