package duke.util;

/**
 * Represents the User Interface.
 */
public class Ui {
    //duke.Duke logo
    private static final String logo = " ___          _        \n"
            + "|  _ \\ _    _| | ____ \n"
            + "| |  | | |  | | |/ / _ \\\n"
            + "| |_| | |_| |   < __/\n"
            + "|___/ \\___|_|\\_\\_|\n";

    public Ui() {
    }

    /**
     * Welcome statement for new user.
     */
    public String welcome() {
        return "Hello this is\n" + logo;
    }
}
