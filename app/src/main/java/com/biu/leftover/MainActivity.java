package com.biu.leftover;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.biu.leftover.model.Occasion;
import com.biu.leftover.utils.Constants;
import com.biu.leftover.utils.DBUtils;
import com.biu.leftover.utils.Utils;
import com.biu.leftover.utils.VerticalSpaceItemDecoration;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private FirebaseAuth mAuth;
    private Toolbar mToolBar;
    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<Occasion, EventViewHolder> recyclerAdapter;
    private DatabaseReference databaseReference;
    private Random random;
    private FloatingActionButton addOccasionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolBar = findViewById(R.id.main_page_toolbar);
        setSupportActionBarAndTitle();
        mAuth = FirebaseAuth.getInstance();
        random = new Random();


        recyclerView = findViewById(R.id.main_events_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        VerticalSpaceItemDecoration verticalSpaceItemDecoration = new VerticalSpaceItemDecoration(10);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.addItemDecoration(verticalSpaceItemDecoration);

        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.EVENTS);
        initialFirebaseRecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);


        addOccasionButton = findViewById(R.id.main_add_occasion_button);
        addOccasionButton.setOnClickListener(view -> goToActivity(AddOccasionActivity.class, false, null));
    }

    private void initialFirebaseRecyclerAdapter() {
        Log.d("########", "initialFirebaseRecyclerAdapter");
        Query query = databaseReference.limitToLast(50);
        FirebaseRecyclerOptions<Occasion> options = new FirebaseRecyclerOptions.Builder<Occasion>().
                setQuery(query, Occasion.class).build();
        recyclerAdapter = new FirebaseRecyclerAdapter<Occasion, EventViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull EventViewHolder holder, int position, @NonNull Occasion model) {
                holder.setTextToTitle(model.getTitle());
                holder.setTextToInfo(model.getInfo());
                holder.setTextToTime(model.getTimeDisplay());
                holder.setTextToLocation(model.getOccasionLocation().getLocationDisplay());
                holder.setTextToScore(String.valueOf(model.getScore()));
                holder.setOnClickListner(v -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("dbId", model.getDbId());
                    goToActivity(ViewOccasionActivity.class, false, map);
                });
                if (model.getImageView() == null) {
                    holder.setSrcToImageView(Utils.getImage(model.getImageIndex()));
                } else {
                    //to complete
                }
                holder.likeButton.setOnClickListener(view -> {
                    Occasion occasion = new Occasion(model);
                    occasion.incScore();
                    DBUtils.updateObject(Constants.EVENTS, occasion.getDbId(), occasion, null);
                });
                holder.disLikeButton.setOnClickListener(view -> {
                    Occasion occasion = new Occasion(model);
                    occasion.decScore();
                    DBUtils.updateObject(Constants.EVENTS, occasion.getDbId(), occasion, null);
                });
            }

            @NonNull
            @Override
            public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                Log.d("########", "onCreateViewHolder");
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_occasion_, parent, false);

                return new EventViewHolder(view);
            }
        };
    }

    private void setSupportActionBarAndTitle() {

        try {
            setSupportActionBar(mToolBar);
            getSupportActionBar().setTitle(Constants.TOOL_BAR_TITLE_MAIN_ACTIVITY);
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and change activity accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            goToActivity(StartActivity.class, true, null);
        }
        recyclerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        recyclerAdapter.stopListening();
    }

    private void goToActivity(Class<?> cls, boolean finish, Map<String, String> extras) {
        Intent startIntent = new Intent(MainActivity.this, cls);
        if (extras != null) {
            extras.forEach(startIntent::putExtra);
        }
        if (finish) {
            startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        startActivity(startIntent);
        if (finish) {
            finish();
        }
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
            goToActivity(StartActivity.class, true, null);
        }
        return true;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView info;
        private TextView time;
        private TextView location;
        private TextView score;
        private ImageView imageView;
        private ConstraintLayout constraintLayout;
        private ImageButton likeButton;
        private ImageButton disLikeButton;

        public EventViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.single_textView_title);
            info = itemView.findViewById(R.id.single_textView_info);
            time = itemView.findViewById(R.id.single_time_text);
            location = itemView.findViewById(R.id.single_location_text);
            score = itemView.findViewById(R.id.view_text_score);
            imageView = itemView.findViewById(R.id.single_imageView);
            likeButton = itemView.findViewById(R.id.view_imageButton_like);
            disLikeButton = itemView.findViewById(R.id.view_imageButton_dislike);
            constraintLayout = itemView.findViewById(R.id.single_parent_layout);
        }

        public void setTextToTitle(String text) {
            this.title.setText(text);
        }

        public void setTextToInfo(String info) {
            this.info.setText(info);
        }

        public void setTextToTime(String time) {
            this.time.setText(time);
        }

        public void setTextToLocation(String location) {
            this.location.setText(location);
        }

        public void setTextToScore(String score) {
            this.score.setText(score);
        }

        public void setSrcToImageView(int src) {
            this.imageView.setImageResource(src);
        }

        public void setOnClickListner(View.OnClickListener clickListner) {
            constraintLayout.setOnClickListener(clickListner);
        }
    }
}
