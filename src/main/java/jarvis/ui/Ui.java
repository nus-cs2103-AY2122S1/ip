package jarvis.ui;

import java.util.Scanner;

import jarvis.exception.JarvisException;
import jarvis.message.ExitMessage;
import jarvis.message.GreetingMessage;
import jarvis.message.OutputMessage;
import jarvis.task.Task;
import jarvis.task.TaskList;

/**
 * Encapsulates the object that interacts with the user
 */
public class Ui {
    Scanner sc = new Scanner(System.in);

    private void showOutputMessage(OutputMessage message) {
        message.print();
    }

    private void showFormattedOutputMessage(OutputMessage message) {
        message.print();
    }

    /**
     * Shows the greeting message to user
     */
    public void showGreetingMessage() {
        OutputMessage greetingMessage = new GreetingMessage();
        greetingMessage.print();
    }

    /**
     * Shows the exit message to user
     */
    public void showExitMessage() {
        OutputMessage exitMessage = new ExitMessage();
        exitMessage.print();
    }

    /**
     * Shows the task list message to user
     */
    public void showTaskList(TaskList taskList) {
        OutputMessage taskListMessage = new OutputMessage(String.format(
                "%s\n%s",
                "Here are the tasks in your list:",
                taskList.toString()
        ));
        taskListMessage.print();
    }

    /**
     * Shows the matching task list message to the user
     *
     * @param taskList The task list containing the tasks
     */
    public void showMatchingTaskList(TaskList taskList) {
        OutputMessage taskListMessage = new OutputMessage(String.format(
                "%s\n%s",
                "Stark Industries Database contains these matching tasks:",
                taskList.toString()
        ));
        taskListMessage.print();
    }

    /**
     * Shows the error message to user
     */
    public void showError(JarvisException e) {
        OutputMessage errorMessage = new OutputMessage(e.getMessage());
        errorMessage.print();
    }

    /**
     * Shows the task added message to user
     */
    public void showTaskAddedMessage(Task task, TaskList taskList) {
        OutputMessage taskAddedMessage = new OutputMessage(String.format(
                "Alright! I have added this to the Stark Industries Database:\n\t%s\n%s",
                task.toString(),
                taskList.taskListSummary()
        ));
        taskAddedMessage.print();
    }

    /**
     * Shows the task done message to user
     */
    public void showTaskDoneMessage(Task task) {
        OutputMessage taskDoneMessage = new OutputMessage(String.format(
                "Nice! I've marked this task as done:\n\t%s",
                task.toString()
        ));
        taskDoneMessage.print();
    }

    /**
     * Shows the task deleted message to user
     */
    public void showTaskDeletedMessage(Task task, TaskList taskList) {
        OutputMessage taskDeletedMessage = new OutputMessage(String.format(
                "Initiated Delete protocol. Delete confirmed for:\n\t%s\n%s",
                task.toString(),
                taskList.taskListSummary()
        ));
        taskDeletedMessage.print();
    }

    /**
     * Reads user input from CLI
     *
     * @return String that is read
     */
    public String readInput() {
        return this.sc.nextLine();
    }

    /**
     * Closes the active scanner
     */
    public void closeScanner() {
        this.sc.close();
    }
}
