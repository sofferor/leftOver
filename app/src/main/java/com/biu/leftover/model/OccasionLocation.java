package com.biu.leftover.model;

public class OccasionLocation {
    private int building;
    private int room;

    public OccasionLocation() {
    }

    public OccasionLocation(int building, int room) {
        this.building = building;
        this.room = room;
    }

    public String getLocationDisplay() {
        return String.valueOf(room) + "/" + String.valueOf(building);
    }

    public int getBuilding() {
        return building;
    }

    public int getRoom() {
        return room;
    }
}
