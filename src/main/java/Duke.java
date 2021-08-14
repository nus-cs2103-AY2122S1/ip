import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String LINE_SEPARATOR = "\t____________________________________________________________\n";
    private static final ArrayList<String> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        Duke.greet();
        while (!(input = scanner.nextLine()).equals("bye")) {
            if (input.equals("list")) {
                Duke.printTaskList();
            } else {
                Duke.addTask(input);
            }
        }
        Duke.exit();
    }

    /**
     * Message to be printed by Duke when Duke is first started.
     */
    private static void greet() {
        Duke.print("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Message to be printed by Duke upon exit.
     */
    private static void exit() {
        Duke.print("Bye. Hope to see you again soon!");
    }

    /**
     * Prints a formatted message with line separator on top and bottom.
     *
     * @param message String to be printed.
     */
    private static void print(String message) {
        String indentedMessage = "\t " + message.replaceAll("\n", "\n\t ") + "\n";
        System.out.println(Duke.LINE_SEPARATOR + indentedMessage + Duke.LINE_SEPARATOR);
    }

    /**
     * Adds the input to the list of tasks and prints out the input.
     *
     * @param input to be added and printed.
     */
    public static void addTask(String input) {
        taskList.add(input);
        Duke.print(String.format("added: %s", input));
    }

    /**
     * Prints out the task list formatted and indented.
     */
    private static void printTaskList() {
        StringBuilder taskString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            String task = i == taskList.size() - 1
                    ? String.format("%d. %s", i + 1, taskList.get(i))
                    : String.format("%d. %s\n", i + 1, taskList.get(i));
            taskString.append(task);
        }
        Duke.print(taskString.toString());
    }
}
