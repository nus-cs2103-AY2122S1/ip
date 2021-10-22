package duke;

public enum SortOrder {
    CHRONOLOGICALLY("chronologically"),
    ALPHABETICALLY("alphabetically");

    public final String label;

    SortOrder(String label) {
        this.label = label;
    }
}
