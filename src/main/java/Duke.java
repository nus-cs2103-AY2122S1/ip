import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String exitString = "bye";
    private static final String exitMessage = "Bye. Hope to see you again soon!";
    private static final String introMessage = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String doneString = "done";
    private static final String doneMessage = "Nice! I've marked this task as done:\n";
    private static final String doneOutsideOfList = "Oops, you do not have such a task!";
    private static final ArrayList<Task> tasks = new ArrayList<>();

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
            System.out.println(formatMessage(react(userInput)));
        }
    }

    /**
     * Takes in the user input and reacts accordingly to specification.
     * @param userInput the user input string.
     * @return string of reaction.
     */
    private static String react(String userInput) {
        String[] split = userInput.split(" ");
        if (split.length == 2 && split[0].equals(doneString) && isInteger(split[1])) {
            int index = Integer.parseInt(split[1]) - 1;
            if (index < tasks.size()) {
                Task task = tasks.get(index);
                task.markAsDone();
                return doneMessage + task.toString();
            }
            return doneOutsideOfList;
        }

        switch (userInput) {
            case ("list"):
                return getTaskListString();
            default:
                Task task = new Task(userInput);
                tasks.add(task);
                return "added: " + userInput;
        }
    }

    /**
     * Utility function to test whether input string is an integer.
     * @param input input String.
     * @return returns true if input string can be parsed as an integer, else false.
     */
    private static Boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Method to get the string of the tasks list.
     * @return string with the list of tasks.
     */
    private static String getTaskListString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(i+1).append(". ").append(tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                output.append("\n");
            }
        }
        return output.toString();
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
