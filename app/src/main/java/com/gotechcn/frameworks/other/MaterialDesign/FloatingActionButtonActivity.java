package com.gotechcn.frameworks.other.MaterialDesign;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gotechcn.frameworks.R;

public class FloatingActionButtonActivity extends AppCompatActivity {


    private FloatingActionButton mFabBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);

        mFabBtn = findViewById(R.id.fab);
        mFabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "点我点我",Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
