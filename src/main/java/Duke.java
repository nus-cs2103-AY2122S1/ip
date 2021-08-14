import java.util.ArrayList;
import java.util.Scanner;

/**
 * This Duke class implements the functionalities of a chatbot,
 * helping a person to keep track of various things.
 *
 * @author Yeo Jun Wei
 * @version Level-4
 */
public class Duke {
    /** Horizontal line for formatting */
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    /** Greeting message to be displayed when bot starts running */
    private static final String GREETING_MESSAGE = "Hello! I'm JWBot\nWhat can I do for you?";

    /** Goodbye message to be displayed when bot stops running */
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";

    /** An ArrayList to store tasks entered by the user */
    private static final ArrayList<Task> tasksList = new ArrayList<>();

    /** A Scanner instance to obtain user input */
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        greetUser();
        listenToCommands();
    }

    /**
     * Displays the formatted greeting message to the user.
     */
    private static void greetUser() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(GREETING_MESSAGE);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the formatted goodbye message to the user.
     */
    private static void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(GOODBYE_MESSAGE);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays the formatted user-entered task to the user,
     * and adds the task to tasksList.
     *
     * @param taskDescription Task entered by the user.
     * @param taskType The type of the task.
     */
    private static void addTask(String taskDescription, String taskType) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Got it. I've added this task:");

        Task taskToBeAdded;
        if (taskType.equals("todo")) {
            taskToBeAdded = new Todo(taskDescription);
        } else if (taskType.equals("deadline")) {
            String[] wordsArr = taskDescription.split(" /by ");
            String task = wordsArr[0];
            String deadline = wordsArr[1];
            taskToBeAdded = new Deadline(task, deadline);
        } else {
            String[] wordsArr = taskDescription.split(" /at ");
            String task = wordsArr[0];
            String timeFrame = wordsArr[1];
            taskToBeAdded = new Event(task, timeFrame);
        }
        tasksList.add(taskToBeAdded);

        System.out.println("  " + taskToBeAdded);
        if (tasksList.size() == 1) {
            System.out.println("Now you have " + tasksList.size() + " task in the list.");
        } else {
            System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Displays all previously entered tasks to the user.
     */
    private static void listTasks() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasksList.size(); i++) {
            System.out.println((i + 1) + "." + tasksList.get(i));
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskId The id of the task to be marked as done.
     */
    private static void markAsDone(String taskId) {
        int taskPositionInList = Integer.valueOf(taskId) - 1;
        Task specifiedTask = tasksList.get(taskPositionInList);
        specifiedTask.markAsDone();

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Nice! I've marked this task as done:\n  " + specifiedTask);
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Listens to the user-entered commands, and acts accordingly.
     */
    private static void listenToCommands() {
        String command = scanner.nextLine();
        if (command.equals("bye")) {
            exit();
        } else {
            if (command.equals("list")) {
                listTasks();
            } else {
                // Splits the string on spaces, and acts accordingly based on the first word
                String[] wordsArr = command.split(" ");
                if (wordsArr[0].equals("done")) {
                    markAsDone(wordsArr[1]);
                } else {
                    String taskType = wordsArr[0];
                    String taskDescription = command.substring(command.indexOf(" ") + 1);
                    addTask(taskDescription, taskType);
                }
            }
            listenToCommands();
        }
    }
}
