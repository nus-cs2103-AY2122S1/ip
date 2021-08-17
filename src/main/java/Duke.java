import java.util.Scanner;

public class Duke {
    /**
     * Declares the string that exits the program.
     */
    private static String exitString = "bye";
    private static String exitMessage = "Bye. Hope to see you again soon!";
    private static String introMessage = "Hello! I'm Duke\n    What can I do for you?";

    public static void main(String[] args) {

        // Introduces the program.
        System.out.println(formatMessage(introMessage));

        // Creates a new Scanner object.
        Scanner scanner = new Scanner(System.in);

        // Continuously grabbing the user input and reacting to it.
        // Loop only ends when userInput is the exitString.
        while (true) {

            // Grabs the user input.
            String userInput = scanner.nextLine();

            // Checks if userInput is equal to the exitString,
            // if so then print the exitMessage and exit the program, else react accordingly.
            if (userInput.equals(exitString)) {
                System.out.println(formatMessage(exitMessage));
                break;
            }

            // Reacts to userInput.
            react(userInput);
        }
    }

    /**
     * Takes in the user input and reacts accordingly to specification.
     * @param userInput the user input string.
     */
    private static void react(String userInput) {
        switch (userInput) {
            default:
                System.out.println(formatMessage(userInput));
        }
    }

    /**
     * Formats string before printing.
     * @param message string to be printed.
     */
    private static String formatMessage(String message) {
        return  "    _____________________________________\n" +
                "    " + message +                       "\n" +
                "    _____________________________________\n";
    }
}
