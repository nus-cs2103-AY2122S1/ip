package duke.task;

/**
 * This is the Operation class that contains task operations.
 */
public enum Operation {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    CLEAR("clear"),
    FIND("find"),
    COMING("coming"),
    BYE("bye");

    private final String value;

    Operation(String value) {
        this.value = value;
    }

    /**
     * Returns value of operation.
     *
     * @return Operation value.
     */
    public String getValue() {
        return value;
    }
}
