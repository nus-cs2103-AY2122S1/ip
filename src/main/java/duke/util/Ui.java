package duke.util;

/**
 * Represents the User Interface.
 */
public class Ui {

    private static final String logo = " ___          _        \n"
            + "|  _ \\ _    _| | ____ \n"
            + "| |  | | |  | | |/ / _ \\\n"
            + "| |_| | |_| |   < __/\n"
            + "|___/ \\___|_|\\_\\_|\n";

    public Ui() {
    }

    /**
     * Welcomes new user.
     *
     * @return Returns the welcome message for new user.
     */
    public String welcome() {
        return "Hello this is\n" + logo;
    }
}
