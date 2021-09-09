package duke.util;

public enum Priority {
    HIGH,
    MEDIUM,
    LOW;

    @Override
    public String toString() {
        String str = "";
        if (this == HIGH) {
            str = "H";
        } else if (this == MEDIUM) {
            str = "M";
        } else if (this == LOW) {
            str = "L";
        }

        return str;
    }
}
