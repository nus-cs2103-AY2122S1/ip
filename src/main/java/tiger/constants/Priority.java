package tiger.constants;

public enum Priority {
    HIGH("H"),
    MEDIUM("M"),
    LOW("L"),
    INVALID("I");


    private String letter;

    Priority(String letter) {
        this.letter = letter;
    }

    public String getLetter() {
        return this.letter;
    }

    public static Priority getPriorityFromLetter(String p) {
        switch (p) {
        case "L":
            return Priority.LOW;
        case "M":
            return Priority.MEDIUM;
        case "H":
            return Priority.HIGH;
        }
        return Priority.INVALID;
    }
}

