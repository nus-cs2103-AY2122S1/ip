import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Main file for chatbot.
 *
 * @author marcuspeh
 * @version 1.0
 * @since 14 Aug 2021
 */

public class Duke {
    /** Exit message. */
    private final static String EXIT = "bye";
    /** Keyword for listing out all the task. */
    private final static String LIST = "list";
    /** Line separator. */
    private final static String LINEBREAK = "\t____________________________________________________________";

    /** For the chatboard to read the user input. */
    private Scanner sc;
    /** Stores all the task. */
    private List<Task> taskList;

    /**
     * Constructor for Duke.
     */
    Duke() {
        sc = new Scanner(System.in);
        taskList = new ArrayList<>();
    }

    /**
     * Start the chatbot and get it to chat with the user.
     */
    private void chat() {
        greetMessage();

        String message;
        while (true) {
            message = sc.nextLine().toLowerCase();

            if (message.equals(EXIT))
                break;
            else if (message.equals(LIST))
                listTask();
            else
                addTask(message);
        }
        exitMessage();
    }

    /**
     * Print out the greeting message used when the chat started.
     */
    private void greetMessage() {
        printMessage("Good day th're! I'm DUKE\n", "What can I doth f'r thee?");
    }

    /**
     * Echos the message the user sends for level-1.
     * @deprecated
     * @param s Message user sent.
     */
    private void echoMessage(String s) {
        printMessage(s);
    }

    /**
     * Add the task entered by the user into the list.
     * @param s task input by the user
     */
    private void addTask(String s) {
        Task task = new Task(s);
        taskList.add(task);
        printMessage("added: " + task.toString());
    }

    /**
     * List out all the task stored by the user.
     */
    private void listTask() {
        String[] task = taskList.stream()
                    .map(x -> x.toString())
                    .collect(Collectors.toList())
                    .toArray(new String[0]);

        for (int i = 0; i < task.length; i++)
            task[i] = i + 1 + ". " + task[i];

        printMessage(task);
    }

    /**
     * Print out the exit message when chat ends.
     */
    private void exitMessage() {
        printMessage("Farewell! Desire to seeth thee again.");
    }

    /**
     * Formats the sentences that will be printed out by the chatbot
     * @param strings Arbitrary number of strings to be printed out
     */
    private void printMessage(String... strings) {
        System.out.println(LINEBREAK);
        for (String str: strings)
            System.out.println("\t" + str);
        System.out.println(LINEBREAK);
    }

    /**
     * Main function to run the chatbot
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.chat();
    }
}
