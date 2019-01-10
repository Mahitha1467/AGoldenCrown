package util;

import model.Kingdom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KingdomUtil {
    private static List<Kingdom> existingKingdoms = Arrays.asList(
            new Kingdom("land", "panda"),
            new Kingdom("water", "octopus"),
            new Kingdom("ice", "mammoth"),
            new Kingdom("air", "owl"),
            new Kingdom("fire", "dragon")
    );

    public static List<String> getAllKingdomNames() {
        List<String> kingdomNames = new ArrayList<>();
        existingKingdoms.forEach(kingdom -> {
            kingdomNames.add(kingdom.getName());
        });

        return kingdomNames;
    }
}
