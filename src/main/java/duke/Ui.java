package duke;


/**
 * Deals with interactions with the user.
 */
public class Ui {
    //private Scanner sc = new Scanner(System.in);
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
    public void welcome() {
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Show divider.
     */
    public void showLine() {
        System.out.println(horizontal);
    }

    /**
     * Print exit message.
     */
    public String exit() {
        return ("Byebye ~ nya");
    }

    /*
    public String readCommand() {
        String command = sc.nextLine();
        return command;
    }

     */

    public String wrap(String input) {
        return horizontal + input + horizontal;
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
