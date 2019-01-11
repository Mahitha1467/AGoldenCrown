package model;

import java.util.List;

public class Ruler {
    private String name;
    private List<String> allies;

    public Ruler() {
    }

    public Ruler(String name, List<String> allies) {
        this.name = name;
        this.allies = allies;
    }

    public String getName() {
        return name;
    }

    public List<String> getAllies() {
        return allies;
    }
}
