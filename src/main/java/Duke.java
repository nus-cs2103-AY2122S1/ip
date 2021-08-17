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
        try {
            switch (inputWords[0]) {
                case "bye":
                    break;
                case "list":
                    printTasks();
                    break;
                case "done": {
                    if (inputWords.length == 1) {
                        throw new DukeException("☹ OOPS!!! Please provide a task ID that exists.");
                    }
                    int taskId = Integer.parseInt(inputWords[1]);
                    if (taskId < 1 || taskId > arr.size()) {
                        throw new DukeException("☹ OOPS!!! Please provide a task ID that exists.");
                    }
                    markTaskAsDone(taskId);
                    break;
                }
                case "todo":
                    if (inputWords.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    addTodo(inputWords[1]);
                    break;
                case "deadline":
                    if (inputWords.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    addDeadline(inputWords[1]);
                    break;
                case "event":
                    if (inputWords.length == 1) {
                        throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                    }
                    addEvent(inputWords[1]);
                    break;
                case "delete":
                    if (inputWords.length == 1) {
                        throw new DukeException("☹ OOPS!!! Please provide a task ID that exists.");
                    }
                    int taskId = Integer.parseInt(inputWords[1]);
                    if (taskId < 1 || taskId > arr.size()) {
                        throw new DukeException("☹ OOPS!!! Please provide a task ID that exists.");
                    }
                    deleteTask(taskId);
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
     * @param taskId ID of the task we are marking as done.
     */
    public static void markTaskAsDone(int taskId) {
        arr.get(taskId - 1).setIsDone(true);
        System.out.println("Nice! I've marked this task as done.");
        System.out.println("   " + arr.get(taskId - 1));
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
    public static void addDeadline(String fullDescription) throws DukeException {
        int sepIndex = fullDescription.indexOf("/by");
        if (fullDescription.charAt(sepIndex + 3) != ' ' || sepIndex == -1) {
            throw new DukeException("☹ OOPS!!! Please input with the correct format e.g. deadline return books" +
                    " /by Sunday");
        }
        String description = fullDescription.substring(0, sepIndex);
        String deadline = fullDescription.substring(sepIndex + 4);
        arr.add(new Deadline(description, deadline));
        printAfterAdding();
    }

    /**
     * Adds an Event task.
     * @param fullDescription String that contains the description and time of the task.
     */
    public static void addEvent(String fullDescription) throws DukeException {
        int sepIndex = fullDescription.indexOf("/at");
        if (fullDescription.charAt(sepIndex + 3) != ' ' || sepIndex == -1) {
            throw new DukeException("☹ OOPS!!! Please input with the correct format e.g. event read books" +
                    " /at Saturday 9am");
        }
        String description = fullDescription.substring(0, sepIndex);
        String time = fullDescription.substring(sepIndex + 4);
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

    /**
     * Deletes a specific task.
     * @param taskId ID of the task to be deleted.
     */
    public static void deleteTask(int taskId) {
        System.out.println("Noted. I have removed this task:");
        System.out.println("   " + arr.get(taskId - 1));
        arr.remove(taskId - 1);
        System.out.println("Now you have " + arr.size() + " task(s) in the list.");
    }
}
