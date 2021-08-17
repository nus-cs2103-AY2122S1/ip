import java.util.Locale;
import java.util.Scanner;

public class Duke {

    // Colors used to Display Text
    public static final String COLOR_REST = "\u001B[0m";
    public static final String COLOR_BLUE = "\u001B[34m";
    public static final String COLOR_CYAN = "\u001B[36m";
    public static final String COLOR_PURPLE = "\u001B[35m";

    // The General Strings Used by the ChatBot
    private static final String logo = "\n" +
            "\t\t /$$      /$$ /$$                 /$$$$$$$              /$$    \n" +
            "\t\t| $$  /$ | $$| $$                | $$__  $$            | $$    \n" +
            "\t\t| $$ /$$$| $$| $$$$$$$   /$$$$$$ | $$  \\ $$  /$$$$$$  /$$$$$$  \n" +
            "\t\t| $$/$$ $$ $$| $$__  $$ /$$__  $$| $$$$$$$  /$$__  $$|_  $$_/  \n" +
            "\t\t| $$$$_  $$$$| $$  \\ $$| $$  \\ $$| $$__  $$| $$  \\ $$  | $$    \n" +
            "\t\t| $$$/ \\  $$$| $$  | $$| $$  | $$| $$  \\ $$| $$  | $$  | $$ /$$\n" +
            "\t\t| $$/   \\  $$| $$  | $$|  $$$$$$/| $$$$$$$/|  $$$$$$/  |  $$$$/\n" +
            "\t\t|__/     \\__/|__/  |__/ \\______/ |_______/  \\______/    \\___/  \n" +
            "                                                               \n";

    private static final String line = "__________________________________________________________________________________________";

    private static final String bye = "\n" +
            "\t  /$$$$$$                            /$$ /$$$$$$$                            /$$\n" +
            "\t /$$__  $$                          | $$| $$__  $$                          | $$\n" +
            "\t| $$  \\__/  /$$$$$$   /$$$$$$   /$$$$$$$| $$  \\ $$ /$$   /$$  /$$$$$$       | $$\n" +
            "\t| $$ /$$$$ /$$__  $$ /$$__  $$ /$$__  $$| $$$$$$$ | $$  | $$ /$$__  $$      | $$\n" +
            "\t| $$|_  $$| $$  \\ $$| $$  \\ $$| $$  | $$| $$__  $$| $$  | $$| $$$$$$$$      |__/\n" +
            "\t| $$  \\ $$| $$  | $$| $$  | $$| $$  | $$| $$  \\ $$| $$  | $$| $$_____/          \n" +
            "\t|  $$$$$$/|  $$$$$$/|  $$$$$$/|  $$$$$$$| $$$$$$$/|  $$$$$$$|  $$$$$$$       /$$\n" +
            "\t\\______/  \\______/  \\______/  \\_______/|_______/  \\____  $$ \\_______/      |__/\n" +
            "\t                                                   /$$  | $$                    \n" +
            "\t                                                  |  $$$$$$/                    \n" +
            "\t                                                   \\______/                     \n";

    // Method to Print Greeting
    public static void greeting() {
        System.out.println(COLOR_CYAN + line + COLOR_REST);
        System.out.println(COLOR_BLUE + logo + COLOR_REST);
        System.out.println(COLOR_CYAN + line + COLOR_REST);
        System.out.println("Hello! I'm the WhoBot\n" +
                "What can I do for you?");
        System.out.println(COLOR_CYAN + line + COLOR_REST);
    }

    // Method to Print GoodBye Message
    public static void goodbye() {
        System.out.println(COLOR_CYAN + line + COLOR_REST);
        System.out.println(COLOR_BLUE + bye + COLOR_REST);
        System.out.println("I hope to see you again soon :)");
        System.out.println(COLOR_CYAN + line + COLOR_REST);
    }

    //Method to Echo the Given Word
    public static void echo(String answer) {
        System.out.println(COLOR_CYAN + line + COLOR_REST);
        System.out.println(COLOR_BLUE + "WhoBot > " + COLOR_REST + answer);
        System.out.println(COLOR_CYAN + line + COLOR_REST);
    }

    //Main Method
    public static void main(String[] args) {

        greeting();

        Scanner cmdReader = new Scanner(System.in);

        while (true) {
            // Take in the input
            String command;
            System.out.print(COLOR_PURPLE + "> " + COLOR_REST);
            command = cmdReader.nextLine().trim();

            //Quits if the input is bye or goodbye (case-insensitive)
            if (command.toLowerCase(Locale.ROOT).equals("bye") || command.toLowerCase(Locale.ROOT).equals("goodbye")) {
                goodbye();
                break;
            } else {
                //Else echoes the input line
                echo(command);
            }
        }
    }
}
