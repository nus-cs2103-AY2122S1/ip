package duke.command;

/**
 * A enumeration of possible input types.
 */
public enum InputTypes {
    BYE("bye"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), LIST("list"),
    DONE("done"), DELETE("delete"), UNKNOWN("unknown"), FIND("find");

    private String value;

    private InputTypes(String value) {
        this.value = value;
    }

    /**
     * @return The value of element.
     */
    public String getValue() {
        return value;
    }
}
