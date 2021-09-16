package ponyo.ui;

/**
 * Text UI of the application.
 */
public class Ui {
    /**
     * Shows messages to user.
     *
     * @param messages the messages to be printed
     * @return an array of strings to be printed in order
     */
    public static String[] show(String... messages) {
        assert messages.length > 0;
        return messages;
    }
}
