package model;

public class Input {
    private String kingdomName;
    private String message;

    public Input(String kingdomName, String message) {
        this.kingdomName = kingdomName;
        this.message = message;
    }

    public String getKingdomName() {
        return kingdomName;
    }

    public String getMessage() {
        return message;
    }
}
