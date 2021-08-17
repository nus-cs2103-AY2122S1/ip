import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Declares the string that exits the program.
     */
    private static String exitString = "bye";
    private static String exitMessage = "Bye. Hope to see you again soon!";
    private static String introMessage = "Hello! I'm Duke\nWhat can I do for you?";
    private static ArrayList<String> tasks = new ArrayList<>();

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
            case ("list"):
                printTaskList();
                break;
            default:
                tasks.add(userInput);
                System.out.println(formatMessage("added: " + userInput));
        }
    }

    /**
     * Method to print the list of items in the tasks array list.
     */
    private static void printTaskList() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(i+1).append(". ").append(tasks.get(i));
            if (i != tasks.size() - 1) {
                output.append("\n");
            }
        }
        System.out.println(formatMessage(output.toString()));
    }

    /**
     * Formats string before printing.
     * @param message string to be printed.
     */
    private static String formatMessage(String message) {
        return  "_____________________________________\n" +
                message +                            "\n" +
                "_____________________________________\n";
    }
}
