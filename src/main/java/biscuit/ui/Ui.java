package biscuit.ui;

import java.util.Scanner;

/**
 * Ui class deals with interactions with the user.
 */
public class Ui {

    Scanner scanner;

    /**
     * Constructs Ui class.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        String logo = "████████████████████████████████████████\n"
                + "█▄─▄─▀█▄─▄█─▄▄▄▄█─▄▄▄─█▄─██─▄█▄─▄█─▄─▄─█\n"
                + "██─▄─▀██─██▄▄▄▄─█─███▀██─██─███─████─███\n"
                + "▀▄▄▄▄▀▀▄▄▄▀▄▄▄▄▄▀▄▄▄▄▄▀▀▄▄▄▄▀▀▄▄▄▀▀▄▄▄▀▀\n\n"
                + "      ████████████████████\n"
                + "    ██░░░░░░░░░░░░░░░░░░░░██\n"
                + "  ██░░░░██░░░░░░░░░░░░██░░░░██\n"
                + "  ██░░░░██░░██░░░░██░░██░░░░██\n"
                + "  ██░░████░░░░░░░░░░░░████░░██\n"
                + "    ██  ██░░░░████░░░░██  ██\n"
                + "        ██░░░░░██░░░░░██                  ██\n"
                + "          ██░░░░░░░░██░░████████        ██░░██\n"
                + "        ██░░████████░░░░░░░░░░░░██      ██░░██\n"
                + "      ██░░░░░░░░░░░░░░░░░░░░░░░░░░██  ██░░░░██\n"
                + "      ██░░░░░░░░░░░░░░██░░░░░░░░░░░░██░░░░██\n"
                + "      ██░░░░░░░░░░░░░░██░░░░░░░░░░░░██░░░░██\n"
                + "      ██░░░░░░██░░░░░░██░░░░░░░░░░░░░░░░██\n"
                + "      ██░░░░░░██░░░░░░██░░░░░░░░░░░░░░██\n"
                + "      ██░░░░░░██░░░░░░██░░░░░░░░░░████\n"
                + "████████████████████████████████████████\n";

        System.out.println("Woof from\n" + logo);
        System.out.println("Woof! I'm Biscuit.\nWhat can I do for you?");
        showLine();
    }

    /**
     * Prints line separator.
     */
    public void showLine() {
        System.out.println("────────────────────────────────────────");
    }

    /**
     * Prints message.
     *
     * @param message Message to display.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints error.
     *
     * @param error Error to display.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Reads user's next command.
     *
     * @return User's command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

}
