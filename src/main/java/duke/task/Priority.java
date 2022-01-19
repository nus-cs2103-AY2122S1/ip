package duke.task;

public enum Priority {
    HIGH,
    MEDIUM,
    LOW;

    /**
     * Factory method for Priority enum.
     *
     * @param s The string to be converted to Priority enum.
     * @return The corresponding Priority enum.
     */
    public static Priority of(String s) {
        for (Priority priority : Priority.values()) {
            if (s.equalsIgnoreCase(priority.toString())) {
                return priority;
            }
        }

        return null;
    }
}
