package duke;

import java.util.Scanner;

public class Ui {
    public static final String SPACE = "    ";
    public static final String LOGO =
        SPACE + "██████   ██████  ██████   █████  ████████ \n" +
        SPACE + "██   ██ ██    ██ ██   ██ ██   ██    ██    \n" +
        SPACE + "██████  ██    ██ ██████  ███████    ██    \n" +
        SPACE + "██   ██ ██    ██ ██   ██ ██   ██    ██    \n" +
        SPACE + "██████   ██████  ██   ██ ██   ██    ██";
    public static final String BOT_LINE = "============================================================";
    public static final String USER_LINE = "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _";
    private Scanner sc = new Scanner(System.in);

    /**
     * Displays the Greetings
     */
    public void showGreetings() {
        System.out.println(SPACE + BOT_LINE);
        System.out.println(LOGO);
        System.out.println(SPACE + BOT_LINE);
        System.out.println(SPACE + "Jak się masz? My name-a Borat. I like you.");
        System.out.println(SPACE + "What I do for you?");
        System.out.println(SPACE + BOT_LINE);
    }

    /**
     * Displays the goodbye message
     */
    public void showGoodBye() {
        this.showMessage("Bye. Have a good time!");
    }

    /**
     * Displays Borat's message to the user
     * @param message The message content to be displayed
     */
    public void showMessage(String message) {
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
    public String getInput() {
        return sc.nextLine();
    }

    /**
     * Show the list of commands
     * @return A String of all the commands and their description
     */
    public String getCommandMenu() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Constants.Commands c : Constants.Commands.values()) {
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
    public String getAllAcceptedDates() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Constants.Dates c : Constants.Dates.values()) {
            sb.append("(" + i++ + ") ");
            sb.append(c.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
