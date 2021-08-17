import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> arr = new ArrayList<>();
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello, I'm Duke");
        String command;
        do {
            System.out.print(">> ");
            command = in.nextLine();
            handleInput(command);
        }
        while(!command.equals("bye"));
        System.out.println("Bye, hope to see you again!");
    }

    /**
     * Handles the different input commands to Duke.
     * @param command input command from the user
     */
    public static void handleInput(String command) {
        // first element is the instruction, second element is the rest of the command
        String[] inputWords = command.split(" ", 2);
        switch (inputWords[0]) {
            case "bye":
                break;
            case "list":
                printTasks();
                break;
            case "done":
                markTaskAsDone(Integer.parseInt(inputWords[1]));
                break;
            case "todo":
                addTodo(inputWords[1]);
                break;
            case "deadline": {
                addDeadline(inputWords[1]);
                break;
            }
            case "event": {
                addEvent(inputWords[1]);
                break;
            }
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Lists out all the tasks.
     */
    public static void printTasks() {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(i + 1 + ": " + arr.get(i));
        }
    }

    /**
     * Mark a task as done.
     * @param TaskId ID of the task we are marking as done.
     */
    public static void markTaskAsDone(int TaskId) {
        arr.get(TaskId - 1).setIsDone(true);
        System.out.println("Nice! I've marked this task as done.");
        System.out.println("   " + arr.get(TaskId - 1));
    }

    /**
     * Adds a Todo task.
     * @param description Description of the Todo.
     */
    public static void addTodo(String description) {
        arr.add(new Todo(description));
        printAfterAdding();
    }

    /**
     * Adds a Deadline task.
     * @param fullDescription String that contains the description and deadline of the task.
     */
    public static void addDeadline(String fullDescription) {
        String description = fullDescription.substring(0, fullDescription.indexOf("/by"));
        String deadline = fullDescription.substring(fullDescription.indexOf("/by") + 4);
        arr.add(new Deadline(description, deadline));
        printAfterAdding();
    }

    /**
     * Adds an Event task.
     * @param fullDescription String that contains the description and time of the task.
     */
    public static void addEvent(String fullDescription) {
        String description = fullDescription.substring(0, fullDescription.indexOf("/at"));
        String time = fullDescription.substring(fullDescription.indexOf("/at") + 4);
        arr.add(new Event(description, time));
        printAfterAdding();
    }

    /**
     * Prints information about the latest element that was just added.
     */
    public static void printAfterAdding() {
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + arr.get(arr.size() - 1));
        System.out.println("Now you have " + arr.size() + " task(s) in the list.");
    }
}
