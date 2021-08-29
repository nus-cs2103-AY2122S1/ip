package duke;

/**
 * Enum class for types of events.
 */
public enum Type {
    TODO,
    DEADLINE,
    EVENT;

    /**
     * String to denote type.
     * @param type Type enum.
     * @return String representation of type.
     */
    public static String typeString(Type type) {
        if (type == Type.EVENT) {
            return "E";
        } else if (type == Type.TODO) {
            return "T";
        } else if (type == Type.DEADLINE) {
            return "D";
        } else {
            throw new NullPointerException();
        }
    }

    public static String getConnector(Type type) {
        if (type == Type.EVENT) {
            return "at";
        } else if (type == Type.TODO) {
            return "";
        } else if (type == Type.DEADLINE) {
            return "by";
        } else {
            throw new NullPointerException();
        }
    }
}
