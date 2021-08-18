import java.util.Scanner;

/**
 * Project Duke is a educational software project.
 * It is designed to take you through the steps of building a small software incrementally.
 */

public class Duke {
    /**
     * This is the scanner object used to get user input.
     */
    private static Scanner sc= new Scanner(System.in);

    /**
     * This is the declared string that triggers the exit.
     */
    private static String cancelWord = "bye";


    /**
     * Prints out the initial greeting message.
     */
    private static void greet() {
        System.out.println("\t____________________________________________________________\n" +
                "\tHello! I'm Duke. \n\tWhat can I do for you?\n" +
                "\t____________________________________________________________");
    }

    /**
     * Asks users for their inputs.
     * When the string matches cancelWord, program will close.
     */

    private static void askForResponse() {
        // boolean flag to indicate if loop should be exited
        boolean exit = false;

        // if boolean is false, loop will be ran
        while (!exit) {
            String response = sc.nextLine();
            if (response.equals(cancelWord)) {
                System.out.println("\t____________________________________________________________\n\t" +
                        "Bye. Hope to see you again soon!" +
                        "\n\t____________________________________________________________");
                exit = true;
            } else {
                System.out.println("\t____________________________________________________________\n\t" +
                        response +
                        "\n\t____________________________________________________________");
            }
        }
    }

    /**
     * Starts the Duke chatbot.
     * Users can input String to interact with the chatbot.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // Initial greeting of user
        greet();

        // Starts to ask for string of instruction
        askForResponse();
    }
}
