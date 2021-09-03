package type;

/**
 * Encapsulates valid values for command type
 */
public enum CommandTypeEnum {
    BYE("bye"),
    DEADLINE("deadline"),
    DELETE("delete"),
    DONE("done"),
    EVENT("event"),
    FIND("find"),
    LIST("list"),
    TODO("todo");

    private final String value;

    CommandTypeEnum(String value) {
        this.value = value;
    }

    /**
     * Returns string representation of a command type enum.
     *
     * @return String representation of a command type enum
     */
    @Override
    public String toString() {
        return this.value;
    }
}
