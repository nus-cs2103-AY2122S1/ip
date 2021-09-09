package duke.task;

/**
 * An enumeration for different commands
 */
public enum TaskType {
    TODO("todo", "T"),
    DEADLINE("deadline", "D"),
    EVENT("event", "E");

    private final String label;
    private final String abbr;

    /**
     * Initializes an instance of TaskType enumeration.
     * @param label Label of the enumeration (task type)
     * @param abbr Abbreviation of the enumeration (task type)
     */
    TaskType(String label, String abbr) {
        this.label = label;
        this.abbr = abbr;
    }

    /**
     * Gets the label of the task type.
     * @return Label
     */
    public String getLabel() {
        return label;
    }

    /**
     * Gets the abbreviation of the task type.
     * @return Abbreviation
     */
    public String getAbbr() {
        return abbr;
    }
}
