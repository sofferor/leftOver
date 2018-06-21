package com.biu.leftover.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum FoodType {
    DAIRY("Dairy"),
    PARVE("Parve"),
    MEAT("Meat"),
    VEGAN("Vegan"),
    NONE("None");

    private final static List<String> foodTypeNamesList = new ArrayList<>(FoodType.values().length);

    static {
        for (FoodType foodType : FoodType.values()) {
            foodTypeNamesList.add(foodType.getTypeName());
        }
    }

    private String typeName;

    FoodType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    public static List<String> getFoodTypesNames() {
        return Collections.unmodifiableList(foodTypeNamesList);
    }
}
