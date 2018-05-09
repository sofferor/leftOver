package com.biu.leftover;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        //try to get the toolbar and write a title to it.
        mToolBar = (Toolbar) findViewById(R.id.main_page_toolbar);
        if (mToolBar == null) {
            Toast.makeText(MainActivity.this, "MainActivity - mToolBar is null.", Toast.LENGTH_LONG);
            Log.e("MainActivity", "MainActivity - mToolBar is null.");
        }
        setSupportActionBar(mToolBar);
        try {
            getSupportActionBar().setTitle("Tool Bar");
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e("MainActivity", e.getMessage());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            goToStartActivity();
        }
    }

    private void goToStartActivity() {
        Intent startIntent = new Intent(MainActivity.this, StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.main_menu_log_out) {
            FirebaseAuth.getInstance().signOut();
            goToStartActivity();
        }
        return  true;
    }
}
