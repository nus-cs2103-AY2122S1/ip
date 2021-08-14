import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String LINE_SEPARATOR = "\t____________________________________________________________\n";
    private static final ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        Duke.greet();
        while (!(input = scanner.nextLine().trim()).equals("bye")) {
            handleUserInput(input);
        }
        Duke.exit();
    }

    /**
     * Handles user input conditionally based on the command.
     */
    private static void handleUserInput(String input) {
        String[] userInput = input.split(" ", 2);
        String command = userInput[0];
        switch(command) {
            case "list":
                Duke.printTaskList();
                break;
            case "done":
                Duke.setTaskDone(Integer.parseInt(input.split(" ")[1]));
                break;
            case "todo":
                String arg = userInput[1];
                Duke.addTask(new Todo(arg));
                break;
            case "deadline":
                String deadlineDescription = userInput[1].split(" /by ")[0];
                String by = userInput[1].split(" /by ")[1];
                Duke.addTask(new Deadline(deadlineDescription, by));
                break;
            case "event":
                String eventDescription = userInput[1].split(" /at ")[0];
                String at = userInput[1].split(" /at ")[1];
                Duke.addTask(new Event(eventDescription, at));
                break;
        }
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
    public static void addTask(Task input) {
        taskList.add(input);
        Duke.print(String.format("Got it. I've added this task:\n  %s\nNow you have %d %s in the list.",
                input, taskList.size(), taskList.size() == 1 ? "task" : "tasks"));
    }

    /**
     * Set i-th task to be done and prints confirmation message.
     */
    private static void setTaskDone(int i) {
        Task task = taskList.get(i - 1);
        task.setDone();
        print(String.format("Nice! I've marked this task as done:\n  %s", task.toString()));
    }

    /**
     * Prints out the task list formatted and indented.
     */
    private static void printTaskList() {
        StringBuilder tasksString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            String taskAsString = i == taskList.size() - 1
                    ? String.format("%d.%s", i + 1, taskList.get(i))
                    : String.format("%d.%s\n", i + 1, taskList.get(i));
            tasksString.append(taskAsString);
        }
        Duke.print(tasksString.toString());
    }
}
