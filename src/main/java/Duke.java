import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final List<String> list = new ArrayList<>();
    private static final String WELCOME = "Hello, I'm Duke\nWhat can I do for you?";
    private static final String BYE = "Bye. Hope to see you again soon!";

    /**
     * Prints welcome message, then accepts user input until exit command is entered.
     */
    public void start() {
        formatAndPrint(WELCOME);
        Scanner sc = new Scanner(System.in);
        boolean continueListening = true;
        while(continueListening) {
            String input = sc.nextLine();
            continueListening = listen(input);
        }
    }

    /**
     * Accepts user input and runs the appropriate function.
     * @param input String containing user input.
     * @return Boolean that controls whether to continue accepting user input.
     */
    public boolean listen(String input) {
        switch(input) {
            case "bye":
                formatAndPrint(BYE);
                return false;
            case "list":
                displayList();
                return true;
            default:
                addToList(input);
                return true;
        }
    }

    /**
     * Adds the user's input to the list and prints user's input.
     * @param input String that user inputs.
     */
    public void addToList(String input) {
        list.add(input);
        formatAndPrint("added: " + input);
    }

    /**
     * Formats the list of tasks for displaying when the user inputs "list".
     */
    public void displayList() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            output.append(String.format("%d. %s", i + 1, list.get(i)));
            // Append new line for all lines except last line.
            if (i != list.size() - 1) {
                output.append("\n");
            }
        }
        formatAndPrint(output.toString());
    }

    /**
     * Helper function to format output between 2 lines.
     * @param s String to be outputted.
     */
    private static void formatAndPrint(String s) {
        String line = "____________________________________________________________\n";
        System.out.printf("%s%s\n%s", line, s, line);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
