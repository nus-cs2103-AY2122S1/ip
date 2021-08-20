import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Main file for chatbot.
 *
 * @author marcuspeh
 * @version Level-8
 * @since 20 Aug 2021
 */

public class Duke {
    /** For the chatboard to read the user input. */
    private Scanner sc;
    /** Stores all the task. */
    private TaskList taskList;

    /**
     * Constructor for Duke.
     */
    Duke() {
        sc = new Scanner(System.in);
        taskList = new TaskList(Storage.importTask());
    }

    /**
     * Start the chatbot and get it to chat with the user.
     */
    private void chat() {
        greetMessage();
        String message;
        Keyword keyword;
        boolean isRunning = true;
        while (isRunning) {
            message = sc.nextLine().strip();
            try {
                keyword = Parser.parseChat(message);

                switch (keyword) {
                case EXIT:
                    isRunning = false;
                    break;
                case LIST:
                    listTask();
                    break;
                case DONE:
                    taskList.markDone(message);
                    break;
                case DEADLINE:
                    taskList.addDeadline(message);
                    break;
                case EVENTS:
                    taskList.addEvent(message);
                    break;
                case TODOS:
                    taskList.addTodo(message);
                    break;
                case DELETE:
                    taskList.deleteTask(message);
                    break;
                }
            } catch (DukeException e) {
                chatErrorMessage();
            }
        }
        exitMessage();
    }

    /**
     * List out all the task stored by the user.
     */
    private void listTask() {
        List<Task> fullList = taskList.getTaskList();

        if (fullList.size() == 0) {
            printMessage("You have no task.");
            return;
        }

        String[] task = IntStream.range(0, fullList.size())
                    .mapToObj(x -> (x + 1) + ". " + fullList.get(x).toString())
                    .collect(Collectors.toList())
                    .toArray(new String[0]);

        printMessage(task);
    }

    /**
     * Print out the greeting message used when the chat started.
     */
    private void greetMessage() {
        printMessage("Good day there! I'm DUKE\n", "What can I do for you?");
    }

    /**
     * Echos the message the user sends for level-1.
     *
     * @param s Message user sent.
     * @Deprecated Level-2
     */
    private void echoMessage(String s) {
        printMessage(s);
    }

    /**
     * Print out the exit message when chat ends.
     */
    private void exitMessage() {
        printMessage("Farewell! Hope to see you again.");
    }

    /**
     * Prints out chat error message when command is not recognized.
     */
    private void chatErrorMessage() {
        printMessage("Ugh! Only the following commands are recognised.",
                "bye - Ends the chat session.",
                "todo <description> - Adds a new todo to the task list.",
                "deadline <description> /by <date/time> - Adds a new deadline to the task list",
                "event <description> /at <date/time> - Adds a new event to the task list",
                "list - return a list of all the task",
                "done <number> - Sets the task to be done",
                "delete <number> - Delete the task");
    }

    /**
     * Formats the sentences that will be printed out by the chatbot.
     *
     * @param strings Arbitrary number of strings to be printed out
     */
    public static void printMessage(String... strings) {
        System.out.println("\t____________________________________________________________");
        for (String str: strings)
            System.out.println("\t" + str);
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Main function to run the chatbot.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.chat();
    }
}
