package task;

/**
 * The is the Operation class that contains task operations.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public enum Operation {

    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    CLEAR("clear"),
    BYE("bye");

    private final String value;

    Operation(String value) {
        this.value = value;
    }

    /**
     * Get value of operation.
     *
     * @return operation value
     */
    public String getValue() {
        return value;
    }
}
