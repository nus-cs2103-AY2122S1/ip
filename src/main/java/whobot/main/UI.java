package whobot.main;

import java.util.Scanner;

import whobot.main.gui.DisplayBuffer;

public class UI {

    /** Colors used to Display Text */
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_CYAN = "\u001B[36m";
    public static final String COLOR_PURPLE = "\u001B[35m";
    public static final String COLOR_RED = "\u001B[31m";

    /** Scanner for getting input from user */
    public static final Scanner CMD_READER = new Scanner(System.in);

    /** Greeting String */
    private static final String LOGO = "\n"
            + "\t\t\t /$$      /$$ /$$                 /$$$$$$$              /$$\n"
            + "\t\t\t| $$  /$ | $$| $$                | $$__  $$            | $$\n"
            + "\t\t\t| $$ /$$$| $$| $$$$$$$   /$$$$$$ | $$  \\ $$  /$$$$$$  /$$$$$$\n"
            + "\t\t\t| $$/$$ $$ $$| $$__  $$ /$$__  $$| $$$$$$$  /$$__  $$|_  $$_/\n"
            + "\t\t\t| $$$$_  $$$$| $$  \\ $$| $$  \\ $$| $$__  $$| $$  \\ $$  | $$\n"
            + "\t\t\t| $$$/ \\  $$$| $$  | $$| $$  | $$| $$  \\ $$| $$  | $$  | $$ /$$\n"
            + "\t\t\t| $$/   \\  $$| $$  | $$|  $$$$$$/| $$$$$$$/|  $$$$$$/  |  $$$$/\n"
            + "\t\t\t|__/     \\__/|__/  |__/ \\______/ |_______/  \\______/    \\___/\n";

    /** Break Line String */
    private static final String LINE = "____________________________________________________________"
            + "____________________________________________________";

    /** GoodBye String */
    private static final String BYE = "\n"
            + "\t\t  /$$$$$$                            /$$ /$$$$$$$                            /$$\n"
            + "\t\t /$$__  $$                          | $$| $$__  $$                          | $$\n"
            + "\t\t| $$  \\__/  /$$$$$$   /$$$$$$   /$$$$$$$| $$  \\ $$ /$$   /$$  /$$$$$$       | $$\n"
            + "\t\t| $$ /$$$$ /$$__  $$ /$$__  $$ /$$__  $$| $$$$$$$ | $$  | $$ /$$__  $$      | $$\n"
            + "\t\t| $$|_  $$| $$  \\ $$| $$  \\ $$| $$  | $$| $$__  $$| $$  | $$| $$$$$$$$      |__/\n"
            + "\t\t| $$  \\ $$| $$  | $$| $$  | $$| $$  | $$| $$  \\ $$| $$  | $$| $$_____/\n"
            + "\t\t|  $$$$$$/|  $$$$$$/|  $$$$$$/|  $$$$$$$| $$$$$$$/|  $$$$$$$|  $$$$$$$       /$$\n"
            + "\t\t\\______/  \\______/  \\______/  \\_______/|_______/  \\____  $$ \\_______/      |__/\n"
            + "\t\t                                                   /$$  | $$\n"
            + "\t\t                                                  |  $$$$$$/\n"
            + "\t\t                                                   \\______/\n";

    /** Enum Type for indicating the type of echo */
    public enum Type { START, MIDDLE, END, COMPLETE, ERROR }

    /***
     * Prints Greeting Message
     */
    public void greeting() {
        System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
        System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
        System.out.println(COLOR_BLUE + LOGO + COLOR_RESET);
        System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
        echo("Hello! I'm the WhoBot.", UI.Type.START);
        echo("What can I do for you?", UI.Type.END);
    }

    /***
     * Prints GoodBye Message
     */
    public void goodbye() {
        System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
        System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
        System.out.println(COLOR_BLUE + BYE + COLOR_RESET);
        echo("I hope to see you again soon :)", UI.Type.MIDDLE);
        System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
        System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
    }

    /***
     * Prints the given string with the right formatting based on  the type
     *
     * @param answer String to print
     * @param type specifies the type of formatting to use
     */
    public void echo(String answer, UI.Type type) {
        if (WhoBot.isGui()) {
            if (type == Type.COMPLETE || type == Type.END || type == Type.ERROR) {
                DisplayBuffer.addToBuffer(answer);
                DisplayBuffer.printBuffer();
            } else {
                DisplayBuffer.addToBuffer(answer);
            }
        } else {
            if (type == UI.Type.COMPLETE) {
                System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
                System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RESET + answer);
                System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
            } else if (type == UI.Type.START) {
                System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
                System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RESET + answer);
            } else if (type == UI.Type.END) {
                System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RESET + answer);
                System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
            } else if (type == UI.Type.MIDDLE) {
                System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RESET + answer);
            } else if (type == UI.Type.ERROR) {
                System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
                System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RED + answer + COLOR_RESET);
                System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
            }
        }
    }

}
