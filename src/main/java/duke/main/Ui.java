package duke.main;

import duke.task.Task;

import java.util.Scanner;

/**
 * Represents user interface abstraction.
 *
 * @author Gordon Yit
 * @version CS2103T
 */
public class Ui {
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    private String padding = "~";
    private String firstLine = " Hello! I'm Duke.Duke ";
    private String secondLine = " What do you wanna do today? ";
    private int bufferLength = 5;
    private int width = Integer.max(firstLine.length(), secondLine.length()) + bufferLength;
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String FAREWELL = "@@@@ Till we meet again, my friend @@@@";
    private String buffer1 = padding.repeat((width - firstLine.length()) / 2);
    private String buffer2 = padding.repeat((width - secondLine.length()) / 2);
    private String welcomeMessage = buffer1 + firstLine + buffer1 + "\n" + buffer2 + secondLine + buffer2 + "\n";
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String GREETING = "Hello from\n" + logo + welcomeMessage;
    private Scanner sc;
    private String command;

    /**
     * Class constructor.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Welcomes the user.
     */
    public String showWelcome() {
        return GREETING;
    }

    /**
     * Reads user commands
     *
     * @return user commands as a string.
     */
    public String readCommand() {
        command = sc.nextLine();
        return command;
    }

    /**
     * Shows farewell message to user.
     */
    public String showFarewell() {
        return FAREWELL;
    }

    /**
     * Shows the diving line.
     */
    public String showLine() {
        String line = "_________________________________";
        return line;
    }

    /**
     * Shows the error message from loading the file.
     */
    public String showLoadingError() {
        String loadingError = "There was a problem loading the file.";
        return loadingError;
    }

    /**
     * Shows the error message from dukeException class.
     *
     * @param errorMessage exception message.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Displays a specific duke.task that was marked done.
     *
     * @param task the duke.task marked as done.
     */
    public String showMarkTaskDone(Task task) {
        String doneMessage = "Yay, one task down!\n";
        doneMessage += String.format("~~%s~~", task.toString());
        return doneMessage;
    }

    /**
     * Shows the task deleted.
     *
     * @param task the deleted task.
     * @param numTasksRemaining number of tasks after deleting the task.
     */
    public String showTaskDeleted(Task task, int numTasksRemaining) {
        String deletionMessage = "Alrighty, I've removed this task:\n";
        deletionMessage += String.format("~~%s~~\n", task.toString());
        deletionMessage += String.format("Now, you have %s %s remaining", numTasksRemaining,
                (numTasksRemaining > 1 ? "tasks" : "task"));
        return deletionMessage;
    }

    /**
     * Shows the duke.task added.
     *
     * @param task        the duke.task added.
     * @param newNumTasks number of tasks after adding the new duke.task.
     */
    public String showTaskAdded(Task task, int newNumTasks) {
        String additionMessage = "Got it. I've added this task:\n";
        additionMessage += String.format("~~%S~~\n", task.toString());
        additionMessage += String.format("Now you have %s %s in the list.", newNumTasks,
                (newNumTasks > 1 ? "tasks" : "task"));
        return additionMessage;
    }

    /**
     * Lists out all the tasks Duke.Duke is keeping track of.
     *
     * @param tasks current taskList of tasks.
     */
    public String showListOfTasks(TaskList tasks) {
        String header = "Here are the tasks in your list:";
        return iterate(header, tasks);
    }

    /**
     * Lists out all the tasks that falls on a specified date.
     *
     * @param tasks   list of duke.task that fall on the specified date.
     * @param message the header message.
     */
    public String showMatchingTasks(TaskList tasks, String searchPhrase, String message) {
        return iterate(message, tasks);
    }
    private String iterate(String headerMessage, TaskList tasks) {
        String listOfTasksDisplay = headerMessage + "\n";
        for (int i = 0; i < tasks.getNumTasks(); i++) {
            listOfTasksDisplay += String.format("%s.%s\n", i + 1, tasks.getTask(i).toString());
        }
        return listOfTasksDisplay;
    }
}
