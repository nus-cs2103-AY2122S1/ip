package duke;


/**
 * Deals with interactions with the user.
 */
public class Ui {
    private String horizontal = "_______________________\n";
    private String logo =
            "                     _               _         \n"
                    + "                    | |             | |        \n"
                    + "  _ __ ___     ___  | |   ___     __| |  _   _ \n"
                    + " | '_ ` _ \\   / _ \\ | |  / _ \\   / _` | | | | |\n"
                    + " | | | | | | |  __/ | | | (_) | | (_| | | |_| |\n"
                    + " |_| |_| |_|  \\___| |_|  \\___/   \\__,_|  \\__, |\n"
                    + "                                          __/ |\n"
                    + "                                         |___/ ";

    /**
     * Constructor of Ui.
     */
    public Ui() {
    }

    /**
     * Prints the welcome message.
     */
    public String welcome() {
        return ("Hello from\n" + logo);
    }


    /**
     * Print exit message.
     */
    public String exit() {
        return ("Byebye ~ nya");
    }


    /**
     * Output the error message.
     *
     * @param error Error name.
     */
    public static String showError(String error) {
        return ("OvO I found the following errors! " + error);
    }

}
