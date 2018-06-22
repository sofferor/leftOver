package com.biu.leftover;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.biu.leftover.model.Occasion;
import com.biu.leftover.utils.Constants;
import com.biu.leftover.utils.DBUtils;
import com.biu.leftover.utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ViewOccasionActivity extends AppCompatActivity {

    private TextView title;
    private TextView info;
    private TextView time;
    private TextView location;
    private TextView score;
    private ImageView imageView;
    private ImageButton likeButton;
    private ImageButton disLikeButton;
    private FloatingActionButton deleteOccasionButton;
    private Toolbar toolBar;
    private TextView foodType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiw_occasion);

        toolBar = findViewById(R.id.view_toolbar);
        //Toolbar set
        try {
            setSupportActionBar(toolBar);
            getSupportActionBar().setTitle(Constants.TOOL_BAR_TITLE_VIEW);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
            Toast.makeText(ViewOccasionActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(this.getLocalClassName(), e.getMessage());
        }
        title = findViewById(R.id.view_textView_title);
        info = findViewById(R.id.view_textView_info);
        time = findViewById(R.id.view_time_text);
        location = findViewById(R.id.view_location_text);
        score = findViewById(R.id.view_text_score);
        imageView = findViewById(R.id.view_imageView);
        likeButton = findViewById(R.id.view_imageButton_like);
        disLikeButton = findViewById(R.id.view_imageButton_dislike);
        deleteOccasionButton = findViewById(R.id.view_delete_occasion_button);
        foodType = findViewById(R.id.view_foodtype);
        String dbId = getIntent().getExtras().getString("dbId");
        DBUtils.getObjDbReference(Constants.EVENTS, dbId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Occasion occasion = dataSnapshot.getValue(Occasion.class);
                title.setText(occasion.getTitle());
                info.setText(occasion.getInfo());
                time.setText(occasion.getCreateTimeDisplay());
                location.setText(occasion.getOccasionLocation().getLocationDisplay());
                score.setText(String.valueOf(occasion.getScore()));
                foodType.setText(occasion.getFoodTypeName());
                if (occasion.getImageView() == null) {
                    imageView.setImageResource(Utils.getImage(occasion.getImageIndex()));
                } else {
                    //to complete
                }
                likeButton.setOnClickListener(view -> {
                    occasion.incScore();
                    DBUtils.updateObject(Constants.EVENTS, occasion.getDbId(), occasion, null);
                    score.setText(String.valueOf(occasion.getScore()));
                });
                disLikeButton.setOnClickListener(view -> {
                    occasion.decScore();
                    DBUtils.updateObject(Constants.EVENTS, occasion.getDbId(), occasion, null);
                    score.setText(String.valueOf(occasion.getScore()));
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        deleteOccasionButton.setOnClickListener(view -> {
                DBUtils.deleteObj(Constants.EVENTS, dbId);
                startActivity(new Intent(ViewOccasionActivity.this, MainActivity.class));
        });

    }
}
