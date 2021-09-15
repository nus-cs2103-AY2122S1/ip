package duke;

import java.util.Scanner;

public class Ui {
    public static final String SPACE = "    ";
    public static final String LOGO = SPACE
            + "██████   ██████  ██████   █████  ████████ "
            + "\n" + SPACE
            + "██   ██ ██    ██ ██   ██ ██   ██    ██    \n" + SPACE
            + "██████  ██    ██ ██████  ███████    ██    \n" + SPACE
            + "██   ██ ██    ██ ██   ██ ██   ██    ██    \n" + SPACE
            + "██████   ██████  ██   ██ ██   ██    ██";
    public static final String BOT_LINE = "============================================================";
    public static final String USER_LINE = "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _";
    private static Scanner sc = new Scanner(System.in);

    /**
     * Displays the Greetings
     */
    public static void showGreetings() {
        System.out.println(SPACE + BOT_LINE);
        System.out.println(LOGO);
        System.out.println(SPACE + BOT_LINE);
        System.out.println(SPACE + "Jak się masz? My name-a Borat. I like you.");
        System.out.println(SPACE + "What I do for you?");
        System.out.println(SPACE + BOT_LINE);
    }

    /**
     * Returns the greeting message.
     * @return The greeting message.
     */
    public static String getGreetingMessage() {
        return "Jak sie masz? My name-a Borat. I like you.\n"
                + "What I do for you?";
    }

    /**
     * Returns the goodbye message
     * @return The goodbye message.
     */
    public static String getGoodByeMessage() {
        return "Bye. Have a good time!";
    }

    /**
     * Displays Borat's message to the user
     * @param message The message content to be displayed
     */
    public static void showMessage(String message) {
        assert message != null : "[duke.Ui.showMessage]: message parameter should not be null.";
        message = SPACE + message.replace("\n", "\n" + SPACE);
        System.out.println(SPACE + USER_LINE);
        System.out.println(message);
        System.out.println(" ");
        System.out.println(SPACE + BOT_LINE);
    }

    /**
     * Get the user input
     * @return The string representation of the user input
     */
    public static String getInput() {
        return sc.nextLine();
    }

    /**
     * Returns the manual for all the commands
     * @return A String of all the commands and their description
     */
    public static String getHelpMenu() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Constant.Command c : Constant.Command.values()) {
            sb.append("(" + i++ + ") ");
            sb.append(c.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Show the list of accepted dates type
     * @return A String of all the dates and their description
     */
    public static String getAllAcceptedDates() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Constant.Date c : Constant.Date.values()) {
            sb.append("(" + i++ + ") ");
            sb.append(c.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
