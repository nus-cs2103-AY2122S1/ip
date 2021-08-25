package WhoBot.Main;

import java.util.Scanner;

public class UI {

    /** Colors used to Display Text */
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_CYAN = "\u001B[36m";
    public static final String COLOR_PURPLE = "\u001B[35m";
    public static final String COLOR_RED = "\u001B[31m";

    /** Greeting String */
    private static final String logo = "\n" +
            "\t\t\t /$$      /$$ /$$                 /$$$$$$$              /$$\n" +
            "\t\t\t| $$  /$ | $$| $$                | $$__  $$            | $$\n" +
            "\t\t\t| $$ /$$$| $$| $$$$$$$   /$$$$$$ | $$  \\ $$  /$$$$$$  /$$$$$$\n" +
            "\t\t\t| $$/$$ $$ $$| $$__  $$ /$$__  $$| $$$$$$$  /$$__  $$|_  $$_/\n" +
            "\t\t\t| $$$$_  $$$$| $$  \\ $$| $$  \\ $$| $$__  $$| $$  \\ $$  | $$\n" +
            "\t\t\t| $$$/ \\  $$$| $$  | $$| $$  | $$| $$  \\ $$| $$  | $$  | $$ /$$\n" +
            "\t\t\t| $$/   \\  $$| $$  | $$|  $$$$$$/| $$$$$$$/|  $$$$$$/  |  $$$$/\n" +
            "\t\t\t|__/     \\__/|__/  |__/ \\______/ |_______/  \\______/    \\___/\n";

    /** Break Line String */
    private static final String line = "________________________________________________________________________________________________________________";

    /** GoodBye String */
    private static final String bye = "\n" +
            "\t\t  /$$$$$$                            /$$ /$$$$$$$                            /$$\n" +
            "\t\t /$$__  $$                          | $$| $$__  $$                          | $$\n" +
            "\t\t| $$  \\__/  /$$$$$$   /$$$$$$   /$$$$$$$| $$  \\ $$ /$$   /$$  /$$$$$$       | $$\n" +
            "\t\t| $$ /$$$$ /$$__  $$ /$$__  $$ /$$__  $$| $$$$$$$ | $$  | $$ /$$__  $$      | $$\n" +
            "\t\t| $$|_  $$| $$  \\ $$| $$  \\ $$| $$  | $$| $$__  $$| $$  | $$| $$$$$$$$      |__/\n" +
            "\t\t| $$  \\ $$| $$  | $$| $$  | $$| $$  | $$| $$  \\ $$| $$  | $$| $$_____/\n" +
            "\t\t|  $$$$$$/|  $$$$$$/|  $$$$$$/|  $$$$$$$| $$$$$$$/|  $$$$$$$|  $$$$$$$       /$$\n" +
            "\t\t\\______/  \\______/  \\______/  \\_______/|_______/  \\____  $$ \\_______/      |__/\n" +
            "\t\t                                                   /$$  | $$\n" +
            "\t\t                                                  |  $$$$$$/\n" +
            "\t\t                                                   \\______/\n";


    /** Scanner for getting input from user */
    public static final Scanner cmdReader = new Scanner(System.in);

    /** Enum Type for indicating the type of echo */
    public enum TYPE {START, MIDDLE, END, COMPLETE, ERROR};

    // Method to Print Greeting

    /***
     * Prints Greeting Message
     */
    public void greeting() {
        System.out.println(COLOR_CYAN + line + COLOR_RESET);
        System.out.println(COLOR_CYAN + line + COLOR_RESET);
        System.out.println(COLOR_BLUE + logo + COLOR_RESET);
        System.out.println(COLOR_CYAN + line + COLOR_RESET);
        echo("Hello! I'm the WhoBot.", UI.TYPE.START);
        echo("What can I do for you?", UI.TYPE.END);
    }

    // Method to Print GoodBye Message

    /***
     * Prints GoodBye Message
     */
    public void goodbye() {
        System.out.println(COLOR_CYAN + line + COLOR_RESET);
        System.out.println(COLOR_CYAN + line + COLOR_RESET);
        System.out.println(COLOR_BLUE + bye + COLOR_RESET);
        echo("I hope to see you again soon :)", UI.TYPE.MIDDLE);
        System.out.println(COLOR_CYAN + line + COLOR_RESET);
        System.out.println(COLOR_CYAN + line + COLOR_RESET);
    }

    //Method to Echo the Given Word

    /***
     * Prints the given string with the right formatting based on  the type
     *
     * @param answer String to print
     * @param type specifies the type of formatting to use
     */
    public void echo(String answer, UI.TYPE type) {
        if (type == UI.TYPE.COMPLETE) {
            System.out.println(COLOR_CYAN + line + COLOR_RESET);
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RESET + answer);
            System.out.println(COLOR_CYAN + line + COLOR_RESET);
        } else if (type == UI.TYPE.START) {
            System.out.println(COLOR_CYAN + line + COLOR_RESET);
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RESET + answer);
        } else if (type == UI.TYPE.END) {
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RESET + answer);
            System.out.println(COLOR_CYAN + line + COLOR_RESET);
        } else if (type == UI.TYPE.MIDDLE) {
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RESET + answer);
        } else if (type == UI.TYPE.ERROR) {
            System.out.println(COLOR_CYAN + line + COLOR_RESET);
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RED + answer + COLOR_RESET);
            System.out.println(COLOR_CYAN + line + COLOR_RESET);
        }
    }

}
