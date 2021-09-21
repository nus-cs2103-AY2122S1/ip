package duke.constant;

/**
 * This is the MessageType class that contains task operations.
 */
public enum MessageType {
    NORMAL("normal"),
    ERROR("error");

    private final String value;

    MessageType(String value) {
        this.value = value;
    }

    /**
     * Returns value of message type.
     *
     * @return MessageType value.
     */
    public String getValue() {
        return value;
    }
}
