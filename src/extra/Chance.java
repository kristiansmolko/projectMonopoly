package extra;

public class Chance {
    private final String text;
    private final String action;
    private final int value;

    Chance(String text, String action, int value) {
        this.text = text;
        this.action = action;
        this.value = value;
    }

    public String getAction(){
        return action;
    }

    public String getText(){
        return text;
    }

    public int getValue(){
        return value;
    }
}
