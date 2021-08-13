import java.util.Scanner;

public class Duke {
    // Set up constant
    final static String divider = "\t--------------------------------------------------------";
    final static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    final static String banner = "____    __    ____  _______  __        ______   ______   .___  ___.  _______    .___________.  ______       _______   __    __   __  ___  _______  __  \n" +
            "\\   \\  /  \\  /   / |   ____||  |      /      | /  __  \\  |   \\/   | |   ____|   |           | /  __  \\     |       \\ |  |  |  | |  |/  / |   ____||  | \n" +
            " \\   \\/    \\/   /  |  |__   |  |     |  ,----'|  |  |  | |  \\  /  | |  |__      `---|  |----`|  |  |  |    |  .--.  ||  |  |  | |  '  /  |  |__   |  | \n" +
            "  \\            /   |   __|  |  |     |  |     |  |  |  | |  |\\/|  | |   __|         |  |     |  |  |  |    |  |  |  ||  |  |  | |    <   |   __|  |  | \n" +
            "   \\    /\\    /    |  |____ |  `----.|  `----.|  `--'  | |  |  |  | |  |____        |  |     |  `--'  |    |  '--'  ||  `--'  | |  .  \\  |  |____ |__| \n" +
            "    \\__/  \\__/     |_______||_______| \\______| \\______/  |__|  |__| |_______|       |__|      \\______/     |_______/  \\______/  |__|\\__\\ |_______|(__) \n" +
            "                                                                                                                                                       ";
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        printWelcomeMessage();
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("exit")) {
            echo(input);
            input = sc.nextLine();
        }
        printExitMessage();
    }

    public static void prettyPrint(String message) {
        System.out.println(String.format("%s\n\t%s\n%s", divider, message, divider));
    }

    public static void printWelcomeMessage() {
        System.out.println(banner);
        prettyPrint("Hello! I'm Duke, your personal CLI bot!\n\tWhat can I do for you?");
    }

    public static void echo(String input) {
        prettyPrint(input);
    }

    public static void printExitMessage() {
        prettyPrint("Bye bye! See you again soon!");
    }
}
