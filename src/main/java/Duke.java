import exception.TaskManagerException;
import task.Task;

import java.util.Scanner;

/**
 * Duke class.
 *
 * This class holds the main logic for Duke project (CS2103T's iP).
 * Duke is a task manager for users that love the command line interface.
 * @author muhammad-khair, damithc
 */
public class Duke {

    private final static String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String EXIT_KEY = "bye";

    private final ChatBot chatBot = new ChatBot();

    private final TaskManager taskManager = new TaskManager();

    /**
     * Driver function for main logic.
     *
     * @param args command line arguments fed
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    /**
     * Runs the whole duke process.
     */
    public void run() {
        chatBot.print("Hello from\n" + LOGO);
        chatBot.info("Hello! I'm Duke.\n\tWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine().trim();
        while (!userInput.equals(EXIT_KEY)) {
            makeDecision(userInput);
            userInput = scanner.nextLine().trim();
        }
        scanner.close();

        chatBot.info("Bye. Hope to see you again soon!");
    }

    /**
     * Makes a decision based on the user's input, and acts on it.
     * Some actions are listing tasks, marking them as done, creating and deleting tasks.
     *
     * @param userInput user input to process as commands for Duke
     */
    public void makeDecision(String userInput) {
        try {
            if (userInput.equals("list")) {
                chatBot.list(taskManager.listTasks());

            } else if (userInput.startsWith("done")) {
                String taskNumberString = userInput.substring(4).trim();
                Task completedTask = taskManager.markTaskAsDone(taskNumberString);
                chatBot.info("Nice! I've marked this task as done:\n\t  " + completedTask);

            } else if (userInput.startsWith("delete")) {
                String taskNumberString = userInput.substring(6).trim();
                Task deletedTask = taskManager.deleteTask(taskNumberString);
                chatBot.info("Noted. I've removed this task:\n\t  " + deletedTask +
                        "\n\tNow you have " + taskManager.getTaskListSize() +
                        " tasks in the list.");

            } else if (userInput.startsWith("todo")) {
                echoTaskCreation(taskManager.addToDoTask(userInput.substring(4)));

            } else if (userInput.startsWith("event")) {
                echoTaskCreation(taskManager.addEventTask(userInput.substring(5)));

            } else if (userInput.startsWith("deadline")) {
                echoTaskCreation(taskManager.addDeadlineTask(userInput.substring(8)));

            } else {
                chatBot.error("Please ensure instruction follows specified format.");
            }

        } catch (TaskManagerException exception) {
            chatBot.error(exception.getLocalizedMessage());
        }
    }

    /**
     * Helper function that echos out completed task creation.
     *
     * @param task task which was just created
     */
    public void echoTaskCreation(Task task) {
        chatBot.info("Got it. I've added this task:\n\t  " + task + "\n\tNow you have " +
                taskManager.getTaskListSize() + " tasks in the list.");
    }
}
