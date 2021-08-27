package duke;

import java.util.Scanner;

public class Ui {

    /**
     * Global variables
     */
    private static final String SPACE = "    ";
    private static final String LINE = SPACE + "______________________________________________________________________";
    private static final String END_LINE = SPACE + "======================================================================\n";
    private static final String LOGO = "\n" +
            "███████████████████████████████\n" +
            "█▄─▄─▀█─█─█▄─██─▄█─▄─▄─█▄─██─▄█\n" +
            "██─▄─▀█─▄─██─██─████─████─██─██\n" +
            "▀▄▄▄▄▀▀▄▀▄▀▀▄▄▄▄▀▀▀▄▄▄▀▀▀▄▄▄▄▀▀";
    private Items items;

    public Ui() {
        items = new Items();
    }

    /**
     * method to greet the user
     */
    public void greet() {
        System.out.println(LOGO);
        printMessage("Hello! I'm Bhutu, your personal chatbot!\nWhat can I do for you?");
    }

    /**
     * Get the user input
     * @param sc The scanner to get the input
     * @return The string representation of the user input
     */
    public static String getInput(Scanner sc) {
        return sc.nextLine();
    }

    /**
     * print all bot messages in a specific format.
     * @param message message from the bot.
     */
    public static void printMessage(String message) {
        message = SPACE + message.replace("\n", "\n" + SPACE);
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(END_LINE);
    }
}
