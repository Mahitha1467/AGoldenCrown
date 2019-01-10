package util;

import model.Kingdom;

import java.util.Arrays;
import java.util.List;
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
}
