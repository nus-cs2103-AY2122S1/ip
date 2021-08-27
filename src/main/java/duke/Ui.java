package duke;

import java.util.Scanner;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    Scanner sc = new Scanner(System.in);
    String horizontal = "_______________________";
    String logo =
            "                     _               _         \n" +
                    "                    | |             | |        \n" +
                    "  _ __ ___     ___  | |   ___     __| |  _   _ \n" +
                    " | '_ ` _ \\   / _ \\ | |  / _ \\   / _` | | | | |\n" +
                    " | | | | | | |  __/ | | | (_) | | (_| | | |_| |\n" +
                    " |_| |_| |_|  \\___| |_|  \\___/   \\__,_|  \\__, |\n" +
                    "                                          __/ |\n" +
                    "                                         |___/ ";

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
     *
     * @return Horizontal divider line.
     */
    public void showLine() {
        System.out.println(horizontal);
    }

    /**
     * Print exit message.
     */
    public void exit(){
        System.out.println("Byebye ~ nya");
    }

    /**
     * Read the next line of user inpput.
     *
     * @return User's next line.
     */
    public String readCommand() {
        String command = sc.nextLine();
        return command;
    }

    /**
     * Output the error message.
     *
     * @param error Error name.
     */
    public static void showError(String error) {
        System.out.println("OvO I found the following errors! " + error);
    }


}