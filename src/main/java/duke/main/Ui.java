package duke.main;

import java.util.Scanner;

import duke.task.Task;

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
    private String farewell = "@@@@ Till we meet again, my friend @@@@";
    private String buffer1 = padding.repeat((width - firstLine.length()) / 2);
    private String buffer2 = padding.repeat((width - secondLine.length()) / 2);
    private String welcomeMessage = buffer1 + firstLine + buffer1 + "\n" + buffer2 + secondLine + buffer2 + "\n";
    private String greeting = "Hello from\n" + logo + welcomeMessage;
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
    public void showWelcome() {
        System.out.println(greeting);
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
    public void showFarewell() {
        System.out.println(farewell);
        sc.close();
    }

    /**
     * Shows the diving line.
     */
    public void showLine() {
        String line = "_________________________________";
        System.out.println(line);
    }

    /**
     * Shows the error message from loading the file.
     */
    public void showLoadingError() {
        String loadingError = "There was a problem loading the file.";
        System.out.println(loadingError);
    }

    /**
     * Shows the error message from dukeException class.
     *
     * @param errorMessage exception message.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays a specific duke.task that was marked done.
     *
     * @param task the duke.task marked as done.
     */
    public void showMarkTaskDone(Task task) {
        String doneMessage = "Yay, one duke.task down!";
        System.out.println(doneMessage);
        String taskMarkedAsDone = String.format("~~%s~~", task.toString());
        System.out.println(taskMarkedAsDone);
    }

    /**
     * Displays the duke.task deleted.
     *
     * @param task              the deleted duke.task.
     * @param numTasksRemaining number of tasks after deleting the duke.task.
     */
    public void showTaskDeleted(Task task, int numTasksRemaining) {
        String deletionMessage = "Alrightty, I've removed this duke.task:";
        System.out.println(deletionMessage);
        String deletedTask = String.format("~~%s~~", task.toString());
        System.out.println(deletedTask);
        String tasksRemaining = String.format("Now, you have %s %s remaining", numTasksRemaining, (
            numTasksRemaining > 1 ? "tasks" : "duke/task"));
        System.out.println(tasksRemaining);
    }

    /**
     * Displays the duke.task added.
     *
     * @param task        the duke.task added.
     * @param newNumTasks number of tasks after adding the new duke.task.
     */
    public void showTaskAdded(Task task, int newNumTasks) {
        String additionMessage = "Got it. I've added this duke.task:";
        System.out.println(additionMessage);
        String addedTask = String.format("~~%S~~", task.toString());
        System.out.println(addedTask);
        String newTally = String.format("Now you have %s %s in the list.", newNumTasks, (
            newNumTasks > 1 ? "tasks" : "duke/task"));
        System.out.println(newTally);
    }

    /**
     * Lists out all the tasks Duke.Duke is keeping track of.
     *
     * @param tasks current taskList of tasks.
     */
    public void showListOfTasks(TaskList tasks) {
        String header = "Here are the tasks in your list:";
        iterate(header, tasks);
    }

    /**
     * Lists out all the tasks that falls on a specified date.
     *
     * @param tasks   list of duke.task that fall on the specified date.
     * @param message the header message.
     */
    public void showMatchingTasks(TaskList tasks, String message) {
        iterate(message, tasks);
    }

    private void iterate(String headerMessage, TaskList tasks) {
        System.out.println(headerMessage);
        for (int i = 0; i < tasks.getNumTasks(); i++) {
            System.out.println(String.format("%s.%s", i + 1, tasks.getTask(i).toString()));
        }
    }
}
