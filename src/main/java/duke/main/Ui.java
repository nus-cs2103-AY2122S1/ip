package duke.main;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents user interface abstraction.
 *
 * @author Gordon Yit
 * @version CS2103T
 */
public class Ui {
    private static String logo = " ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    private static String padding = "~";
    private static String firstLine = " Hello! I'm Duke.Duke ";
    private static String secondLine = " What do you wanna do today? ";
    private static int bufferLength = 5;
    private static int width = Integer.max(firstLine.length(), secondLine.length()) + bufferLength;
    private static final String FAREWELL = "@@@@ Till we meet again, my friend @@@@";
    private static String buffer1 = padding.repeat((width - firstLine.length()) / 2);
    private static String buffer2 = padding.repeat((width - secondLine.length()) / 2);
    private static String welcomeMessage = buffer1 + firstLine + buffer1 + "\n" + buffer2 + secondLine + buffer2 + "\n";
    private static final String GREETING = "Hello from\n" + logo + welcomeMessage;
    private Scanner sc;

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
        String command = sc.nextLine();
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
        assert numTasksRemaining >= 0 : "number of task remaining cannot be a negative number";
        return generateNotificationMessage("Alrightty, I've removed this task:", task, numTasksRemaining);
    }

    /**
     * Shows the task added.
     *
     * @param task the task added.
     * @param newNumTasks number of tasks after adding the new duke.task.
     */
    public String showTaskAdded(Task task, int newNumTasks) {
        assert newNumTasks >= 0 : "new number of tasks cannot be a negative number";
        return generateNotificationMessage("Got it. I've added this task:", task, newNumTasks);
    }

    @SuppressWarnings("checkstyle:SeparatorWrap")
    private String generateNotificationMessage(String headerMessage, Task task, int taskNum) {
        String notificationMessage = String.format("%s\n", headerMessage);
        notificationMessage += String.format("~~%S~~\n", task.toString());
        notificationMessage += String.format("Now you have %s %s remaining.", taskNum, (
            taskNum > 1 ? "tasks" : "task"));
        return notificationMessage;
    }
    /**
     * Lists out all the tasks Duke.Duke is keeping track of.
     *
     * @param tasks current taskList of tasks.
     */
    public String showListOfTasks(TaskList tasks, int numTasks) {
        String header = "Here are the tasks in your list:";
        return iterate(header, tasks);
    }

    /**
     * Lists out all the tasks that falls on a specified date.
     *
     * @param tasks   list of duke.task that fall on the specified date.
     * @param message the header message.
     */
    public String showMatchingTasks(TaskList tasks, String message) {
        return iterate(message, tasks);
    }
    private String iterate(String headerMessage, TaskList tasks) {
        String listOfTasksDisplay = String.format("%s\n", headerMessage);
        String taskString;
        for (int i = 0; i < tasks.getNumTasks(); i++) {
            assert i < tasks.getNumTasks() : "index should be less than number of tasks";
            taskString = tasks.getTask(i).toString();
            listOfTasksDisplay += String.format("%s.%s\n", i + 1, taskString);
        }
        return listOfTasksDisplay;
    }
}
