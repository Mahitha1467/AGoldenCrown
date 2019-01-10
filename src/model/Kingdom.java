package model;

public class Kingdom {
    private String name;
    private String emblem;

    public Kingdom(String name, String emblem) {
        this.name = name;
        this.emblem = emblem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmblem() {
        return emblem;
    }

    public void setEmblem(String emblem) {
        this.emblem = emblem;
    }
}
