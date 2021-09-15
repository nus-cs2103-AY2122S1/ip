package jarvis.ui;

import java.util.Scanner;

import jarvis.command.CommandTypeEnum;
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
    private Scanner scanner = new Scanner(System.in);

    /**
     * Shows the unformatted message to the user.
     *
     * @param message The message to be shown.
     */
    public static void showOutputMessage(OutputMessage message) {
        System.out.println(message.getMessage());
    }

    /**
     * Shows the formatted message to the user.
     *
     * @param message The message to be shown.
     */
    public static void showFormattedOutputMessage(OutputMessage message) {
        System.out.println(message.getFormattedMessage());
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
        if (taskList.getTaskListSize() == 0) {
            return new OutputMessage("There were no matching tasks in the Stark Industries Database.");
        }
        return new OutputMessage(String.format(
                "%s\n%s",
                "Stark Industries Database contains these matching tasks:",
                taskList
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
                taskList.getTaskListSummary()
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
                taskList.getTaskListSummary()
        ));
    }

    /**
     * Gets the undo message that is to be shown to user.
     *
     * @param numberOfUndos The number of undos performed.
     * @return A OutputMessage containing the undo message.
     */
    public OutputMessage getUndoMessage(int numberOfUndos) {
        return new OutputMessage(String.format(
                "Undo protocol complete! %s command(s) reverted!",
                numberOfUndos
        ));
    }

    /**
     * Gets the help message that is to be shown to user.
     *
     * @return A OutputMessage containing the help message.
     */
    public OutputMessage getHelpMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        String heading = "These are the valid commands in the Stark Industries Database:\n\n";
        stringBuilder.append(heading);

        CommandTypeEnum[] commandTypeEnums = CommandTypeEnum.values();

        for (int i = 0; i < commandTypeEnums.length; i++) {
            stringBuilder.append(String.format("%s. ", i + 1));
            stringBuilder.append(commandTypeEnums[i].getHelpTextForCommand());
            stringBuilder.append("\n\n");
        }

        return new OutputMessage(stringBuilder.toString());
    }

    /**
     * Reads user input from CLI.
     *
     * @return String that is read.
     */
    public String readInput() {
        return this.scanner.nextLine();
    }

    /**
     * Closes the active scanner.
     */
    public void closeScanner() {
        this.scanner.close();
    }
}
