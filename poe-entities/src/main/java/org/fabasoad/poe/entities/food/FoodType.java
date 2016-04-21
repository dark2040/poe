package org.fabasoad.poe.entities.food;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * @author Yevhen Fabizhevskyi
 * @date 05.04.2016.
 */
public enum FoodType {
    CARROT("Carrot", "collect_carrot", "grow_carrot"),
    CABBAGE("Cabbage", "collect_cabbage", "grow_cabbage"),
    GRAPE("Grape", "collect_grape", "grow_grape"),
    WHEAT("Wheat", "collect_wheat", "grow_wheat"),
    RICE("Rice", "collect_rice", "grow_rice"),
    OLIVES("Olives", "collect_olives", "grow_olives");

    private final String displayName;
    private final String collectImageName;
    private final String growImageName;

    FoodType(String displayName, String collectImageName, String growImageName) {
        this.displayName = displayName;
        this.collectImageName = collectImageName;
        this.growImageName = growImageName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getCollectImageName() {
        return collectImageName;
    }

    public String getGrowImageName() {
        return growImageName;
    }

    public static String getFolderName() {
        return "food";
    }

    public static String defaultToCollectAsString() {
        return defaultToCollectAsCollection().stream().map(m -> m.name().toLowerCase()).collect(Collectors.joining(", "));
    }

    public static Collection<FoodType> defaultToCollectAsCollection() {
        return Collections.singletonList(CARROT);
    }

    public static String defaultToGrowAsString() {
        return defaultToGrow().name().toLowerCase();
    }

    public static FoodType defaultToGrow() {
        return CARROT;
    }

    public static String valuesAsString() {
        return Arrays.stream(values()).map(m -> m.name().toLowerCase()).collect(Collectors.joining(", "));
    }
}
