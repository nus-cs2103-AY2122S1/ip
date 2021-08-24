package whobot.main;

import java.util.Scanner;

public class UI {

    // Colors used to Display Text
    public static final String COLOR_RESET = "\u001B[0m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_CYAN = "\u001B[36m";
    public static final String COLOR_PURPLE = "\u001B[35m";
    public static final String COLOR_RED = "\u001B[31m";

    // The General Strings Used by the ChatBot
    private static final String LOGO = "\n" +
            "\t\t\t\t\t /$$      /$$ /$$                 /$$$$$$$              /$$\n" +
            "\t\t\t\t\t| $$  /$ | $$| $$                | $$__  $$            | $$\n" +
            "\t\t\t\t\t| $$ /$$$| $$| $$$$$$$   /$$$$$$ | $$  \\ $$  /$$$$$$  /$$$$$$\n" +
            "\t\t\t\t\t| $$/$$ $$ $$| $$__  $$ /$$__  $$| $$$$$$$  /$$__  $$|_  $$_/\n" +
            "\t\t\t\t\t| $$$$_  $$$$| $$  \\ $$| $$  \\ $$| $$__  $$| $$  \\ $$  | $$\n" +
            "\t\t\t\t\t| $$$/ \\  $$$| $$  | $$| $$  | $$| $$  \\ $$| $$  | $$  | $$ /$$\n" +
            "\t\t\t\t\t| $$/   \\  $$| $$  | $$|  $$$$$$/| $$$$$$$/|  $$$$$$/  |  $$$$/\n" +
            "\t\t\t\t\t|__/     \\__/|__/  |__/ \\______/ |_______/  \\______/    \\___/\n";

    private static final String LINE = "____________________________________________________________"
            +"____________________________________________________";

    private static final String BYE = "\n" +
            "\t\t\t  /$$$$$$                            /$$ /$$$$$$$                            /$$\n" +
            "\t\t\t /$$__  $$                          | $$| $$__  $$                          | $$\n" +
            "\t\t\t| $$  \\__/  /$$$$$$   /$$$$$$   /$$$$$$$| $$  \\ $$ /$$   /$$  /$$$$$$       | $$\n" +
            "\t\t\t| $$ /$$$$ /$$__  $$ /$$__  $$ /$$__  $$| $$$$$$$ | $$  | $$ /$$__  $$      | $$\n" +
            "\t\t\t| $$|_  $$| $$  \\ $$| $$  \\ $$| $$  | $$| $$__  $$| $$  | $$| $$$$$$$$      |__/\n" +
            "\t\t\t| $$  \\ $$| $$  | $$| $$  | $$| $$  | $$| $$  \\ $$| $$  | $$| $$_____/\n" +
            "\t\t\t|  $$$$$$/|  $$$$$$/|  $$$$$$/|  $$$$$$$| $$$$$$$/|  $$$$$$$|  $$$$$$$       /$$\n" +
            "\t\t\t\\______/  \\______/  \\______/  \\_______/|_______/  \\____  $$ \\_______/      |__/\n" +
            "\t\t\t                                                   /$$  | $$\n" +
            "\t\t\t                                                  |  $$$$$$/\n" +
            "\t\t\t                                                   \\______/\n";

    // The Global Variables used by the ChatBot
    public static final Scanner CMD_READER = new Scanner(System.in);
    public enum TYPE {START, MIDDLE, END, COMPLETE, ERROR};

    // Method to Print Greeting
    public void greeting() {
        System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
        System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
        System.out.println(COLOR_BLUE + LOGO + COLOR_RESET);
        System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
        echo("Hello! I'm the WhoBot.", UI.TYPE.START);
        echo("What can I do for you?", UI.TYPE.END);
    }

    // Method to Print Goodbye Message
    public void goodbye() {
        System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
        System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
        System.out.println(COLOR_BLUE + BYE + COLOR_RESET);
        echo("I hope to see you again soon :)", UI.TYPE.MIDDLE);
        System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
        System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
    }

    //Method to Echo the Given Word
    public void echo(String answer, UI.TYPE type) {
        if (type == UI.TYPE.COMPLETE) {
            System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RESET + answer);
            System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
        } else if (type == UI.TYPE.START) {
            System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RESET + answer);
        } else if (type == UI.TYPE.END) {
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RESET + answer);
            System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
        } else if (type == UI.TYPE.MIDDLE) {
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RESET + answer);
        } else if (type == UI.TYPE.ERROR) {
            System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
            System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_RED + answer + COLOR_RESET);
            System.out.println(COLOR_CYAN + LINE + COLOR_RESET);
        }
    }

}
