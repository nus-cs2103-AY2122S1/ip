package duke.ui;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.TaskList;

/**
 * Provides methods to interact with user.
 */
public class Ui {
    // Constant Strings
    protected static final String HORIZONTAL_LINE = "____________________________________________________________";
    protected static final String LEFT_INDENT = "    ";

    private static ArrayList<String> log;
    private static UiMode uiMode;

    private Scanner scanner;

    /**
     * Initialize Ui component with Ui mode.
     * @param uiMode Does the
     */
    public Ui(UiMode uiMode) {
        Ui.uiMode = uiMode;
        switch (uiMode) {
        case GUI:
            log = new ArrayList<>();
            break;
        case CLI:
            this.scanner = new Scanner(System.in);
            break;
        default:
            throw new AssertionError(uiMode);
        }
    }

    /**
     * Read next line of user command from System.in.
     * @return next line of user command as string
     */
    public String readCommand() {
        return Ui.uiMode == UiMode.CLI ? scanner.nextLine() : "";
    }

    protected static void printWithIndent(String s) {
        if (Ui.uiMode == UiMode.CLI) {
            System.out.println(LEFT_INDENT + s);
        } else if (Ui.uiMode == UiMode.GUI) {
            log.add(s + "\n");
        }
    }

    /**
     * Prints a divider line.
     */
    public static void printDividerLine() {
        if (Ui.uiMode == UiMode.CLI) {
            printWithIndent(HORIZONTAL_LINE);
        }
    }

    /**
     * Prints welcome message.
     */
    public void printWelcomeMessage() {
        if (Ui.uiMode == UiMode.CLI) {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);
            printDividerLine();
            printWithIndent("Hello! I'm Duke");
            printWithIndent("What can I do for you?");
            printDividerLine();
        }
    }

    /**
     * Prints welcome message.
     */
    public void printWelcomeMessage(MainWindow mainWindow) {
        if (Ui.uiMode == UiMode.GUI) {
            mainWindow.printWelcomeMessage("Hello! I'm Duke. What can I do for you?");
        }
    }

    public static void printGoodbyeMessage() {
        printWithIndent("Bye. Hope to see you again soon!");
    }

    /**
     * Print contents of task list.
     * @param taskList Duke's ask list
     */
    public static void printList(TaskList taskList) {
        requireNonNull(taskList, "task list is not initialized");

        for (int i = 0; i < taskList.size(); i++) {
            printWithIndent((i + 1) + "." + taskList.get(i).toString());
        }
    }

    /**
     * Print contents of task list.
     * @param listSize Size of current list.
     * @throws IllegalArgumentException if list size is negative
     */
    public static void printTaskCount(int listSize) throws IllegalArgumentException {
        if (listSize < 0) {
            throw new IllegalArgumentException("list size should not be negative");
        }

        printWithIndent(getTaskCountString(listSize));
    }

    /**
     * Prints found tasks info.
     * @param tasks Found tasks
     */
    public static void printFoundTasks(String[] tasks) {
        if (tasks != null && tasks.length > 0) {
            printWithIndent("Here are the matching tasks in your list:");
            for (int i = 0; i < tasks.length; i++) {
                printWithIndent((i + 1) + "." + tasks[i]);
            }
        } else {
            printWithIndent("There is no matching task in your list.");
        }
    }

    /**
     * Prints a new task info.
     * @param taskStr String representation of the new task
     */
    public static void printNewTask(String taskStr) {
        requireNonNull(taskStr, "Task string cannot be null");

        printWithIndent("Got it. I've added this task:");
        printWithIndent("  " + taskStr);
    }

    /**
     * Prints a removed task info.
     * @param taskStr String representation of the removed task.
     */
    public static void printRemoveTask(String taskStr) {
        requireNonNull(taskStr, "Task string cannot be null");

        printWithIndent("Noted. I've removed this task: ");
        printWithIndent("  " + taskStr);
    }

    public static void printRemoveAll() {
        printWithIndent("Noted. I've removed all tasks from list.");
    }

    /**
     * Prints a task which is marked as done.
     * @param taskStr String representation of the task which is marked as done.
     */
    public static void printMarkDone(String taskStr) {
        requireNonNull(taskStr, "Task string cannot be null");

        printWithIndent("Nice! I've marked this task as done: ");
        printWithIndent("  " + taskStr);
    }

    /**
     * Prints an error message based on exception type of DukeException.
     * @param e exception to print
     * @param userInput the latest user command before exception happens
     */
    public static void printErrorMessage(DukeException e, String userInput) {
        requireNonNull(userInput, "User input cannot be null");
        switch (e.getType()) {
        case INDEX_OUT_OF_BOUND:
        case INVALID_COMMAND:
        case INVALID_OPERAND:
        case MISSING_OPERAND:
            printWithIndent(userInput + ": " + e.getMessage());
            break;
        case DDL_MISSING_KEYWORD:
            printWithIndent(e.getMessage() + ". Correct format is:");
            printWithIndent("deadline {description} /by {due time}");
            printWithIndent("Example: deadline return book /by Sunday");
            break;
        case EVENT_MISSING_KEYWORD:
            printWithIndent(e.getMessage() + ". Correct format is:");
            printWithIndent("event {description} /at {time period}");
            printWithIndent("Example: event project meeting /at Mon 2-4pm");
            break;
        case OTHERS:
            printWithIndent("Unknown exception occurred.");
            break;
        case PIPE_SYMBOL:
        case HAS_DUPLICATE:
        case FAIL_TO_READ:
        case FAIL_TO_WRITE:
        case FAIL_TO_CREATE_FILE:
        default:
            printWithIndent(e.getMessage());
            break;
        }
    }

    /**
     * Prints an error message based on exception type of DukeException.
     * @param e exception to print
     */
    public static void printErrorMessage(DukeException e) {
        printErrorMessage(e, "");
    }

    protected static String getTaskCountString(int listSize) {
        assert listSize >= 0 : "list size cannot be negative: " + listSize;
        return "Now you have " + listSize + " task" + (listSize <= 1 ? "" : "s")
                + " in the list.";
    }

    public static String getResponse() {
        String s = log.stream().reduce("", (result, str) -> result + " " + str);
        log.clear();
        return s;
    }
}
