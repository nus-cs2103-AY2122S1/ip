import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static final String LINE_SEPARATOR = "\t____________________________________________________________\n";
    private static final ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        Duke.start();
        while (!(input = scanner.nextLine().trim()).equals("bye")) {
            try {
                handleUserInput(input);
            } catch (DukeException e) {
                Duke.print(e.getMessage());
            }
        }
        Duke.exit();
        scanner.close();
    }

    /**
     * Handles user input conditionally based on the command.
     */
    private static void handleUserInput(String input) throws DukeException {
        String[] userInput = input.split(" ", 2);
        String command = userInput[0];
        switch(command) {
            case "list":
                Duke.printTaskList();
                break;
            case "done":
                Duke.setTaskDone(userInput);
                break;
            case "todo":
                Duke.addTodo(userInput);
                break;
            case "deadline":
                Duke.addDeadline(userInput);
                break;
            case "event":
                Duke.addEvent(userInput);
                break;
            case "delete":
                Duke.deleteTask(userInput);
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Message to be printed by Duke when Duke is first started.
     */
    private static void start() {
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
     * Adds a todo task to the list of tasks.
     *
     * @param userInput given by user.
     */
    public static void addTodo(String[] userInput) throws DukeException {
        try {
            Duke.addTask(new Todo(userInput[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Todo description cannot be empty");
        }
    }

    /**
     * Adds a deadline task to the list of tasks.
     *
     * @param userInput given by user.
     */
    public static void addDeadline(String[] userInput) throws DukeException {
        try {
            String deadlineDescription = userInput[1].split(" /by ")[0];
            String by = userInput[1].split(" /by ")[1];
            Duke.addTask(new Deadline(deadlineDescription, by));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Deadline description and time by cannot be empty");
        }
    }

    /**
     * Adds a event task to the list of tasks.
     *
     * @param userInput given by user.
     */
    public static void addEvent(String[] userInput) throws DukeException {
        try {
            String eventDescription = userInput[1].split(" /at ")[0];
            String by = userInput[1].split(" /at ")[1];
            Duke.addTask(new Event(eventDescription, by));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Event description and time at cannot be empty");
        }
    }

    /**
     * Deletes a task given it's index from the list of tasks.
     *
     * @param userInput given by user.
     */
    public static void deleteTask(String[] userInput) throws DukeException {
        try {
            int i = Integer.parseInt(userInput[1]);
            Task task = taskList.get(i - 1);
            taskList.remove(i - 1);
            print(String.format("Noted. I've removed this task:\n  %s\nNow you have %d %s in the list.",
                    task, taskList.size(), taskList.size() == 1
                            ? "task"
                            : "tasks"));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please give a valid number!");
        }
    }

    /**
     * Set i-th task to be done and prints confirmation message.
     *
     * @param userInput given by user.
     */
    private static void setTaskDone(String[] userInput) throws DukeException {
        try {
            int i = Integer.parseInt(userInput[1]);
            Task task = taskList.get(i - 1);
            task.setDone();
            print(String.format("Nice! I've marked this task as done:\n  %s", task.toString()));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Please give a valid number!");
        }
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
