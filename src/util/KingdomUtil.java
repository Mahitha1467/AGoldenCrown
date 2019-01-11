package util;

import model.Kingdom;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class KingdomUtil {
    private static List<Kingdom> existingKingdoms = Arrays.asList(
            new Kingdom("land", "panda"),
            new Kingdom("water", "octopus"),
            new Kingdom("ice", "mammoth"),
            new Kingdom("air", "owl"),
            new Kingdom("fire", "dragon")
    );

    public static List<String> getAllKingdomNames() {
        return existingKingdoms.stream()
                .map(Kingdom::getName)
                .collect(Collectors.toList());
    }

    private static String getEmblem(String name) {
        Optional<Kingdom> requiredKingdom = existingKingdoms.stream()
                .filter(kingdom -> kingdom.getName().equals(name))
                .findFirst();

        return requiredKingdom.isPresent() ? requiredKingdom.get().getEmblem() : "";
    }


    public static Map<String, String> getKingdomEmblems(List<String> kingdomsNames) {
        Map<String, String> kingdomsWithEmblem = new HashMap<>();
        kingdomsNames.forEach(name -> kingdomsWithEmblem.put(name, getEmblem(name)));

        return kingdomsWithEmblem;
    }

}
