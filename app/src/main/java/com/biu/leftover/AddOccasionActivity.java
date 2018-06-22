package com.biu.leftover;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.biu.leftover.model.FoodType;
import com.biu.leftover.model.Occasion;
import com.biu.leftover.model.OccasionLocation;
import com.biu.leftover.utils.Constants;
import com.biu.leftover.utils.DBUtils;

import java.util.Date;

public class AddOccasionActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText occasionName;
    EditText occasionInfo;
    EditText roomNumber;
    EditText buildingNumber;
    Spinner foodTypesSpinner;
    Button saveOccasionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_occasion);
        setToolbar();

        occasionName = findViewById(R.id.occasion_name);
        occasionInfo = findViewById(R.id.occasion_info);
        roomNumber = findViewById(R.id.room_number);
        buildingNumber = findViewById(R.id.building_number);
        saveOccasionButton = findViewById(R.id.save_occasion_button);

        foodTypesSpinner = findViewById(R.id.food_types_spinner);
        ArrayAdapter<String> foodTypesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, FoodType.getFoodTypesNames());
        foodTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodTypesSpinner.setAdapter(foodTypesAdapter);
        foodTypesSpinner.setSelection(foodTypesAdapter.getPosition(FoodType.NONE.getTypeName()));


        saveOccasionButton.setOnClickListener(view -> {
            if (buildingNumber.getText().toString().isEmpty() || roomNumber.getText().toString().isEmpty()) {
                Toast.makeText(AddOccasionActivity.this, "You must enter a room number and a building number.", Toast.LENGTH_LONG).show();
                return;
            }
            OccasionLocation occasionLocation = new OccasionLocation(Integer.valueOf(buildingNumber.getText().toString()), Integer.valueOf(roomNumber.getText().toString()));
            Occasion occasion = new Occasion(occasionName.getText().toString(), occasionInfo.getText().toString(), new Date(), occasionLocation, foodTypesSpinner.getSelectedItem().toString());
            DBUtils.addObject(Constants.EVENTS, occasion).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(AddOccasionActivity.this, "Occasion added successfully.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(AddOccasionActivity.this, "Adding occasion failed, please try again.", Toast.LENGTH_LONG).show();
                }
            });
            goToActivity(MainActivity.class, false);
        });
    }

    private void goToActivity(Class<?> cls, boolean finish) {
        Intent startIntent = new Intent(AddOccasionActivity.this, cls);
        startActivity(startIntent);
        if (finish) {
            finish();
        }
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.main_page_toolbar);
        //Toolbar set
        try {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle(Constants.TOOL_BAR_TITLE_ADD_OCCASION);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            Toast.makeText(AddOccasionActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(this.getLocalClassName(), e.getMessage());
        }
    }
}
