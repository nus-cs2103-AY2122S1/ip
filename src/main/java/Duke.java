import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Main file for chatbot.
 *
 * @author marcuspeh
 * @version Level-4
 * @since 15 Aug 2021
 */

public class Duke {
    /** Exit message. */
    private final static String EXIT = "bye";
    /** Keyword for listing out all the task. */
    private final static String LIST = "list";
    /** Keyword for marking task as done. */
    private final static String DONE = "done";
    /** Keyword for marking task as Deadline task." */
    private final static String DEADLINE = "deadline";
    /** Deadline separator. */
    private final static String DEADLINESEPARATOR = " /by ";
    /** Keyword for marking task as Events. */
    private final static String EVENTS = "event";
    /** Event separator. */
    private final static String EVENTSEPARATOR = " /at ";
    /** Keyword for marking task as Todos. */
    private final static String TODOS = "todo";
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
            message = sc.nextLine().strip();
            String command = message.split(" ")[0];

            if (command.equals(EXIT))
                break;
            else if (command.equals(LIST))
                listTask();
            else if (command.equals(DONE))
                try {
                    markDone(Integer.parseInt(message.substring(DONE.length() + 1)));
                } catch (Exception e) {
                    doneErrorMessage();
                }
            else if (command.equals(DEADLINE))
                try {
                    String[] details = message.split(DEADLINESEPARATOR);
                    addDeadline(details[0].substring(DEADLINE.length() + 1), details[1]);
                } catch (Exception e) {
                    deadlineErrorMessage();
                }
            else if (command.equals(EVENTS))
                try {
                    String[] details = message.split(EVENTSEPARATOR);
                    addEvent(details[0].substring(EVENTS.length() + 1), details[1]);
                } catch (Exception e) {
                    eventErrorMessage();
                }
            else if (command.equals(TODOS))
                try {
                    addTodo(message.substring(TODOS.length() + 1));
                } catch (Exception e) {
                    todoErrorMessage();
                }
            else
                chatErrorMessage();
        }
        exitMessage();
    }

    /**
     * Add the task entered by the user into the list.
     *
     * @param task task input by the user
     */
    private void addTask(Task task) {
        taskList.add(task);
        printMessage("Got it. I've added this task:",
                task.toString(),
                String.format("Now you have %o task(s).", taskList.size()));
    }

    /**
     * Adds a new Event to the task list.
     *
     * @param s Description of the task.
     * @param dateTime Date and time of the event.
     */
    private void addEvent(String s, String dateTime) {
        addTask(new Events(s, dateTime));
    }

    /**
     * Adds a new deadline to the task list.
     *
     * @param s Description of the task
     * @param dateTime Deadline of the task
     */
    private void addDeadline(String s, String dateTime) {
        addTask(new Deadlines(s, dateTime));
    }

    /**
     * Add a new todo to the task list.
     *
     * @param s Description of the task.
     */
    private void addTodo(String s) {
        addTask(new ToDos(s));
    }

    /**
     * List out all the task stored by the user.
     */
    private void listTask() {
        if (taskList.size() == 0) {
            printMessage("You have no task.");
            return;
        }

        String[] task = IntStream.range(0, this.taskList.size())
                    .mapToObj(x -> (x + 1) + ". " + taskList.get(x).toString())
                    .collect(Collectors.toList())
                    .toArray(new String[0]);

        printMessage(task);
    }

    /**
     * Mark the nth task as done.
     *
     * @param n the task to be mark as done.
     */
    private void markDone(int n) {
        Task task = taskList.get(n - 1);
        boolean success = task.markDone();
        if (success)
            printMessage("Nice! I've did mark this task as done:", task.toString());
        else
            printMessage("Ugh! This task was already done:", task.toString());
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
                "done <number> - Sets the task to be done");
    }

    /**
     * Prints out error message if done message does not contains number.
     */
    private void doneErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
                "done <number>",
                "Note: number is based on the number from command 'list'");
    }

    /**
     * Prints out error message if todo message does not contains description.
     */
    private void todoErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
                "todo <description>");
    }

    /**
     * Prints out error message if deadline message does not contains /by.
     */
    private void deadlineErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
                "deadline <description> /by <date/time>");
    }


    /**
     * Prints out error message if event message does not contains /at.
     */
    private void eventErrorMessage() {
        printMessage("Ugh! The command should be in this format:",
               "event <description> /at <date/time>");
    }

    /**
     * Formats the sentences that will be printed out by the chatbot.
     *
     * @param strings Arbitrary number of strings to be printed out
     */
    private void printMessage(String... strings) {
        System.out.println(LINEBREAK);
        for (String str: strings)
            System.out.println("\t" + str);
        System.out.println(LINEBREAK);
    }

    /**
     * Main function to run the chatbot.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.chat();
    }
}
