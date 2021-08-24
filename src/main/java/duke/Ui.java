package duke;

/**
 * A general user interface that identifies the format of messages displayed to the user.
 */
public class Ui {
    private static final String INDENTATION = "     ";
    private static final String SUBINDENTATION = "       ";

    public static String getIndentation() {
        return INDENTATION;
    }

    public static String getSubIndentation() {
        return SUBINDENTATION;
    }

    /**
     * Standardise the format of an input message.
     *
     * @param message the string to be shown to the user.
     */
    public String formatMessage(String message) {
        return "    ____________________________________________________________\n" + INDENTATION
                + message + "    ____________________________________________________________";
    }
}
