import java.util.Scanner;
/**
 * This class encapsulates a CLI bot named Duke.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class Duke {
    // Set up constant
    static final String divider = "\t--------------------------------------------------------";
    static final String banner =
            "____    __    ____  _______  __        ______   ______   .___  ___.  _______    .___________.  ______       _______   __    __   __  ___  _______  __  \n"
                    + "\\   \\  /  \\  /   / |   ____||  |      /      | /  __  \\  |   \\/   | |   ____|   |           | /  __  \\     |       \\ |  |  |  | |  |/  / |   ____||  | \n"
                    + " \\   \\/    \\/   /  |  |__   |  |     |  ,----'|  |  |  | |  \\  /  | |  |__      `---|  |----`|  |  |  |    |  .--.  ||  |  |  | |  '  /  |  |__   |  | \n"
                    + "  \\            /   |   __|  |  |     |  |     |  |  |  | |  |\\/|  | |   __|         |  |     |  |  |  |    |  |  |  ||  |  |  | |    <   |   __|  |  | \n"
                    + "   \\    /\\    /    |  |____ |  `----.|  `----.|  `--'  | |  |  |  | |  |____        |  |     |  `--'  |    |  '--'  ||  `--'  | |  .  \\  |  |____ |__| \n"
                    + "    \\__/  \\__/     |_______||_______| \\______| \\______/  |__|  |__| |_______|       |__|      \\______/     |_______/  \\______/  |__|\\__\\ |_______|(__) \n"
                    + "                                                                                                                                                       ";
    static final List list = new List();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        printWelcomeMessage();
        String input = sc.nextLine();
        while (!input.equalsIgnoreCase("exit")) {
            if (input.equalsIgnoreCase("list")) {
                list.printList();
            } else {
                list.addToList(input);
            }
            input = sc.nextLine();
        }
        printExitMessage();
    }

    /**
     * Formats message passed in and prints it out to the screen.
     *
     * @param message Message to be pretty printed. */
    public static void prettyPrint(String message) {
        System.out.printf("%s\n\t%s\n%s%n", divider, message, divider);
    }

    /**
     * Prints the welcome message when a user uses the bot for the first time.
     */
    public static void printWelcomeMessage() {
        System.out.println(banner);
        prettyPrint(
                "Hello! I'm Duke, your personal CLI bot!\n\t"
                        + "I can now help you keep track of things!\n\t"
                        + "Simply type in what you need me to keep track of and type 'list' to see the things you have added.\n\t"
                        + "Once you are done, just type 'exit' to quit the program.");
    }

    /**
     * Prints the exit message when user types in the exit command.
     */
    public static void printExitMessage() {
        prettyPrint("Bye bye! See you again soon!");
    }
}
