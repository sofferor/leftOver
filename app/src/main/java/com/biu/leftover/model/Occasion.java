package com.biu.leftover.model;

import android.widget.ImageView;

import com.biu.leftover.utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Occasion implements DbObject {

    private String dbId;
    private String title;
    private String info;
    private Date create_time;
    private Date update_time;
    private OccasionLocation occasionLocation;
    private int score;
    private int imageIndex = new Random().nextInt(Constants.NUM_OF_EMOJIES);
    private ImageView imageView;
    private FoodType foodType;

    public Occasion() {
    }

    public Occasion(Occasion other) {
        this.dbId = other.getDbId();
        this.title = other.getTitle();
        this.info = other.getInfo();
        this.create_time = other.getCreate_time();
        this.update_time = other.getUpdate_time();
        this.occasionLocation = other.getOccasionLocation();
        this.score = other.getScore();
        this.foodType = other.getFoodType();
    }

    public Occasion(String title, String info, Date create_time, OccasionLocation occasionLocation, String foodType) {
        this.title = title;
        this.info = info;
        this.create_time = create_time;
        this.update_time = create_time;
        this.occasionLocation = occasionLocation;
        this.foodType = FoodType.valueOf(foodType);
        this.score = 0;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public String getCreateTimeDisplay() {
        return new SimpleDateFormat("dd/MM HH:mm").format(getCreate_time());
    }

    public OccasionLocation getOccasionLocation() {
        return occasionLocation;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incScore() {
        this.score += 1;
    }

    public void decScore() {
        this.score -= 1;
    }

    public int getImageIndex() {
        return imageIndex;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public String getFoodTypeName() {
        if (foodType == FoodType.NONE) {
            return null;
        }
        return foodType.getTypeName();
    }

    @Override
    public String getDbId() {
        return dbId;
    }

    @Override
    public void setDbId(String dbId) {
        this.dbId = dbId;
    }

    @Override
    public void setUpdate_time(Date date) {
        this.update_time = date;
    }
}
