package com.biu.leftover;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.biu.leftover.utils.Constants;

public class AboutActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setToolbar();
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.about_page_toolbar);
        //Toolbar set
        try {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(Constants.TOOL_BAR_TITLE_ABOUT);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            Toast.makeText(AboutActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(this.getLocalClassName(), e.getMessage());
        }
    }
}
