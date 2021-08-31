package jarvis.ui;

import java.util.Scanner;

import jarvis.exception.JarvisException;
import jarvis.message.ExitMessage;
import jarvis.message.GreetingMessage;
import jarvis.message.OutputMessage;
import jarvis.task.Task;
import jarvis.task.TaskList;

/**
 * Encapsulates the object that interacts with the user.
 */
public class Ui {
    private Scanner sc = new Scanner(System.in);

    /**
     * Shows the unformatted message to the user.
     *
     * @param message The message to be shown.
     */
    public static void showOutputMessage(OutputMessage message) {
        message.print();
    }

    /**
     * Shows the formatted message to the user.
     *
     * @param message The message to be shown.
     */
    public static void showFormattedOutputMessage(OutputMessage message) {
        message.print();
    }

    /**
     * Gets the greeting message.
     *
     * @return A OutputMessage containing the greeting message.
     */
    public OutputMessage getGreetingMessage() {
        return new GreetingMessage();
    }

    /**
     * Gets the exit message.
     *
     * @return A OutputMessage containing the exit message.
     */
    public OutputMessage getExitMessage() {
        return new ExitMessage();
    }

    /**
     * Gets the task list message that is to be shown to user.
     *
     * @param taskList The task list containing all the tasks.
     * @return A OutputMessage containing the task list message.
     */
    public OutputMessage getTaskListMessage(TaskList taskList) {
        return new OutputMessage(String.format(
                "%s\n%s",
                "Here are the tasks in your list:",
                taskList.toString()
        ));
    }

    /**
     * Gets the matching task list message that is to be shown to the user.
     *
     * @param taskList The task list containing the tasks.
     * @return A OutputMessage containing the matching task list message.
     */
    public OutputMessage getMatchingTaskListMessage(TaskList taskList) {
        return new OutputMessage(String.format(
                "%s\n%s",
                "Stark Industries Database contains these matching tasks:",
                taskList.toString()
        ));
    }

    /**
     * Gets the error message that is to be shown to user.
     *
     * @param e The exception.
     * @return A OutputMessage containing the error message.
     */
    public OutputMessage getErrorMessage(JarvisException e) {
        return new OutputMessage(e.getMessage());
    }

    /**
     * Gets the task added message to user.
     *
     * @param task The task which was added.
     * @param taskList The task list containing all the tasks.
     * @return A OutputMessage containing the task added message.
     */
    public OutputMessage getTaskAddedMessage(Task task, TaskList taskList) {
        return new OutputMessage(String.format(
                "Alright! I have added this to the Stark Industries Database:\n\t%s\n%s",
                task.toString(),
                taskList.taskListSummary()
        ));
    }

    /**
     * Gets the task done message that is to be shown to user.
     *
     * @param task The task that was marked as done.
     * @return A OutputMessage containing the task done message.
     */
    public OutputMessage getTaskDoneMessage(Task task) {
        return new OutputMessage(String.format(
                "Nice! I've marked this task as done:\n\t%s",
                task.toString()
        ));
    }

    /**
     * Gets the task deleted message that is to be shown to user.
     *
     * @param task The task which was deleted.
     * @param taskList The task list containing all the tasks.
     * @return A OutputMessage containing the task deleted message.
     */
    public OutputMessage getTaskDeletedMessage(Task task, TaskList taskList) {
        return new OutputMessage(String.format(
                "Initiated Delete protocol. Delete confirmed for:\n\t%s\n%s",
                task.toString(),
                taskList.taskListSummary()
        ));
    }

    /**
     * Reads user input from CLI.
     *
     * @return String that is read.
     */
    public String readInput() {
        return this.sc.nextLine();
    }

    /**
     * Closes the active scanner.
     */
    public void closeScanner() {
        this.sc.close();
    }
}
